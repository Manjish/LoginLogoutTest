package com.finalproject.loginlogouttest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private EditText txtUsername,txtPassword;
private Button btnLogin;
SharedPreferences preferences;
SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initItems();
    }

    private void initItems(){
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        preferences = getSharedPreferences("LoginLogout", Context.MODE_PRIVATE);
        editor = preferences.edit();
        if (preferences.getBoolean("loginStatus",false)){
            startActivity(new Intent(MainActivity.this,LoggedIn.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                if (username.equals("admin")&&password.equals("admin")){
                    editor.putString("user","admin").commit();
                    editor.putBoolean("loginStatus",true).commit();
                    Intent intent = new Intent(MainActivity.this,LoggedIn.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }
}
