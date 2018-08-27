package com.example.bin.managestudentproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
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

public class listActivity extends AppCompatActivity {

    private TextView tv_welcome;
    private ListView lv_student;
    private ArrayList<studentsBean> studentsList;
    private adapterStudent adapterStudent;
    private studentsDao studentDB;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);
        tv_welcome = (TextView) findViewById(R.id.tv_welcome);
        tv_welcome.setText("Welcome back : "+ getIntent().getStringExtra("username"));
        studentDB = new studentsDao(listActivity.this);

        studentDB.ganGiaTriMacDinh();
        addListView();
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
    }
}
