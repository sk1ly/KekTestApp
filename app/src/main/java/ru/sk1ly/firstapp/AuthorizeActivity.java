package ru.sk1ly.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static ru.sk1ly.firstapp.Constants.*;

public class AuthorizeActivity extends AppCompatActivity {

    public EditText mUserFirstName;
    public EditText mUserSecondName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorize);
        mUserFirstName = findViewById(R.id.input_first_user_name);
        mUserSecondName = findViewById(R.id.input_second_user_name);
    }

    public void onClickAuthorizeCompleteButton(View view) {
        Intent answerIntent = new Intent();
        if (mUserFirstName.getText().toString().isEmpty() ||
                mUserSecondName.getText().toString().isEmpty()) {
            Toast.makeText(this,
                    "Необходимо заполнить два поля! Вы не были авторизованы", Toast.LENGTH_LONG).show();
            setResult(RESULT_CANCELED, answerIntent);
        } else {
            answerIntent.putExtra(Keys.USER_FIRST_NAME_ANSWER, mUserFirstName.getText().toString());
            answerIntent.putExtra(Keys.USER_SECOND_NAME_ANSWER, mUserSecondName.getText().toString());
            setResult(RESULT_OK, answerIntent);
        }
        finish();
    }

}
