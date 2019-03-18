package android.example.popularmovies.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private String posterURL;
    private int id;
    private String originalTitle;
    private String plotSynopsis;
    private String userRating;
    private String releaseDate;

    public Movie(int id, String posterURL, String originalTitle, String plotSynopsis, String userRating, String releaseDate) {
        this.posterURL = posterURL;
        this.id = id;
        this.originalTitle = originalTitle;
        this.plotSynopsis = plotSynopsis;
        this.userRating = userRating;
        this.releaseDate = releaseDate;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public int getId() {
        return id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    public String getUserRating() {
        return userRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(posterURL);
        dest.writeString(originalTitle);
        dest.writeString(plotSynopsis);
        dest.writeString(userRating);
        dest.writeString(releaseDate);
    }

    public static final Parcelable.Creator<Movie> CREATOR
            = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in.readInt(), in.readString(), in.readString(), in.readString(), in.readString(), in.readString());
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
