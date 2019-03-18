package android.example.popularmovies;

import android.example.popularmovies.Model.Movie;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieAPI {
    @GET("popular")
    Call<JsonObject> getPopularMovies(@Query("api_key") String api_key);

    @GET("top_rated")
    Call<JsonObject> getTopRatedMovies(@Query("api_key") String api_key);
}
