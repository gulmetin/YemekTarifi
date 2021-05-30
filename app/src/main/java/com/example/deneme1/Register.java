package com.example.deneme1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class Register extends AppCompatActivity {
    EditText username;
    EditText email;
    EditText password;
    Button kaydol;
    TextView txtGiris;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.nameKullanici);
        email = findViewById(R.id.email);
        password = findViewById(R.id.sifre);
        kaydol = findViewById(R.id.btnKaydol);
        txtGiris = findViewById(R.id.girisYapTxt);

        kaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().length() > 0 && email.getText().toString().length() > 0 && password.getText().toString().length() > 0){
                    DB dataBase = new DB(Register.this);
                    SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
                    if (dataBase.loginControlByUsernameAndPassword(sqLiteDatabase,username.getText().toString(),password.getText().toString())){
                        Toast.makeText(Register.this, "Aynı kullanıcı adına sahip hesap mevcut!", Toast.LENGTH_LONG).show();
                    }
                    else{
                        User user = new User();
                        user.setUsername(username.getText().toString());
                        user.setEmail(email.getText().toString());
                        user.setPassword(password.getText().toString());
                        dataBase.insertUser(sqLiteDatabase, user);
                        Toast.makeText(Register.this, "Kaydedildi!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Register.this, Login.class);
                        startActivity(intent);
                    }

                }
                else{
                    Toast.makeText(Register.this, "Boş Alan Bırakmayınız!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        txtGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });

    }
}