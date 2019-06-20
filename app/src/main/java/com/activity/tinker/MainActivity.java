package com.activity.tinker;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tencent.tinker.lib.tinker.TinkerInstaller;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (checkSelfPermission(perms[0]) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(perms, 200);
            }
        }
    }

    public void fix(View view) {
        Toast.makeText(MainActivity.this, "开始修复", Toast.LENGTH_SHORT).show();
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch.apk";
        Log.i(TAG, "fix: path "+path);
        if(new File(path).exists()){
            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(),path);
        }else{
            Toast.makeText(MainActivity.this, "修复包不存在  搞什么啊?", Toast.LENGTH_SHORT).show();
        }

    }

    public void tos(View view) {
        Toast.makeText(MainActivity.this, "修复成功", Toast.LENGTH_SHORT).show();
    }
}
