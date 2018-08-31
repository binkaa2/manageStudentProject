package com.example.bin.managestudentproject.dao;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bin.managestudentproject.R;
import com.example.bin.managestudentproject.bean.studentsBean;
import com.example.bin.managestudentproject.bean.usersBean;
import com.example.bin.managestudentproject.listActivity;

import java.util.ArrayList;

public class    adapterStudent extends BaseAdapter {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        //INFLATER
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        Button btnSua = (Button) row.findViewById(R.id.btn_sua);
        Button btnXoa = (Button) row.findViewById(R.id.btn_xoa);

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure to delete this ??");
                builder.setCancelable(true);

                builder.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                studentsDao dao = new studentsDao(context);
                                dao.deleteStudents(listStudents.get(position).getId());
                                Intent intent = new Intent(context,listActivity.class);
                                Toast.makeText(context, "Đã xóa thành công", Toast.LENGTH_SHORT).show();
                                context.startActivity(intent);
                            }
                        }
                );
                builder.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }
                );
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


       /* Bitmap bmHinhDaiDien = BitmapFactory.decodeByteArray(student.getAnhDaiDien(),0,student.getAnhDaiDien().length);
        iv_hinhdaidien.setImageBitmap(bmHinhDaiDien);*/

        return row;

    }
}
