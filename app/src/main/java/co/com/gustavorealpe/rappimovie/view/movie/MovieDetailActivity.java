package co.com.gustavorealpe.rappimovie.view.movie;

import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import co.com.gustavorealpe.rappimovie.R;
import co.com.gustavorealpe.rappimovie.common.Movie;
import co.com.gustavorealpe.rappimovie.di.component.DaggerMovieDetailComponent;
import co.com.gustavorealpe.rappimovie.di.module.MovieDetailModule;
import co.com.gustavorealpe.rappimovie.presenter.MovieDetailPresenter;
import co.com.gustavorealpe.rappimovie.view.BaseActivity;

public class MovieDetailActivity extends BaseActivity implements MovieDetailView {

    public static final String MOVIE_ID = "movieID";
    @Inject
    MovieDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

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

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
