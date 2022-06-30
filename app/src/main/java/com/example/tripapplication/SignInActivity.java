package com.example.tripapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tripapplication.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {

    public static final String KEY_USER = "KEY";
    ActivitySignInBinding binding;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.edtName.length() == 0) {
                    binding.edtName.setError("Vui lòng nhập lại");
                }
                if (binding.edtPass.length() == 0) {
                    binding.edtPass.setError("Vui lòng nhập lại");
                } else {
                    if (checkDataSignin()) {

                        Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent();
                        i.setClass(SignInActivity.this, MainActivity.class);
                        i.putExtra(KEY_USER, binding.edtName.getText().toString());
                        startActivity(i);
                    } else
                        Toast.makeText(getApplicationContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    binding.edtName.getText().clear();
                    binding.edtPass.getText().clear();
                }
            }
        });

    }
    private boolean checkDataSignin() {
        db = openOrCreateDatabase("data.db", MODE_PRIVATE, null);

        String sql = "SELECT * FROM tbUser";

        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            if (binding.edtName.getText().toString().equals(cursor.getString(1)) && binding.edtPass.getText().toString().equals(cursor.getString(2)))
                return true;
        }
        return false;
    }
}