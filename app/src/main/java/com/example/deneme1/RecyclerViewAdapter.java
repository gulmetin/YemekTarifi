package com.example.deneme1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    static List<Food> foodList;
    static Context context;

    public RecyclerViewAdapter(List<Food> _foodList){
        foodList = _foodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        byte[] bytes = foodList.get(position).getImage();
        Bitmap image = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        holder.foodImage.setImageBitmap(image);
        holder.foodName.setText(foodList.get(position).getName());
        if(foodList.get(position).getFav()==1){
            holder.favButton.setBackgroundResource(R.drawable.fav_red_btn);
        }
        else{
            holder.favButton.setBackgroundResource(R.drawable.fav_btn);
        }
        holder.rcView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,InsertUpdate.class);
                intent.putExtra("event","update");
                intent.putExtra("foodId",(foodList.get(position).getId()+""));
                context.startActivity(intent);
            }
        });
        holder.favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int foodid = foodList.get(position).getId();
                ListFav listfav = new ListFav();
                if(foodList.get(position).getFav()==1){
                    //foodList.get(position).setFav(0);
                    listfav.updateFav(foodid,0);
                    holder.favButton.setBackgroundResource(R.drawable.fav_btn);
                }
                else if(foodList.get(position).getFav()==0){
                    //foodList.get(position).setFav(1);
                    listfav.updateFav(foodid,1);
                    holder.favButton.setBackgroundResource(R.drawable.fav_red_btn);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout rcView;
        ImageView foodImage;
        TextView foodName;
        Button favButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rcView = itemView.findViewById(R.id.listItemRCView);
            foodName = itemView.findViewById(R.id.listItemName);
            foodImage = itemView.findViewById(R.id.listItemImage);
            favButton = itemView.findViewById(R.id.favBtn);
        }
    }
}