package com.example.bin.managestudentproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;a

import com.example.bin.managestudentproject.bean.usersBean;
import com.example.bin.managestudentproject.dao.SQLite;
import com.example.bin.managestudentproject.dao.studentsDao;

public class MainActivity extends AppCompatActivity {
    /*

     */
    private AutoCompleteTextView tv_username;
    private EditText tv_password;
    private SQLite db;

    public void loginOnClick(View view){
        String username = tv_username.getText().toString();
        String password = tv_password.getText().toString();
        if(db.checkLogin(username,password)) {
            Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
            /*Intent intent = new Intent(MainActivity.this, com.example.bin.managestudentproject.listActivity.class);
            startActivity(intent);*/
            startActivity(new Intent(MainActivity.this, com.example.bin.managestudentproject.listActivity.class).putExtra("username",username));
        }
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
        usersBean user = db.createDefaultUser();



    }


}
