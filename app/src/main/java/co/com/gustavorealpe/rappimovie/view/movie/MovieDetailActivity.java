package co.com.gustavorealpe.rappimovie.view.movie;

import android.media.Image;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.gustavorealpe.rappimovie.R;
import co.com.gustavorealpe.rappimovie.common.Movie;
import co.com.gustavorealpe.rappimovie.data.net.ApiConstants;
import co.com.gustavorealpe.rappimovie.di.component.DaggerMovieDetailComponent;
import co.com.gustavorealpe.rappimovie.di.module.MovieDetailModule;
import co.com.gustavorealpe.rappimovie.presenter.MovieDetailPresenter;
import co.com.gustavorealpe.rappimovie.view.BaseActivity;

public class MovieDetailActivity extends BaseActivity implements  MovieDetailView{

    public static final String MOVIE_ID = "movieID";
    @Inject
    MovieDetailPresenter presenter;

    @BindView(R.id.ivImage)
    ImageView ivImage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        int id = getIntent().getIntExtra(MOVIE_ID,0);

        initDagger(id);
    }

    private void initDagger(int id){
        DaggerMovieDetailComponent.builder()
                .appComponent(getAppComponent())
                .movieDetailModule(new MovieDetailModule(this, id))
                .build()
                .inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onInit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        presenter = null;
    }

    @Override
    public void setMovie(Movie movie) {
        Glide.with(this)
                .load(ApiConstants.ENDPOINT_IMAGES+movie.getBackdropPath())
                .into(ivImage);
        toolbar.setTitle(movie.getTitle());
        collapsingToolbarLayout.setTitle(movie.getTitle());
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
