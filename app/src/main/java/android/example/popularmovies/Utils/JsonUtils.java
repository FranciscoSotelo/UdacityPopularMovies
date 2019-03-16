package android.example.popularmovies.Utils;

import android.content.Context;
import android.example.popularmovies.Model.Movie;
import android.example.popularmovies.Model.MovieDetails;
import android.example.popularmovies.R;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static List<Movie> parseMoviePosters(String json, Context context) {
        try {
            JSONObject MoviesData = new JSONObject(json);
            JSONArray moviesData = MoviesData.getJSONArray(context.getString(R.string.results_key));

            List<Movie> movies = new ArrayList<>();
            for(int i = 0; i < moviesData.length(); i++)
            {
                JSONObject movieJsonObj = moviesData.getJSONObject(i);

                String baseURL = context.getString(R.string.image_database_baseURL);
                String imageSize = context.getString(R.string.image_poster_size);
                String posterString = movieJsonObj.getString(context.getString(R.string.poster_path_key));
                String builtPosterURL = baseURL + imageSize + posterString;

                int id = movieJsonObj.getInt(context.getString(R.string.id_key));

                Movie movie = new Movie(id, builtPosterURL, movieJsonObj.toString());
                movies.add(movie);
            }
            return movies;
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static MovieDetails parseMovieDetails(String json, Context context){
        try {
            JSONObject MovieData = new JSONObject(json);

            int id = MovieData.getInt(context.getString(R.string.id_key));

            String baseURL = context.getString(R.string.image_database_baseURL);
            String imageSize = context.getString(R.string.image_poster_size);
            String posterString = MovieData.getString(context.getString(R.string.poster_path_key));
            String builtPosterURL = baseURL + imageSize + posterString;


            String originalTitle = MovieData.getString(context.getString(R.string.original_title_key));
            String plotSynopsis = MovieData.getString(context.getString(R.string.synopsis_key));
            String userRating = MovieData.getString(context.getString(R.string.user_rating_key));
            String releaseDate = MovieData.getString(context.getString(R.string.release_date_key));

            MovieDetails movieDetails = new MovieDetails(
                    id,
                    builtPosterURL,
                    originalTitle,
                    plotSynopsis,
                    userRating,
                    releaseDate);

            return movieDetails;
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
