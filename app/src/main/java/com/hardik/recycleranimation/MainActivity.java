package com.hardik.recycleranimation;

import android.provider.SyncStateContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hardik.recycleranimation.Constants.SERVICE_URL;
import com.hardik.recycleranimation.adapter.UserAdapter;
import com.hardik.recycleranimation.entity.User;
import com.hardik.recycleranimation.utility.MyApp;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ImageView imgGrid;
    private FloatingActionButton fabAdd;
    private CardView cardViewList,cardViewGrid;
    private UserAdapter userAdapter;
    private boolean isRow;
    private List<User> userList = new ArrayList<>();
    private RequestQueue mRequestQueue;
    private static final String url = "https://api.androidhive.info/json/shimmer/menu.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fabAdd = findViewById(R.id.fab_add);
        imgGrid = findViewById(R.id.img_grid);
        cardViewList = findViewById(R.id.card_list);
        cardViewGrid = findViewById(R.id.card_view);

        fabAdd.setOnClickListener(this);
        imgGrid.setOnClickListener(this);

        mRequestQueue = Volley.newRequestQueue(this);

        userAdapter = new UserAdapter(userList, this);
        recyclerView.setAdapter(userAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

        fetchRecipies();
    }

    public void fetchRecipies() {

        JsonArrayRequest request = new JsonArrayRequest(SERVICE_URL.GetRecipies, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                if (response == null) {
                    Toast.makeText(getApplicationContext(), "Couldn't fetch the menu! Please try again.", Toast.LENGTH_LONG).show();
                    return;
                }

                List<User> user = new Gson().fromJson(response.toString(), new TypeToken<List<User>>() {
                }.getType());

                userList.clear();
                userList.addAll(user);

                userAdapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mRequestQueue.add(request);
        //MyApp.getInstance().addToRequestQueue(request);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id){

            case R.id.img_grid:

                isRow = true;
                imgGrid.setImageResource(R.drawable.ic_view_compact_dark);
                break;
        }

        imgGrid.setImageResource(R.drawable.ic_view_compact_dark);
        isRow = true;


    }
}