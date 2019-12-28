package ru.sk1ly.firstapp;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    private TextView mAboutAppTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        mAboutAppTextView = findViewById(R.id.about_app_text);
        mAboutAppTextView.setText(getString(R.string.about_app_text, getPackageInfo().versionName));
    }

    private PackageInfo getPackageInfo() {
        PackageInfo packageInfo;
        try {
            packageInfo = this.getPackageManager().getPackageInfo(
                    this.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
        return packageInfo;
    }
}
