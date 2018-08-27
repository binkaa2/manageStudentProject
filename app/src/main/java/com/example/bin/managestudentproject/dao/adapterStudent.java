package com.example.bin.managestudentproject.dao;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bin.managestudentproject.R;
import com.example.bin.managestudentproject.bean.studentsBean;
import com.example.bin.managestudentproject.bean.usersBean;

import java.util.ArrayList;

public class adapterStudent extends BaseAdapter {
    Context context;
    ArrayList<studentsBean> listStudents;

    public adapterStudent(Context context, ArrayList<studentsBean> listStudents) {
        this.context = context;
        this.listStudents = listStudents;
    }

    @Override
    public int getCount() {
        return listStudents.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //INFLATER
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.listview_student,null);
        TextView tv_hoten = (TextView) row.findViewById(R.id.tv_hoten);
        TextView tv_lop = (TextView) row.findViewById(R.id.tv_lop);
        TextView tv_chuyennganh = (TextView) row.findViewById(R.id.tv_chuyennganh);
        TextView tv_sdt = (TextView) row.findViewById(R.id.tv_sdt);
        ImageView iv_hinhdaidien = (ImageView) row.findViewById(R.id.im_hinhdaidien);

        studentsBean student = listStudents.get(position);
        tv_hoten.setText(student.getHoten());
        tv_lop.setText(student.getLop());
        tv_sdt.setText(student.getSdt());
        tv_chuyennganh.setText(student.getChuyenNganh());

       /* Bitmap bmHinhDaiDien = BitmapFactory.decodeByteArray(student.getAnhDaiDien(),0,student.getAnhDaiDien().length);
        iv_hinhdaidien.setImageBitmap(bmHinhDaiDien);*/

        return row;

    }
}
