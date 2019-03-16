package android.example.popularmovies;
import android.content.Context;
import android.example.popularmovies.Model.Movie;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.List;

public class PostersAdapter extends RecyclerView.Adapter<PostersAdapter.PosterViewHolder> {

    final private ListItemClickListener mOnClickListener;

    private List<Movie> movieListData;

    public interface  ListItemClickListener{
        void onListItemClick(Movie clickedItem);
    }

    @Override
    public int getItemCount() {
        return movieListData.size();
    }

    public PostersAdapter(List<Movie> movies, ListItemClickListener listener){
        movieListData = movies;
        mOnClickListener = listener;
    }

    @Override
    public PosterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.poster_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        PosterViewHolder viewHolder = new PosterViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PosterViewHolder holder, int position) {
        holder.bind(position);
    }

    class PosterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView listItemPosterView;

        public PosterViewHolder(View itemView){
            super(itemView);

            listItemPosterView = (ImageView) itemView.findViewById(R.id.iv_item_poster);

            itemView.setOnClickListener(this);
        }

        void bind(int listIndex){
            Picasso.get().load(movieListData.get(listIndex).getPosterURL())
                    .fit()
                    .into(listItemPosterView);
        }

        @Override
        public void onClick(View view){
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(movieListData.get(clickedPosition));
        }
    }
}
