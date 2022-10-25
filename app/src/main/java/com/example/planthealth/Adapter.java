package com.example.planthealth;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    public Adapter(List<Food> foods){this.foods = foods;}
    List<Food> foods;

    public void setFilteredList(List<Food> filteredList){
        this.foods = filteredList;
        notifyDataSetChanged();
    }

    public  Adapter(Context ctx, List<Food> foods) {
        this.inflater = LayoutInflater.from(ctx);
        this.foods = foods;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //bind the data
        holder.nameFood.setText(foods.get(position).getName());
        holder.unitFood.setText(foods.get(position).getUnit());
        holder.unitCal.setText(foods.get(position).getCalories());
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameFood, unitFood, unitCal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameFood = itemView.findViewById(R.id.nameFood);
            unitFood = itemView.findViewById(R.id.unitFood);
            unitCal = itemView.findViewById(R.id.unitCal);

        }
    }
}
