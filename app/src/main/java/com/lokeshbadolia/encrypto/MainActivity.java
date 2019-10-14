package com.lokeshbadolia.encrypto;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {


    private boolean doubleBackToExitPressedOnce = false;

    public String res = null, input = null,input2 = null;
    public EditText blank;
    public TextView blank2;
    public TextView btn, btnCopy;
    public Context context = this;
    public ImageView searchBtn,menu;

    public EditText blank3;
    public TextView blank4,btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        btn = (TextView) findViewById(R.id.btn);
        btnCopy = (TextView) findViewById(R.id.btnCopy);
        blank = (EditText) findViewById(R.id.blank);
        blank2 = (TextView) findViewById(R.id.blank2);

        searchBtn = (ImageView) findViewById(R.id.searchBtn);
        menu = (ImageView) findViewById(R.id.menu);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.click_animate));
                Intent intent = new Intent(MainActivity.this,developer.class);
                startActivity(intent);

            }
        });



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.click_animate));
                input = blank.getText().toString();
                encrypt(input);
                //decrypt(input);
                blank2.setText(res);
            }
        });

        btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.click_animate));
                String code = blank2.getText().toString().trim();
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Coded Text", code);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(context,"Text Copied",Toast.LENGTH_SHORT).show();
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.click_animate));
                showBottomSheetDialog();
            }
        });

    }

    public void encrypt(String input) {
        char[] arr = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '#', '$', '@', ' ', '.', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char[] logicArray = {'I', 'q', 'b', 'P', '2', 'e', 'p', 'A', '#', 'm', 'U', '4', 'Z', 'j', '@', 'C', 'd', 'Q', '9', 'h', '$', 's', '5', 'J', 'g', 'Y', '3', 'z', 'E', 'n', 'L', 'H', '&', 'y', 'W', '1', 'r', 'M', 'u', '8', 'V', 'v', 'B', '0', 'O', 'a', 't', '%', '7', 'K', 'i', 'R', 'c', 'T', '6', 'x', 'D', 'w', 'f', 'X', 'G', 'o', 'S', 'l', 'F', 'k', 'N'};

        char[] str = input.toCharArray();

        int refStart = 0;
        int refEnd = 0;

        char[] newStr = new char[str.length];
        int newStrCount = 0;

        int[] pattern = {4, 2, 5, 3, 1};


        for (int i = 0; i < str.length; i++) {

            int patternCounter = 0;

            if (i < pattern.length) {
                patternCounter = i;
            } else {
                patternCounter = i % 5;
            }

            if (str[i] >= '0' && str[i] <= '9') {
                refStart = 0;
                refEnd = 10;
            } else if (str[i] >= 'a' && str[i] <= 'z') {
                refStart = 10;
                refEnd = 36;
            } else if (str[i] >= 'A' && str[i] <= 'Z') {
                refStart = 41;
                refEnd = 66;
            } else {
                refStart = 36;
                refEnd = 41;
            }

            for (int j = refStart; j < refEnd; j++) {

                if (arr[j] == str[i]) {

                    int originalPos = j;// + 1;
                    int newPos = originalPos + pattern[patternCounter];

                    int maxLogicArrayCounter = 0;

                    if (newPos <= logicArray.length) {
                        maxLogicArrayCounter = newPos;
                    } else {
                        int remaining = newPos - logicArray.length;
                        maxLogicArrayCounter = 0 + remaining;
                    }

                    char newVar = logicArray[maxLogicArrayCounter];
                    newStr[newStrCount] = newVar;
                    newStrCount++;
                    break;
                }
            }

        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < newStr.length; i++) {
            res = sb.append(newStr[i] + "").toString();
        }

    }

    public void decrypt(String input) {
        char[] arr = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '#', '$', '@', ' ', '.', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char[] logicArray = {'I', 'q', 'b', 'P', '2', 'e', 'p', 'A', '#', 'm', 'U', '4', 'Z', 'j', '@', 'C', 'd', 'Q', '9', 'h', '$', 's', '5', 'J', 'g', 'Y', '3', 'z', 'E', 'n', 'L', 'H', '&', 'y', 'W', '1', 'r', 'M', 'u', '8', 'V', 'v', 'B', '0', 'O', 'a', 't', '%', '7', 'K', 'i', 'R', 'c', 'T', '6', 'x', 'D', 'w', 'f', 'X', 'G', 'o', 'S', 'l', 'F', 'k', 'N'};
        char[] str = input.toCharArray();
        char[] newStr = new char[str.length];

        int newStrCount = 0;

        int[] pattern = {4, 2, 5, 3, 1};


        for (int i = 0; i < str.length; i++) {

            int patternCounter = 0;

            if (i < pattern.length) {
                patternCounter = i;
            } else {
                patternCounter = i % 5;
            }


            for (int j = 0; j < logicArray.length; j++) {
                if (logicArray[j] == str[i]) {
                    int foundPos = j;// + 1;
                    int newPos = foundPos - pattern[patternCounter];

                    int maxLogicArrayCounter = 0;

                    if (newPos >= 0) {
                        maxLogicArrayCounter = newPos;
                    } else {
                        maxLogicArrayCounter = arr.length + newPos;
                    }

                    char newVar = arr[maxLogicArrayCounter];
                    newStr[newStrCount] = newVar;
                    newStrCount++;
                    break;
                }
            }

        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < newStr.length; i++) {
            res = sb.append(newStr[i] + "").toString();
        }

    }

    public void showBottomSheetDialog() {
        View view = getLayoutInflater().inflate(R.layout.decrypt_bottomsheet, null);
        final BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(view);
        dialog.show();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setWhiteNavigationBar(dialog);
        }

        blank3 = (EditText) view.findViewById(R.id.blank3);
        blank4 = (TextView) view.findViewById(R.id.blank4);
        btn2 = (TextView) view.findViewById(R.id.btn2);


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.click_animate));
                input2 = blank3.getText().toString();
                decrypt(input2);
                blank4.setText(res);

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setWhiteNavigationBar(@NonNull Dialog dialog) {
        Window window = dialog.getWindow();
        if (window != null) {
            DisplayMetrics metrics = new DisplayMetrics();
            window.getWindowManager().getDefaultDisplay().getMetrics(metrics);

            GradientDrawable dimDrawable = new GradientDrawable();
            // ...customize your dim effect here

            GradientDrawable navigationBarDrawable = new GradientDrawable();
            navigationBarDrawable.setShape(GradientDrawable.RECTANGLE);
            navigationBarDrawable.setColor(Color.WHITE);

            Drawable[] layers = {dimDrawable, navigationBarDrawable};

            LayerDrawable windowBackground = new LayerDrawable(layers);
            windowBackground.setLayerInsetTop(1, metrics.heightPixels);

            window.setBackgroundDrawable(windowBackground);
        }
    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            //super.onBackPressed();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
            startActivity(intent);
            finish();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Click again to Exit.", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}



/*
 public void encrypt(String input) {
        char[] arr = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '#', '$', '@', ' ', '.', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char[] logicArray = {'I', 'q', 'b', 'P', '2', 'e', 'p', 'A', '#', 'm', 'U', '4', 'Z', 'j', '@', 'C', 'd', 'Q', '9', 'h', '$', 's', '5', 'J', 'g', 'Y', '3', 'z', 'E', 'n', 'L', 'H', '&', 'y', 'W', '1', 'r', 'M', 'u', '8', 'V', 'v', 'B', '0', 'O', 'a', 't', '%', '7', 'K', 'i', 'R', 'c', 'T', '6', 'x', 'D', 'w', 'f', 'X', 'G', 'o', 'S', 'l', 'F', 'k', 'N'};

        char[] str = input.toCharArray();

        int refStart = 0;
        int refEnd = 0;

        char[] newStr = new char[str.length];
        int newStrCount = 0;

        int[] pattern = {4, 2, 5, 3, 1};


        for (int i = 0; i < str.length; i++) {

            int patternCounter = 0;

            if (i < pattern.length) {
                patternCounter = i;
            } else {
                patternCounter = i % 5;
            }

            if (str[i] >= '0' && str[i] <= '9') {
                refStart = 0;
                refEnd = 10;
            } else if (str[i] >= 'a' && str[i] <= 'z') {
                refStart = 10;
                refEnd = 36;
            } else if (str[i] >= 'A' && str[i] <= 'Z') {
                refStart = 41;
                refEnd = 66;
            } else {
                refStart = 36;
                refEnd = 41;
            }

            for (int j = refStart; j < refEnd; j++) {

                if (arr[j] == str[i]) {

                    int originalPos = j;// + 1;
                    int newPos = originalPos + pattern[patternCounter];

                    int maxLogicArrayCounter = 0;

                    if (newPos <= logicArray.length) {
                        maxLogicArrayCounter = newPos;
                    } else {
                        int remaining = newPos - logicArray.length;
                        maxLogicArrayCounter = 0 + remaining;
                    }

                    char newVar = logicArray[maxLogicArrayCounter];
                    newStr[newStrCount] = newVar;
                    newStrCount++;
                    break;
                }
            }

        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < newStr.length; i++) {
            res = sb.append(newStr[i] + "").toString();
        }

    }

    public void decrypt(String input) {
        char[] arr = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '#', '$', '@', ' ', '.', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char[] logicArray = {'I', 'q', 'b', 'P', '2', 'e', 'p', 'A', '#', 'm', 'U', '4', 'Z', 'j', '@', 'C', 'd', 'Q', '9', 'h', '$', 's', '5', 'J', 'g', 'Y', '3', 'z', 'E', 'n', 'L', 'H', '&', 'y', 'W', '1', 'r', 'M', 'u', '8', 'V', 'v', 'B', '0', 'O', 'a', 't', '%', '7', 'K', 'i', 'R', 'c', 'T', '6', 'x', 'D', 'w', 'f', 'X', 'G', 'o', 'S', 'l', 'F', 'k', 'N'};
        char[] str = input.toCharArray();
        char[] newStr = new char[str.length];

        int newStrCount = 0;

        int[] pattern = {4, 2, 5, 3, 1};


        for (int i = 0; i < str.length; i++) {

            int patternCounter = 0;

            if (i < pattern.length) {
                patternCounter = i;
            } else {
                patternCounter = i % 5;
            }


            for (int j = 0; j < logicArray.length; j++) {
                if (logicArray[j] == str[i]) {
                    int foundPos = j;// + 1;
                    int newPos = foundPos - pattern[patternCounter];

                    int maxLogicArrayCounter = 0;

                    if (newPos >= 0) {
                        maxLogicArrayCounter = newPos;
                    } else {
                        maxLogicArrayCounter = arr.length + newPos;
                    }

                    char newVar = arr[maxLogicArrayCounter];
                    newStr[newStrCount] = newVar;
                    newStrCount++;
                    break;
                }
            }

        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < newStr.length; i++) {
            res = sb.append(newStr[i] + "").toString();
        }

    }

*/