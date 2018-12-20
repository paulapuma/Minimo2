package edu.upc.eetac.dsa;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CitiesList extends AppCompatActivity {

    private RecyclerViewAdapter recycler;
    private RecyclerView recyclerView;

    private SegonMinimAPI apirest;

    TextView textViewFollowing;
    TextView textViewRepositories;
    ImageView imageViewProfile;
    List<CitiesList> citiesList;

    String username;

    Retrofit retrofit;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_item_list);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recycler = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Intent intent = getIntent();
        username = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);

        textViewFollowing = findViewById(R.id.textView_putFollowing);
        textViewRepositories = findViewById(R.id.textView_putRepos);
        imageViewProfile = (ImageView)findViewById(R.id.fotoUser);


        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Waiting for the server");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();


        getUserProfile();

        getFollowers();

    }


    private void getUserProfile(){

        Call<User> userCall = apirest.obtenerInfoUser(username);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    User user = response.body();

                    textViewFollowing.setText(String.valueOf(user.getFollowing()));
                    textViewRepositories.setText(String.valueOf(user.getPublic_repos()));
                    Picasso.with(getApplicationContext()).load(user.getAvatar_url()).into(imageViewProfile);

                    progressDialog.hide();
                }else{
                    progressDialog.hide();

                    //Show the alert dialog
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ListActivity.this);

                    alertDialogBuilder
                            .setTitle("Error")
                            .setMessage(response.message())
                            .setCancelable(false)
                            .setPositiveButton("OK", (dialog, which) -> finish());

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                progressDialog.hide();

                //Show the alert dialog
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ListActivity.this);

                alertDialogBuilder
                        .setTitle("Error")
                        .setMessage(t.getMessage())
                        .setCancelable(false)
                        .setPositiveButton("OK", (dialog, which) -> finish());

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    private void getFollowers(){

        Call<List<User>> userCall = apirest.obtenerListaFollowers(username);

        userCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    List<User> followersList = response.body();

                    if (followersList.size() != 0) {
                        recycler.addFollowers(followersList);

                    }
                    progressDialog.hide();
                }else{
                    progressDialog.hide();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                progressDialog.hide();
            }
        });

    }


}
