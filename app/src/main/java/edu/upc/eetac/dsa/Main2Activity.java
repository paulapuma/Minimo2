package edu.upc.eetac.dsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main2Activity extends AppCompatActivity {

    private EditText reposText;
    private EditText followingText;
    private RecyclerView listFollowers;
    private ImageView image;

    protected SegonMinimAPI segonMinimAPI;
    private RecyclerView listCities;



    private void getCities() {
        SegonMinimAPI dibaAPI = this.segonMinimAPI;
        //showProgress(true);
        Call<Cities> callCitiesList = dibaAPI.cities("1","11");
        callCitiesList.enqueue(new Callback<Cities>() {
            @Override
            public void onResponse(Call<Cities> call, Response<Cities> response) {
                int statusCode = response.code();
                if (response.isSuccessful()) {
                    Cities cities = response.body();
                    listCities.setAdapter(new RecyclerViewAdapter(cities.getElements());
          //          showProgress(false);
                }
            }
            @Override
            public void onFailure(Call<Cities> call, Throwable t) {

            }
        });

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_item_list);

        Intent intent = getIntent();

        String userGit = intent.getStringExtra("user");
        Log.d("TAG", "ffffff");//System.out.println(userGit);

        createTracksAPI();
        segonMinimAPI.getUser(userGit).enqueue(getUserProfile);
        segonMinimAPI.getFollowers(userGit).enqueue(getFollowers);

    }






    };


}
}
        }
        }
