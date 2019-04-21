package com.finalproject.loginlogouttest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class LoggedIn extends AppCompatActivity implements View.OnClickListener {
private Button btnLogout;
private TextView txtView;
SharedPreferences preferences;
SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        initItems();
    }

    private void initItems(){
        txtView = findViewById(R.id.txtView);
        btnLogout = findViewById(R.id.btnLogout);
        preferences = getSharedPreferences("LoginLogout", Context.MODE_PRIVATE);
        editor = preferences.edit();
        txtView.setText("Welcome, "+preferences.getString("user",""));
        btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogout:
                editor.remove("user")
                      .putBoolean("loginStatus",false).commit();
                Intent intent = new Intent(LoggedIn.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
        }
    }
}
