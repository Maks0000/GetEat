package com.maksymb.geteat;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class FoodCategoriesAd  extends RecyclerView.Adapter<FoodCategoriesAd.Category_holder>{

    List<Foodcategories> data;
    Context context;
    int selectedItem = 0;
    OnCategoryClick onCategoryClick;

    public interface OnCategoryClick {
        void onClick(int pos);

    }

    public  FoodCategoriesAd(List<Foodcategories> data, Context context, OnCategoryClick onCategoryClick){
        this.data = data;
        this.context = context;
        this.onCategoryClick = onCategoryClick;

    }


    @NonNull
    @Override
    public Category_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.categoty_holder,parent,false);
        return new Category_holder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull Category_holder holder, int position) {
        holder.image.setImageResource(data.get(position).getImage());
        holder.title.setText(data.get(position).getName());
        if (position == selectedItem){
            //make card selected
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                holder.cardView.setOutlineSpotShadowColor(context.getColor(R.color.green));
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                holder.cardView.setOutlineAmbientShadowColor(context.getColor(R.color.green));
            }
            holder.cardView.setStrokeWidth(2);
            holder.title.setTextColor(context.getColor(R.color.green));
            holder.image.setColorFilter(ContextCompat.getColor(context,R.color.green), PorterDuff.Mode.SRC_IN);
        }else {
            // make card inactive
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                holder.cardView.setOutlineSpotShadowColor(context.getColor(R.color.gray));
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                holder.cardView.setOutlineAmbientShadowColor(context.getColor(R.color.gray));
            }
            holder.title.setTextColor(context.getColor(R.color.gray));
            holder.image.setColorFilter(ContextCompat.getColor(context,R.color.gray), PorterDuff.Mode.SRC_IN);
            holder.cardView.setStrokeWidth(2);

        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Category_holder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView image;
        MaterialCardView cardView;
        public Category_holder(View holder){
            super(holder);
            title = holder.findViewById(R.id.txt_title);
            image = holder.findViewById(R.id.img);
            cardView = holder.findViewById(R.id.card_view);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedItem = getAdapterPosition();
                    //reset items, so that color changes when we click on card


                    if(onCategoryClick != null){
                        onCategoryClick.onClick(getAdapterPosition());

                    }
                    notifyDataSetChanged();

                }
            });
        }



    }
}
