package com.example.tripapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tripapplication.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initData();

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                if (binding.edtName.length() < 5) {
                    binding.edtName.setError("Vui lòng nhập lại");
                }
                if (binding.edtPass.length() < 5) {
                    binding.edtPass.setError("Vui lòng nhập lại");
                }
                if (binding.edtAddress.length() == 0) {
                    binding.edtAddress.setError("Vui lòng nhập lại");
                }
                if (binding.edtPhone.length() < 9 || binding.edtPhone.length() > 11) {
                    binding.edtPhone.setError("Vui lòng nhập lại");
                } else {
                    if (checkData()) {
                        Toast.makeText(getApplicationContext(), "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                        binding.edtName.getText().clear();
                        binding.edtPass.getText().clear();
                        binding.edtAddress.getText().clear();
                        binding.edtPhone.getText().clear();

                    } else {
                        insertData();
                        Toast.makeText(SignUpActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(SignUpActivity.this, SignInActivity.class);
                        startActivity(i);
                    }
                }
            }
        });
    }

    private void initData() {
        db = openOrCreateDatabase("data.db", MODE_PRIVATE, null);

        String sql = " CREATE TABLE IF NOT EXISTS tbUser (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, PASSWORD TEXT, ADDRESS TEXT, PHONE TEXT)";
//        String sql1 = " CREATE TABLE IF NOT EXISTS tbRecentsData (ID INTEGER PRIMARY KEY AUTOINCREMENT, PlacesName TEXT, CountryName TEXT, Price TEXT, Status TEXT, Image TEXT)";
        db.execSQL(sql);
//        db.execSQL(sql1);
    }

    private boolean checkData() {

        String sql = "SELECT * FROM tbUser";
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            if (binding.edtName.getText().toString().equals(cursor.getString(1)))
                return true;
        }
        return false;
    }

    private void insertData() {
        String NAME = binding.edtName.getText().toString();
        String PASSWORD = binding.edtPass.getText().toString();
        String ADDRESS = binding.edtAddress.getText().toString();
        String PHONE = binding.edtPhone.getText().toString();        String sql = " INSERT INTO tbUser (NAME, PASSWORD, ADDRESS, PHONE) VALUES ('" + NAME + "','" + PASSWORD + "','" + ADDRESS + "','" + PHONE + "')";

        db.execSQL(sql);
    }
}