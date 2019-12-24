package ru.sk1ly.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WorkWithDisplayActivity extends AppCompatActivity {

    private TextView mCurrentBrightness;
    private SeekBar mBrightnessSeekBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_work_with_display);

        checkWriteSettingsPermission();
        try {
            mCurrentBrightness = findViewById(R.id.current_brightness_value);
            mCurrentBrightness.setText(getString(
                    R.string.current_brightness_start_text,
                    Settings.System.getInt(
                            getContentResolver(),
                            Settings.System.SCREEN_BRIGHTNESS)));
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

        mBrightnessSeekBar = findViewById(R.id.brightness_seek_bar);
        mBrightnessSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                TODO Менять mCurrentBrightness
//                https://stackoverflow.com/questions/18312609/change-the-system-brightness-programmatically
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void onClickGoToSystemDisplaySettingsButton(View view) {
        Intent systemDisplaySettingsIntent = new Intent(Settings.ACTION_DISPLAY_SETTINGS);
        if (systemDisplaySettingsIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(systemDisplaySettingsIntent);
        }
    }


    private void checkWriteSettingsPermission() {
//        if (Settings.System.canWrite(this)) {
//        }
//        TODO Проверять доступ на изменение, если доступа нет, то переводить пользоателя в настройки
//        https://stackoverflow.com/questions/32083410/cant-get-write-settings-permission
    }
}
