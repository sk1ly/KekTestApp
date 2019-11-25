package ru.sk1ly.firstapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static ru.sk1ly.firstapp.AuthorizeActivity.USER_FIRST_NAME_KEY;
import static ru.sk1ly.firstapp.AuthorizeActivity.USER_SECOND_NAME_KEY;

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
        mUserFirstName = findViewById(R.id.userFirstName);
        mUserSecondName = findViewById(R.id.userSecondName);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AUTHORIZE_USER && resultCode == RESULT_OK) {
//            TODO Проверки на null
            mUserFirstName.setText(data.getStringExtra(USER_FIRST_NAME_KEY));
            mUserSecondName.setText(data.getStringExtra(USER_SECOND_NAME_KEY));
        }
    }

    public void onClickAuthorizeButton(View view) {
        Intent authorizeIntent = new Intent(MainActivity.this, AuthorizeActivity.class);
        startActivityForResult(authorizeIntent, AUTHORIZE_USER);
    }

    public void onClickSayKekButton(View view) {
        Toast.makeText(this, KEK_WORD, Toast.LENGTH_SHORT).show();
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

    public void onClickAboutAppButton(View view) {
        //        TODO
        Toast.makeText(this, "TODO", Toast.LENGTH_SHORT).show();
    }

    private void makeOrientationToastText(String orientationType) {
        Toast.makeText(this, "В данный момент " + orientationType + " ориентация", Toast.LENGTH_LONG).show();
    }
}
