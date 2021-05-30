package com.example.deneme1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    RecyclerView rcView;
    ImageButton button;
    ImageButton listFavs;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //firebase bildirim
        Intent intentBackgroundService = new Intent(this, FirebaseBildirim.class);
        startService(intentBackgroundService);

        Intent intent = getIntent();
        int userId = intent.getIntExtra("userId", 0);

        button=findViewById(R.id.newFood);  //butonun ıd si newFood. Ona başvuru yapıldı
        rcView=findViewById(R.id.recyclerView);
        listFavs = findViewById(R.id.listfavs);

        DB dataBase=new DB(MainActivity.this);
        SQLiteDatabase sqLiteDatabase=dataBase.getReadableDatabase();
        RecyclerViewAdapter rcAdapter=new RecyclerViewAdapter(dataBase.listAllFood(sqLiteDatabase,userId));
        rcView.setAdapter(rcAdapter);
        rcView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        button.setOnClickListener(new View.OnClickListener() {//ekleme butonunda basıldığında insertUpdate de gidecek.
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,InsertUpdate.class);
                intent.putExtra("event","insert");
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });

        listFavs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ListFav.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });

    }
}