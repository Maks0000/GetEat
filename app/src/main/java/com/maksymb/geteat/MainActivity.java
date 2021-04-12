
package com.maksymb.geteat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerCategories;
    RecyclerView recyclerItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerCategories = findViewById(R.id.recycler_categories);
        recyclerItems = findViewById(R.id.recycler_food);
        setCategories();
        setFoodItem(0);
    }

    private void setCategories(){
        List<Foodcategories> data = new ArrayList<>();

        Foodcategories fc1 = new Foodcategories("Vegatable",R.drawable.vege);
        Foodcategories fc2 = new Foodcategories("Meat",R.drawable.meat);
        Foodcategories fc3 = new Foodcategories("Juice&Water",R.drawable.water33);
        Foodcategories fc4 = new Foodcategories("Chocolate",R.drawable.ice);

        data.add(fc1);
        data.add(fc2);
        data.add(fc3);
        data.add(fc4);

        FoodCategoriesAd foodCategoriesAd = new FoodCategoriesAd(data, MainActivity.this, pos -> setFoodItem(pos));

        recyclerCategories.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL,false));
        recyclerCategories.setAdapter(foodCategoriesAd);
        foodCategoriesAd.notifyDataSetChanged();
    }

        private void setFoodItem(int pos){
            List<FoodItem> foodItems = new ArrayList<>();
        switch (pos) {
            case 2:
                FoodItem foodItem1 = new FoodItem("Sprite 0,5l",14,R.drawable.sprite);
                FoodItem foodItem2 = new FoodItem("Cola zero 0,5l",14,R.drawable.colazero);
                FoodItem foodItem3 = new FoodItem("Juice 0,5l",14,R.drawable.juice);
                FoodItem foodItem4 = new FoodItem("Water 5l",14,R.drawable.water5);

                foodItems.add(foodItem1);
                foodItems.add(foodItem2);
                foodItems.add(foodItem3);
                foodItems.add(foodItem4);
                break;
            case 1:
                FoodItem foodItem5 = new FoodItem("meat",14,R.drawable.meat0);
                FoodItem foodItem6 = new FoodItem("chicken",14,R.drawable.chiken);
                FoodItem foodItem7 = new FoodItem("fish",14,R.drawable.fish);
                FoodItem foodItem8 = new FoodItem("grilled chicken leg",14,R.drawable.grilledchickenleg);

                foodItems.add(foodItem5);
                foodItems.add(foodItem6);
                foodItems.add(foodItem7);
                foodItems.add(foodItem8);
                break;
            case 0:
                FoodItem foodItem9 = new FoodItem("Carrot",14,R.drawable.carrot);
                FoodItem foodItem10 = new FoodItem("Cucumber",14,R.drawable.cucumber);
                FoodItem foodItem11 = new FoodItem("Tomatoes",14,R.drawable.pomidor);
                FoodItem foodItem12 = new FoodItem("Potato",14,R.drawable.potato);

                foodItems.add(foodItem9);
                foodItems.add(foodItem10);
                foodItems.add(foodItem11);
                foodItems.add(foodItem12);
                break;
        }


        FoodAdapter foodAdapter = new FoodAdapter(foodItems);
        recyclerItems.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.HORIZONTAL,false));
        recyclerItems.setAdapter(foodAdapter);


        }

}

