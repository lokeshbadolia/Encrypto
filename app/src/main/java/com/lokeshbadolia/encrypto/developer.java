package com.lokeshbadolia.encrypto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class developer extends AppCompatActivity {

    public TextView  btnEmail, btnGit;
    public ImageView btnBack;
    public Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnEmail = (TextView) findViewById(R.id.btnEmail);
        btnGit = (TextView) findViewById(R.id.btnGit);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.click_animate));
                finish();
            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.click_animate));
                Intent EmailIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "lokeshbadolia5@gmail.com"));
                EmailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                EmailIntent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(EmailIntent);
            }
        });

        btnGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.click_animate));
                Uri uri = Uri.parse("https://github.com/lokeshbadolia/");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

    }
}
