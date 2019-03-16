package android.example.popularmovies.Model;

public class Movie {
    private String posterURL;
    private int id;
    private String jsonMovieData;

    public Movie(int id, String posterURL, String jsonMovieData){
        this.id = id;
        this.posterURL = posterURL;
        this.jsonMovieData = jsonMovieData;
    }
    public int getId(){return id;}

    public String getPosterURL(){return posterURL;}

    public String getJsonData(){return  jsonMovieData;}
}
