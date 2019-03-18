package android.example.popularmovies;
import android.content.Context;
import android.content.Intent;
import android.example.popularmovies.Utils.JsonUtils;
import android.example.popularmovies.Model.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements PostersAdapter.ListItemClickListener {
    private PostersAdapter mAdapter;
    private RecyclerView mPosterList;

    private static final String API_KEY = BuildConfig.API_KEY;
    private Retrofit retrofit;
    MovieAPI movieAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPosterList = findViewById(R.id.rv_posters);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);

        mPosterList.setLayoutManager(layoutManager);
        mPosterList.setHasFixedSize(false);

        retrofit = new Retrofit.Builder().baseUrl(getString(R.string.image_database_baseURL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieAPI = retrofit.create(MovieAPI.class);
        CallGetPopularMovies();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemSelected = item.getItemId();
        if(menuItemSelected == R.id.action_most_popular){
           CallGetPopularMovies();
        }
        else if(menuItemSelected == R.id.action_top_rated){
            CallGetTopRatedMovies();
        }
        return true;
    }

    private void CallGetPopularMovies()
    {
        Call<JsonObject> call = movieAPI.getPopularMovies(API_KEY);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                BuildPosterGrid(JsonUtils.parseMoviePosters(response.body().toString(), getApplicationContext()));
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("MainActivity", t.getMessage());
            }
        });
    }

    private void CallGetTopRatedMovies()
    {
        Call<JsonObject> call = movieAPI.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                BuildPosterGrid(JsonUtils.parseMoviePosters(response.body().toString(), getApplicationContext()));
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("MainActivity", t.getMessage());
            }
        });
    }

    private void BuildPosterGrid(List<Movie> movies)
    {
        mAdapter = new PostersAdapter(movies, this);
        mPosterList.setAdapter(mAdapter);
    }

    @Override
    public void onListItemClick(Movie clickedItem){
        Context context = MainActivity.this;
        Class destinationActivity = MovieDetailsActivity.class;
        Intent intent = new Intent(context, destinationActivity);
        intent.putExtra("data", clickedItem);
        startActivity(intent);
    }
}
