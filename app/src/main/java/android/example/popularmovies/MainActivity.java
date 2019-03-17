package android.example.popularmovies;
import android.content.Context;
import android.content.Intent;
import android.example.popularmovies.Utils.JsonUtils;
import android.example.popularmovies.Utils.NetworkUtils;
import android.example.popularmovies.Model.Movie;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;


import java.io.IOException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PostersAdapter.ListItemClickListener {
    private PostersAdapter mAdapter;
    private RecyclerView mPosterList;

    private static final String API_KEY = BuildConfig.API_KEY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPosterList = (RecyclerView) findViewById(R.id.rv_posters);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);

        mPosterList.setLayoutManager(layoutManager);
        mPosterList.setHasFixedSize(false);

        MakeMoviesDiscoverQuery(getString(R.string.movie_database_baseURL_popularity));
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
            MakeMoviesDiscoverQuery(getString(R.string.movie_database_baseURL_popularity));
        }
        else if(menuItemSelected == R.id.action_top_rated){
            MakeMoviesDiscoverQuery(getString(R.string.movie_database_baseURL_top_rated));

        }
        return true;
    }

    private void MakeMoviesDiscoverQuery(String baseURL){
        URL url = NetworkUtils.buildUrl(baseURL, API_KEY);

        new MoviesDiscoveryQueryTask().execute(url);
    }

    public class MoviesDiscoveryQueryTask extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls){
            URL searchURL = urls[0];
            String searchResults = null;
            try{
                searchResults = NetworkUtils.getResponseFromHttpUrl(searchURL);
            }catch (IOException e){
                e.printStackTrace();
            }

            return searchResults;
        }

        @Override
        protected void onPostExecute(String searchResults){
            if(searchResults != null && !searchResults.equals("")){
               BuildPosterGrid(JsonUtils.parseMoviePosters(searchResults, getBaseContext()));
            }
        }
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
        intent.putExtra(Intent.EXTRA_TEXT, clickedItem.getJsonData());
        startActivity(intent);
    }
}
