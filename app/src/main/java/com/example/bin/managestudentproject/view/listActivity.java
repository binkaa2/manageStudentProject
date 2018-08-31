package com.example.bin.managestudentproject;

import android.app.Activity;
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
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bin.managestudentproject.bean.studentsBean;
import com.example.bin.managestudentproject.dao.SQLite;
import com.example.bin.managestudentproject.dao.adapterStudent;
import com.example.bin.managestudentproject.dao.studentsDao;

import java.util.ArrayList;

public class listActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private TextView tv_welcome;
    private ListView lv_student;
    private ArrayList<studentsBean> studentsList;
    private adapterStudent adapterStudent;
    private studentsDao studentDB;
    private DrawerLayout drawerLayout;
    private android.support.v7.widget.Toolbar toolbar;
    private NavigationView navigationView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);
        studentDB = new studentsDao(listActivity.this);
        studentDB.ganGiaTriMacDinh();
        addListView();



        //add drawer
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout  = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.opendraw, R.string.closedraw);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void addListView() {
        lv_student = (ListView) findViewById(R.id.lv_student);
        studentsList = studentDB.getAllStudents();
        adapterStudent = new adapterStudent(listActivity.this,studentsList);
        lv_student.setAdapter(adapterStudent);
    }



    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id== R.id.item_setting){
            //
        }
        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //item select go here
        int id = item.getItemId();

        if(id == R.id.nav_add_student){
            startActivity(new Intent(listActivity.this, com.example.bin.managestudentproject.view.addStudentActivity.class));
        }else if(id==R.id.nav_display_student){
            //
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
