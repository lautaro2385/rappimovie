package co.com.gustavorealpe.rappimovie.di.module;

import javax.inject.Named;

import co.com.gustavorealpe.rappimovie.di.PerActivity;
import co.com.gustavorealpe.rappimovie.presenter.popular.MovieListPresenter;
import co.com.gustavorealpe.rappimovie.presenter.popular.MovieListPresenterImpl;
import co.com.gustavorealpe.rappimovie.view.movie.MovieListView;
import dagger.Module;
import dagger.Provides;

@Module
public class MovieListModule {
    private MovieListView movieListView;
    private Integer category;

    public MovieListModule(MovieListView movieListView, Integer category) {
        this.movieListView = movieListView;
        this.category = category;
    }

    @Provides
    @PerActivity
    MovieListView provideView() {
        return movieListView;
    }

    @Provides
    @PerActivity
    MovieListPresenter providePopularListPresenter(MovieListPresenterImpl popularListPresenter) {
        return popularListPresenter;
    }

    @Provides
    @PerActivity
    @Named("category")
    Integer provideCategory() {
        return category;
    }
}
