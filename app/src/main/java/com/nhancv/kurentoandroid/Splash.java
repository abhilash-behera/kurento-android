package com.nhancv.kurentoandroid;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.nhancv.kurentoandroid.main.MainActivity;
import com.nhancv.kurentoandroid.main.MainActivity_;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(
                (ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED)
                ||(ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO)!=PackageManager.PERMISSION_GRANTED)
                ||(ContextCompat.checkSelfPermission(this,Manifest.permission.MODIFY_AUDIO_SETTINGS)!=PackageManager.PERMISSION_GRANTED)){

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO,Manifest.permission.MODIFY_AUDIO_SETTINGS},1);
        }else{
            Toast.makeText(Splash.this,"Nice!! All permissions granted",Toast.LENGTH_LONG).show();
            startApplication();
        }
    }

    public void startApplication(){
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity_.intent(Splash.this).start();
                finish();

            }
        },3000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED&&grantResults[1]==PackageManager.PERMISSION_GRANTED&&grantResults[2]==PackageManager.PERMISSION_GRANTED){
            Toast.makeText(Splash.this,"Thank you !! For granting all of the permissions",Toast.LENGTH_LONG).show();
            startApplication();
        }else{
            Toast.makeText(Splash.this,"You cannot run this application without the required permissions",Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
