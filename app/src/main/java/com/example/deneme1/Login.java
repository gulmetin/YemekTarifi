package com.example.deneme1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText username;
    EditText password;
    Button giris;
    TextView txtUyeOl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        giris = findViewById(R.id.btnGiris);
        txtUyeOl = findViewById(R.id.uyeOlTxt);

        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB dataBase = new DB(Login.this);
                SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
                if (dataBase.loginControlByUsernameAndPassword(sqLiteDatabase,username.getText().toString(),password.getText().toString())){
                    int userId = dataBase.getUserId(sqLiteDatabase,username.getText().toString(),password.getText().toString());
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    intent.putExtra("userId",userId);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Login.this, "Böyle bir kullanıcı bulunamadı!", Toast.LENGTH_LONG).show();
                }
            }
        });

        txtUyeOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });


    }
}