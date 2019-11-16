package ru.sk1ly.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AuthorizeActivity extends AppCompatActivity {

    public static final String USER_FIRST_NAME_KEY = "ru.sk1ly.firstapp.USER_FIRST_NAME";
    public static final String USER_SECOND_NAME_KEY = "ru.sk1ly.firstapp.USER_SECOND_NAME";

    public EditText mUserFirstName;
    public EditText mUserSecondName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorize);
        mUserFirstName = findViewById(R.id.inputFirstUserName);
        mUserSecondName = findViewById(R.id.inputSecondUserName);
    }

    public void onClickAuthorizeCompleteButton(View view) {
        Intent answerIntent = new Intent();
        answerIntent.putExtra(USER_FIRST_NAME_KEY, mUserFirstName.getText().toString());
        answerIntent.putExtra(USER_SECOND_NAME_KEY, mUserSecondName.getText().toString());
        setResult(RESULT_OK, answerIntent);
        finish();
    }

}
