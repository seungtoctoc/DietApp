package com.example.dietapp5;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView itext_date, itext_menu, itext_kcal;

        MyViewHolder(View view) {
            super(view);

            itext_date = view.findViewById(R.id.itext_date);
            itext_menu = view.findViewById(R.id.itext_menu);
            itext_kcal = view.findViewById(R.id.itext_kcal);
        }
    }



    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;


        myViewHolder.itext_date.setText(MainActivity.mealList.get(position).getDate());

        myViewHolder.itext_menu.setText(MainActivity.mealList.get(position).getMenu());

        myViewHolder.itext_kcal.setText(MainActivity.mealList.get(position).getTotalKcal());




        myViewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, DetailActivity.class);

                intent.putExtra("position", position);

                context.startActivity(intent);


            } });

    }


    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return MainActivity.mealList.size();
    }

}
