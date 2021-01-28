package ru.sk1ly.firstapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class WorkWithDisplayActivity extends AppCompatActivity {

    private int mCurrentBrightnessPercentage;
    private TextView mCurrentBrightnessFullText;
    private SeekBar mBrightnessSeekBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_work_with_display);

        mCurrentBrightnessPercentage =
                convertFloatCurrentBrightnessToPercentageValue(
                        convertSystemCurrentBrightnessToFloat(
                                getCurrentBrightnessFromSystemSettings()));
        mCurrentBrightnessFullText = findViewById(R.id.current_brightness_full_text);
        setTextToCurrentBrightnessFullText();

        initBrightnessSeekBar();
    }

    private void setNewBrightnessValuesAndUpdateSystemBrightness(int progress) {
        mCurrentBrightnessPercentage = progress;
        setTextToCurrentBrightnessFullText();

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.screenBrightness = (float) mCurrentBrightnessPercentage / 100;
        getWindow().setAttributes(layoutParams);
    }

    public void onClickGoToSystemDisplaySettingsButton(View view) {
        Intent systemDisplaySettingsIntent = new Intent(Settings.ACTION_DISPLAY_SETTINGS);
        if (systemDisplaySettingsIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(systemDisplaySettingsIntent);
        }
    }

    private int getCurrentBrightnessFromSystemSettings() {
        try {
            return Settings.System.getInt(
                    getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private float convertSystemCurrentBrightnessToFloat(int systemCurrentBrightness) {
        return systemCurrentBrightness / (float) 255;
    }

    private int convertFloatCurrentBrightnessToPercentageValue(float floatCurrentBrightness) {
        return (int) (floatCurrentBrightness * 100);
    }

    private void setTextToCurrentBrightnessFullText() {
        mCurrentBrightnessFullText.setText(getString(
                R.string.current_brightness_start_text,
                mCurrentBrightnessPercentage));
    }

    private void initBrightnessSeekBar() {
        mBrightnessSeekBar = findViewById(R.id.brightness_seek_bar);
        mBrightnessSeekBar.setProgress(mCurrentBrightnessPercentage);
        mBrightnessSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!Settings.System.canWrite(getApplicationContext())) {
                        mBrightnessSeekBar.setProgress(mCurrentBrightnessPercentage);
                    } else {
                        setNewBrightnessValuesAndUpdateSystemBrightness(progress);
                    }
                } else {
                    showToastWithLackOfVerification();
                    setNewBrightnessValuesAndUpdateSystemBrightness(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!Settings.System.canWrite(getApplicationContext())) {
                        Toast.makeText(getApplicationContext(),
                                "Права на изменение системных настроек отсутствуют!",
                                Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(WorkWithDisplayActivity.this)
                                .setTitle("Отсутствуют права")
                                .setMessage("Необходимо выдать права на изменение системных настроек.\n" +
                                        "Иначе вы не сможете воспользоваться данной функцией.\n" +
                                        "Продолжить?")
                                .setCancelable(false)
                                .setPositiveButton("Конечно", new OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton("Нит", new OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(getApplicationContext(), "Ну чтож..", Toast.LENGTH_SHORT).show();
                                    }
                                });
                        alertDialogBuilder.show();
                    }
                } else {
                    showToastWithLackOfVerification();
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }


    private void showToastWithLackOfVerification() {
        Toast.makeText(
                getApplicationContext(),
                "Я не знаю как проверить вашу версию Android. Корректность дальнейшей работы не гарантирована!",
                Toast.LENGTH_SHORT)
                .show();
    }
}
