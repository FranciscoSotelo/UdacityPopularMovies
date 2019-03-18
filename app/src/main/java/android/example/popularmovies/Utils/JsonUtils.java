package android.example.popularmovies.Utils;

import android.content.Context;
import android.example.popularmovies.Model.Movie;
import android.example.popularmovies.R;

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

                String baseURL = context.getString(R.string.poster_baseURL);
                String imageSize = context.getString(R.string.image_poster_size);
                String posterString = movieJsonObj.getString(context.getString(R.string.poster_path_key));
                String builtPosterURL = baseURL + imageSize + posterString;
                String originalTitle = movieJsonObj.getString(context.getString(R.string.original_title_key));
                String plotSynopsis = movieJsonObj.getString(context.getString(R.string.synopsis_key));
                String userRating = movieJsonObj.getString(context.getString(R.string.user_rating_key));
                String releaseDate = movieJsonObj.getString(context.getString(R.string.release_date_key));

                int id = movieJsonObj.getInt(context.getString(R.string.id_key));

                Movie movie = new Movie(id, builtPosterURL, originalTitle, plotSynopsis, userRating, releaseDate);
                movies.add(movie);
            }
            return movies;
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
