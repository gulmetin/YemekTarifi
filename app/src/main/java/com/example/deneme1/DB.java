package com.example.deneme1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DB extends SQLiteOpenHelper {

    private static final String DB_NAME = "database";
    private static final int VERSION =1;
    private static String[] foodColumns = {"id","name","ingredients","recipe","image","fav","userId"};
    private static String[] userColumns = {"id","username","email","password"};

    public DB(Context context){
        super(context,DB_NAME,null,VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(userId INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT,email TEXT,password TEXT)");
        db.execSQL("CREATE TABLE food(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,ingredients TEXT,recipe TEXT, image BLOB,fav INTEGER, userId INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public List<Food> listAllFood(SQLiteDatabase db, int userId) throws SQLException{
        List<Food> foodList = new ArrayList<>();
        Cursor cursor = db.query("food",null,null,null,null,null,null);
        while (cursor.moveToNext()){
            Food food = new Food();
            if(cursor.getInt((cursor.getColumnIndex(foodColumns[6])))==userId){
                food.setId(cursor.getInt((cursor.getColumnIndex(foodColumns[0]))));
                food.setName(cursor.getString(cursor.getColumnIndex(foodColumns[1])));
                food.setIngredients(cursor.getString(cursor.getColumnIndex(foodColumns[2])));
                food.setRecipe(cursor.getString(cursor.getColumnIndex(foodColumns[3])));
                food.setImage(cursor.getBlob(cursor.getColumnIndex(foodColumns[4])));
                food.setFav(cursor.getInt((cursor.getColumnIndex(foodColumns[5]))));
                foodList.add(food);
            }

        }
        return foodList;
    }
    public List<Food> listFavFood(SQLiteDatabase db,int userId) throws SQLException{
        List<Food> foodList = new ArrayList<>();
        Cursor cursor = db.query("food",null,null,null,null,null,null);
        while (cursor.moveToNext()){
            Food food = new Food();
            if(cursor.getInt((cursor.getColumnIndex(foodColumns[6])))==userId){
                if(cursor.getInt((cursor.getColumnIndex(foodColumns[5])))==1){
                    food.setId(cursor.getInt((cursor.getColumnIndex(foodColumns[0]))));
                    food.setName(cursor.getString(cursor.getColumnIndex(foodColumns[1])));
                    food.setIngredients(cursor.getString(cursor.getColumnIndex(foodColumns[2])));
                    food.setRecipe(cursor.getString(cursor.getColumnIndex(foodColumns[3])));
                    food.setImage(cursor.getBlob(cursor.getColumnIndex(foodColumns[4])));
                    food.setFav(cursor.getInt((cursor.getColumnIndex(foodColumns[5]))));
                    foodList.add(food);
                }
            }

        }
        return foodList;
    }

    public Food findFoodById(SQLiteDatabase db,int id,int userId) throws SQLException{
        Cursor cursor = db.query("food",null,null,null,null,null,null);
        Food food = new Food();
        while (cursor.moveToNext()){
            if(cursor.getInt((cursor.getColumnIndex(foodColumns[6])))==userId){
                if(cursor.getInt((cursor.getColumnIndex(foodColumns[0])))==id) {
                    food.setId(cursor.getInt((cursor.getColumnIndex(foodColumns[0]))));
                    food.setName(cursor.getString(cursor.getColumnIndex(foodColumns[1])));
                    food.setIngredients(cursor.getString(cursor.getColumnIndex(foodColumns[2])));
                    food.setRecipe(cursor.getString(cursor.getColumnIndex(foodColumns[3])));
                    food.setImage(cursor.getBlob(cursor.getColumnIndex(foodColumns[4])));
                    food.setFav(cursor.getInt(cursor.getColumnIndex(foodColumns[5])));
                    return food;
                }
            }

        }
        return null;
    }
    public boolean loginControlByUsernameAndPassword(SQLiteDatabase db,String username, String password) throws SQLException{
        Cursor cursor = db.query("user",null,null,null,null,null,null);
        while (cursor.moveToNext()){
            if(cursor.getString((cursor.getColumnIndex(userColumns[1]))).equals(username) && cursor.getString((cursor.getColumnIndex(userColumns[3]))).equals(password)) {
                return true;
            }
        }
        return false;
    }
    public int getUserId(SQLiteDatabase db,String username, String password) throws SQLException{
        Cursor cursor = db.query("user",null,null,null,null,null,null);
        while (cursor.moveToNext()){
            if(cursor.getString((cursor.getColumnIndex(userColumns[1]))).equals(username) && cursor.getString((cursor.getColumnIndex(userColumns[3]))).equals(password)) {
                int b=cursor.getInt(cursor.getColumnIndex(userColumns[0]));
                return b;
            }
        }
        return 0;
    }


    public void insertFood(SQLiteDatabase db,Food food) throws SQLException{
        ContentValues contentValues=new ContentValues();
        contentValues.put(foodColumns[1],food.getName());
        contentValues.put(foodColumns[2],food.getIngredients());
        contentValues.put(foodColumns[3],food.getRecipe());
        contentValues.put(foodColumns[4],food.getImage());
        contentValues.put(foodColumns[5],food.getFav());
        contentValues.put(foodColumns[6],food.getUserId());
        db.insertOrThrow("food",null,contentValues);
    }
    public void insertUser(SQLiteDatabase db,User user) throws SQLException{
        ContentValues contentValues=new ContentValues();
        contentValues.put(userColumns[1],user.getUsername());
        contentValues.put(userColumns[2],user.getEmail());
        contentValues.put(userColumns[3],user.getPassword());
        db.insertOrThrow("user",null,contentValues);
    }

    public void update(SQLiteDatabase db,Food food) throws SQLException{
        ContentValues contentValues=new ContentValues();
        contentValues.put(foodColumns[1],food.getName());
        contentValues.put(foodColumns[2],food.getIngredients());
        contentValues.put(foodColumns[3],food.getRecipe());
        contentValues.put(foodColumns[4],food.getImage());
        db.update("food",contentValues,"id="+food.getId(),null);
    }

    public void delete(SQLiteDatabase db,int id) throws SQLException{
        db.delete("food","id="+id,null);
    }



}
//listeleme,insert,update,delete,id ile getirme,