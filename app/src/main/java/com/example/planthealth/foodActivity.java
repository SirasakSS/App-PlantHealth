package com.example.planthealth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class foodActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Food> foods;
    private static String JSON_URL = "https://mocki.io/v1/3495887b-2a2c-4344-927c-ed9ec089c726";
//    private static String JSON_URL = "https://mocki.io/v1/301805ed-8837-43a0-a028-32d280d3d8bf";
    Adapter adapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        searchView = (SearchView) findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.foodList);
        foods = new ArrayList<>();
        extractFoods();


    }

    // extract data
    private void extractFoods() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject foodObject = response.getJSONObject(i);

                        Food food = new Food();
                        food.setName(foodObject.getString("name").toString());
                        food.setUnit(foodObject.getString("unit").toString());
                        food.setCalories(foodObject.getString("calories").toString());
                        food.setType(foodObject.getString("type").toString());
                        foods.add(food);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new Adapter(getApplicationContext(),foods);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });
        queue.add(jsonArrayRequest);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });


    }

    private void filterList(String text) {
        List<Food> filteredList = new ArrayList<>();
        for (Food food : foods){
            if (food.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(food);
//                foods.add(food);
            }
        }
        if (filteredList.isEmpty()){
            Toast.makeText(this, "ไม่มีข้อมูลจ้าาาา", Toast.LENGTH_SHORT).show();
        }else {
            adapter.setFilteredList(filteredList);
        }
    }


}