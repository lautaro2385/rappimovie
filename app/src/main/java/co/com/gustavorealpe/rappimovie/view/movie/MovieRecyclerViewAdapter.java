package co.com.gustavorealpe.rappimovie.view.movie;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import org.joda.time.DateTime;

import java.security.MessageDigest;
import java.time.Year;
import java.time.temporal.TemporalField;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.gustavorealpe.rappimovie.R;
import co.com.gustavorealpe.rappimovie.common.Movie;
import co.com.gustavorealpe.rappimovie.data.net.ApiConstants;
import co.com.gustavorealpe.rappimovie.view.movie.MovieListFragment.OnListFragmentInteractionListener;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

    private List<Movie> mValues = null;
    private Context context;
    private final OnListFragmentInteractionListener mListener;

    public MovieRecyclerViewAdapter(OnListFragmentInteractionListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_movie_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
 //       holder.mIdView.setText(mValues.get(position).getId());
        Glide.with(context)
                .load(ApiConstants.ENDPOINT_IMAGES+holder.mItem.getBackdropPath())
                //.transform(new MyTransformation())
                .into(holder.mImage);

        //int year =new DateTime(holder.mItem.getReleaseDate()).year().get();
        holder.mContentView.setText(mValues.get(position).getTitle());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues == null ? 0 : mValues.size();
    }

    public void setData(List<Movie> movies) {
        this.mValues = movies;
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        @BindView(R.id.ivMovieImage)
        public ImageView mImage;
        @BindView(R.id.content)
        public TextView mContentView;
        public Movie mItem;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }


    private static class MyTransformation extends BitmapTransformation {


        @Override
        protected Bitmap transform(BitmapPool bitmapPool, Bitmap original, int width, int height) {
            Bitmap result = bitmapPool.get(width, height, Bitmap.Config.ARGB_8888);
            // If no matching Bitmap is in the pool, get will return null, so we should allocate.
            if (result == null) {
                // Use ARGB_8888 since we're going to add alpha to the image.
                result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            }
            // Create a Canvas backed by the result Bitmap.
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setAlpha(128);
            // Draw the original Bitmap onto the result Bitmap with a transformation.
            canvas.drawBitmap(original, 0, 0, paint);
            // Since we've replaced our original Bitmap, we return our new Bitmap here. Glide will
            // will take care of returning our original Bitmap to the BitmapPool for us.
            return result;
        }

        @Override
        public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

        }
    }
}
