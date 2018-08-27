package com.example.bin.managestudentproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bin.managestudentproject.dao.SQLite;

public class MainActivity extends AppCompatActivity {
    /*

     */
    private AutoCompleteTextView tv_username;
    private EditText tv_password;
    private SQLite db;

    public void loginOnClick(View view){
        String username = tv_username.getText().toString();
        String password = tv_password.getText().toString();
        if(db.checkLogin(username,password))
            Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
        if(TextUtils.isEmpty(password)){
            tv_password.setError("xin vui long nhap pass");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_username = (AutoCompleteTextView) findViewById(R.id.tv_username);
        tv_password = (EditText) findViewById(R.id.tv_password);
        db = new SQLite(MainActivity.this);
    }
}
