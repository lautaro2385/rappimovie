package co.com.gustavorealpe.rappimovie.di.module;

import com.dbflow5.query.Operator;

import javax.inject.Named;

import co.com.gustavorealpe.rappimovie.di.PerActivity;
import co.com.gustavorealpe.rappimovie.presenter.MovieDetailPresenter;
import co.com.gustavorealpe.rappimovie.presenter.MovieDetailPresenterImpl;
import co.com.gustavorealpe.rappimovie.view.movie.MovieDetailView;
import dagger.Module;
import dagger.Provides;

@Module
public class MovieDetailModule {
    private MovieDetailView view;
    private Integer movieId;

    public MovieDetailModule(MovieDetailView view, Integer movieId) {
        this.view = view;
        this.movieId = movieId;
    }

    @Provides
    @PerActivity
    MovieDetailView provideView(){
        return view;
    }

    @Provides
    @PerActivity
    MovieDetailPresenter providePresenter(MovieDetailPresenterImpl presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    @Named("movieId")
    Integer provideMovieId(){
        return movieId;
    }
}
