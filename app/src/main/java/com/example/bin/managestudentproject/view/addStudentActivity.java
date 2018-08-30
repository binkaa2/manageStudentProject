package com.example.bin.managestudentproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bin.managestudentproject.R;
import com.example.bin.managestudentproject.bean.studentsBean;
import com.example.bin.managestudentproject.dao.adapterStudent;
import com.example.bin.managestudentproject.dao.studentsDao;
import com.example.bin.managestudentproject.listActivity;

import java.util.ArrayList;

public class addStudentActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private android.support.v7.widget.Toolbar toolbar;
    private NavigationView navigationView;
    private studentsDao studentsDao;
    private studentsBean studentsBean;
    private EditText editText_hoten;
    private EditText editText_lop;
    private EditText editText_chuyennganh;
    private EditText editText_sdt;

    public void addStudentsOnClick(View view){
        studentsDao = new studentsDao(addStudentActivity.this);
        studentsBean = new studentsBean();
        studentsBean.setHoten(editText_hoten.getText().toString());
        studentsBean.setLop(editText_lop.getText().toString());
        studentsBean.setChuyenNganh(editText_chuyennganh.getText().toString());
        studentsBean.setSdt(editText_sdt.getText().toString());
        studentsDao.addStudent(studentsBean);
        Toast.makeText(addStudentActivity.this, "ok", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_student);


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

        //
        editText_hoten = (EditText) findViewById(R.id.tv_add_hoten);
        editText_lop = (EditText) findViewById(R.id.tv_add_lop);
        editText_chuyennganh = (EditText) findViewById(R.id.tv_add_chuyennganh);
        editText_sdt = (EditText) findViewById(R.id.tv_add_sdt);


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

        }else if(id==R.id.nav_display_student){
            startActivity(new Intent(addStudentActivity.this, com.example.bin.managestudentproject.listActivity.class));
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
