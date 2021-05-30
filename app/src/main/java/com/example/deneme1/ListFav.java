package com.example.deneme1;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageButton;

public class ListFav extends AppCompatActivity {
    RecyclerView rcView;
    ImageButton backBtn;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);

        Intent intent = getIntent();
         userId = intent.getIntExtra("userId", 0);

        rcView=findViewById(R.id.recyclerView);
        backBtn=findViewById(R.id.backBtn);


        DB dataBase=new DB(ListFav.this);
        SQLiteDatabase sqLiteDatabase=dataBase.getReadableDatabase();
        RecyclerViewAdapter rcAdapter=new RecyclerViewAdapter(dataBase.listFavFood(sqLiteDatabase,userId));
        rcView.setAdapter(rcAdapter);
        rcView.setLayoutManager(new LinearLayoutManager(ListFav.this));

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ListFav.this,MainActivity.class);
                intent.putExtra("event","insert");
                startActivity(intent);
            }
        });
    }
    public void updateFav(int foodid,int fav){
        DB dataBase=new DB(ListFav.this);
        SQLiteDatabase sqLiteDatabase=dataBase.getWritableDatabase();
        Food food;
        food = dataBase.findFoodById(sqLiteDatabase, Integer.parseInt(getIntent().getStringExtra("foodId")),userId);
        food.setFav(fav);
    }
}


