package ru.sk1ly.firstapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static ru.sk1ly.firstapp.Constants.*;

public class MainActivity extends AppCompatActivity {

    private static final int AUTHORIZE_USER = 0;
    private static final String KEK_WORD = "Kek";
    private static final String PORTRAIT_ORIENTATION_WORD_RUS = "портретная";
    private static final String LANDSCAPE_ORIENTATION_WORD_RUS = "альбомная";
    private static final String UNKNOWN_ORIENTATION_WORD_RUS = "неизвестная";

    private TextView mUserFirstName;
    private TextView mUserSecondName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.activity_main_label);
        mUserFirstName = findViewById(R.id.user_first_name);
        mUserSecondName = findViewById(R.id.user_second_name);

        if (savedInstanceState != null) {
            mUserFirstName.setText(savedInstanceState.getCharSequence(Keys.USER_FIRST_NAME));
            mUserSecondName.setText(savedInstanceState.getCharSequence(Keys.USER_SECOND_NAME));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onAuthorizeMenuClick(MenuItem item) {
        Toast.makeText(getApplicationContext(), "Скоро тут будет авторизация!", Toast.LENGTH_SHORT).show();
    }


    public void onSettingsMenuClick(MenuItem item) {
        Toast.makeText(getApplicationContext(), "Скоро тут будут настройки!", Toast.LENGTH_SHORT).show();
    }

    public void onAboutAppMenuClick(MenuItem item) {
        Intent aboutAppIntent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(aboutAppIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AUTHORIZE_USER && resultCode == RESULT_OK) {
            mUserFirstName.setText(data.getStringExtra(Keys.USER_FIRST_NAME_ANSWER));
            mUserSecondName.setText(data.getStringExtra(Keys.USER_SECOND_NAME_ANSWER));
        }  // TODO AUTHORIZE CODE? И правильно ли использовать RESULT_CODE CANCELED?

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence(Keys.USER_FIRST_NAME, mUserFirstName.getText());
        outState.putCharSequence(Keys.USER_SECOND_NAME, mUserSecondName.getText());
    }

    public void onClickAuthorizeButton(View view) {
        Intent authorizeIntent = new Intent(MainActivity.this, AuthorizeActivity.class);
        startActivityForResult(authorizeIntent, AUTHORIZE_USER);
    }

    public void onClickSayKekButton(View view) {
        Toast.makeText(getApplicationContext(), KEK_WORD, Toast.LENGTH_LONG).show();
    }

    public void onClickSayKekWithRicardoButton(View view) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.ricardo_with_kek_toast,
                (ViewGroup) findViewById(R.id.ricardo_with_kek_toast_container));

        TextView text = layout.findViewById(R.id.kek_word);
        text.setText(KEK_WORD);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public void onClickGetOrientationButton(View view) {
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            makeOrientationToastText(PORTRAIT_ORIENTATION_WORD_RUS);
        } else if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            makeOrientationToastText(LANDSCAPE_ORIENTATION_WORD_RUS);
        } else {
            makeOrientationToastText(UNKNOWN_ORIENTATION_WORD_RUS);
        }
    }

    public void onClickWorkWithDisplayButton(View view) {
        Intent workWithDisplayIntent = new Intent(MainActivity.this, WorkWithDisplayActivity.class);
        startActivity(workWithDisplayIntent);
    }

    public void onClickBeerInfoButton(View view) {
        Intent beerInfoIntent = new Intent(MainActivity.this, BeerInfoActivity.class);
        startActivity(beerInfoIntent);
    }

    public void onClickStopwatchButton(View view) {
        Intent stopwatchIntent = new Intent(MainActivity.this, StopwatchActivity.class);
        startActivity(stopwatchIntent);
    }

    private void makeOrientationToastText(String orientationType) {
        Toast.makeText(getApplicationContext(), "В данный момент " + orientationType + " ориентация", Toast.LENGTH_LONG).show();
    }
}
