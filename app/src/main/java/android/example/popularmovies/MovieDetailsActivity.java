package android.example.popularmovies;

import android.content.Intent;
import android.example.popularmovies.Model.MovieDetails;
import android.example.popularmovies.Utils.JsonUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    ImageView iv_Poster;
    TextView tv_title;
    TextView tv_release_date;
    TextView tv_user_rating;
    TextView tv_synopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        iv_Poster = findViewById(R.id.iv_details_poster);
        tv_title = findViewById(R.id.tv_details_title);
        tv_release_date = findViewById(R.id.tv_details_release_date);
        tv_user_rating = findViewById(R.id.tv_details_user_rating);
        tv_synopsis = findViewById(R.id.tv_details_synopsis);

        Intent intent = getIntent();
        MovieDetails movieDetails = null;
        if(intent.hasExtra(Intent.EXTRA_TEXT)){
            movieDetails = JsonUtils.parseMovieDetails(intent.getStringExtra(Intent.EXTRA_TEXT), this);
        }

        if(movieDetails != null)
        {
            Picasso.get().load(movieDetails.getPosterURL())
                    .into(iv_Poster);

            tv_title.setText(movieDetails.getOriginalTitle());
            tv_release_date.setText(movieDetails.getReleaseDate());
            tv_user_rating.setText(movieDetails.getUserRating());
            tv_synopsis.setText(movieDetails.getPlotSynopsis());
        }
    }
}
