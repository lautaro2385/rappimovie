package co.com.gustavorealpe.rappimovie.di.module;

import javax.inject.Named;

import co.com.gustavorealpe.rappimovie.di.PerActivity;
import co.com.gustavorealpe.rappimovie.presenter.movie.MovieDetailPresenter;
import co.com.gustavorealpe.rappimovie.presenter.movie.MovieDetailPresenterImpl;
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
