package co.com.gustavorealpe.rappimovie.view.movie;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.lzyzsd.circleprogress.DonutProgress;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.joda.time.DateTime;

import javax.inject.Inject;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.gustavorealpe.rappimovie.R;
import co.com.gustavorealpe.rappimovie.common.Movie;
import co.com.gustavorealpe.rappimovie.data.net.ApiConstants;
import co.com.gustavorealpe.rappimovie.di.component.DaggerMovieDetailComponent;
import co.com.gustavorealpe.rappimovie.di.module.MovieDetailModule;
import co.com.gustavorealpe.rappimovie.presenter.movie.MovieDetailPresenter;
import co.com.gustavorealpe.rappimovie.view.BaseActivity;

public class MovieDetailActivity extends BaseActivity implements MovieDetailView {

    public static final String MOVIE_ID = "movieID";
    @Inject
    MovieDetailPresenter presenter;

    @BindView(R.id.ivImage)
    ImageView ivImage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.donut_progress)
    DonutProgress donutProgress;
    @BindView(R.id.tvOriginalLanguage)
    TextView tvOriginalLanguage;
    @BindView(R.id.tvOverview)
    TextView tvOverview;
    @BindView(R.id.tvOriginalTitle)
    TextView tvOriginalTitle;

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

        int id = getIntent().getIntExtra(MOVIE_ID, 0);

        initDagger(id);
    }

    private void initDagger(int id) {
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
                .load(ApiConstants.ENDPOINT_IMAGES + movie.getBackdropPath())
                .into(ivImage);
        int year = new DateTime(movie.getReleaseDate()).getYear();
        String title = String.format("%s (%d)", movie.getTitle(), year);
        toolbar.setTitle(title);
        collapsingToolbarLayout.setTitle(title);
        donutProgress.setMax(100);
        int val = (int) (movie.getVoteAverage()*10F);
        donutProgress.setText(String.valueOf(val)+"%");
        donutProgress.setProgress(val);

        tvOriginalLanguage.setText(movie.getOriginalLanguage());

        tvOverview.setText(movie.getOverview());

        tvOriginalTitle.setText(movie.getOriginalTitle());
    }


    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
