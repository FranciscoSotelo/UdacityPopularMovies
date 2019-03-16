package android.example.popularmovies.Utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    final static String PARAM_APIKEY = "api_key";
    final static String PARAM_SORT = "sort_by";

    public static URL buildUrl(String baseURL, String APIKey, String sortby)
    {
        Uri builtUri = Uri.parse(baseURL).buildUpon()
                .appendQueryParameter(PARAM_APIKEY, APIKey)
                .appendQueryParameter(PARAM_SORT, sortby)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
