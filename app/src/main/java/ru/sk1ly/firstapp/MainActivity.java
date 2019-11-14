package ru.sk1ly.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static ru.sk1ly.firstapp.AuthorizeActivity.USER_FIRST_NAME_KEY;
import static ru.sk1ly.firstapp.AuthorizeActivity.USER_SECOND_NAME_KEY;

public class MainActivity extends AppCompatActivity {

    private static final int AUTHORIZE_USER = 0;

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
//        TODO Не работает, разобраться
        Intent authorizeIntent = new Intent(MainActivity.this, AuthorizeActivity.class);
        startActivityForResult(authorizeIntent, AUTHORIZE_USER);
    }
}
