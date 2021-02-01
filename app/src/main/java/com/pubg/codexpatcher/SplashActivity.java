package com.pubg.codexpatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.animation.Animator;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

public class SplashActivity extends AppCompatActivity {

    private int accepted = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        if (getSharedPreferences("ota", MODE_PRIVATE).getBoolean("islogin", false)) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
            }.start();
        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("User agreement");
            dialog.setMessage("I am using this tool at my own risk & I will never blame the developer.\nTo ensure security , I will first test it on guest ID & and then on Main ID");
            dialog.setPositiveButton("Agree", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    accepted ++;
                    requestStoragePermission();
                    Toast.makeText(SplashActivity.this, "Made by @Rahil", Toast.LENGTH_SHORT).show();
                }
            }).setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finishAffinity();
                }
            }).create().show();
        }
    }

    private void requestStoragePermission() {
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        } else {
            if (accepted == 2){
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            } else {
                showKeyDialog();
                Toast.makeText(this, "Key not found", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        SharedPreferences sharedPreferences = getSharedPreferences("ota", MODE_PRIVATE);
        if (requestCode == 0) {
            String permission = permissions[0];
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                showKeyDialog();
            } else {
                boolean showAgain = ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this, permission);
                if (showAgain) {
                    ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    new AlertDialog.Builder(this)
                            .setTitle("Storage Permission needed")
                            .setMessage("This tool modifies PUBG configuration files , which needs storage permission to do so")
                            .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                                }
                            }).create().show();
                } else {
                    Toast.makeText(this, "Please Grant Storage Permission", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setData(Uri.fromParts("package", getPackageName(), null));
                    startActivityForResult(intent, 0);
                    finish();
                }
            }
        }
    }

    private void showKeyDialog(){
        SharedPreferences.Editor editor = getSharedPreferences("ota",MODE_PRIVATE).edit();
        EditText editText = new EditText(this);
        final AlertDialog builder = new AlertDialog.Builder(this).setTitle("Key").setView(editText).setPositiveButton("OK", null).setNegativeButton("Get key", null).setCancelable(false).create();
        builder.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                builder.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (editText.getText().toString().equals("@RahilMod")) {
                            accepted ++;
                            if (accepted == 2){
                                editor.putBoolean("islogin", true);
                                editor.apply();
                                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            } else {
                                Toast.makeText(SplashActivity.this, "Accept agreement first", Toast.LENGTH_SHORT).show();
                            }
                            finish();
                        } else {
                            editText.setError("Incorrect key !");
                            Toast.makeText(SplashActivity.this, "Get key from telegram", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SplashActivity.this, "Dm @ErrorXcode for key", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://t.me/ErrorXcode")));
                    }
                });
            }
        });
        builder.show();

    }
}