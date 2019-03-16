package android.example.popularmovies.Model;

public class MovieDetails {
    private String posterURL;
    private int id;
    private String originalTitle;
    private String plotSynopsis;
    private String userRating;
    private String releaseDate;

    public MovieDetails(int id, String posterURL, String originalTitle, String plotSynopsis, String userRating, String releaseDate){
        this.id = id;
        this.posterURL = posterURL;
        this.originalTitle = originalTitle;
        this.plotSynopsis = plotSynopsis;
        this.userRating = userRating;
        this.releaseDate = releaseDate;

    }
    public int getId(){return id;}

    public String getPosterURL(){return posterURL;}

    public String getOriginalTitle(){return  originalTitle;}

    public String getPlotSynopsis(){return  plotSynopsis;}

    public String getUserRating(){return userRating;}

    public String getReleaseDate(){return releaseDate;}
}