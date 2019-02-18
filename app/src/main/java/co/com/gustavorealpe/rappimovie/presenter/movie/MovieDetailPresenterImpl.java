package co.com.gustavorealpe.rappimovie.presenter.movie;

import javax.inject.Inject;
import javax.inject.Named;

import co.com.gustavorealpe.rappimovie.common.Movie;
import co.com.gustavorealpe.rappimovie.domain.movie.usecase.GetMovieById;
import co.com.gustavorealpe.rappimovie.view.movie.MovieDetailView;
import io.reactivex.observers.DisposableObserver;

public class MovieDetailPresenterImpl implements MovieDetailPresenter {

    private MovieDetailView view;
    private GetMovieById getMovieById;
    private Integer movieId;

    @Inject
    public MovieDetailPresenterImpl(MovieDetailView view,
                                    GetMovieById getMovieById,
                                    @Named("movieId") Integer movieId) {
        this.view = view;
        this.getMovieById = getMovieById;
        this.movieId = movieId;
    }

    @Override
    public void onInit() {
        getMovieById.setId(movieId);
        getMovieById.execute(new DisposableObserver<Movie>() {
            @Override
            public void onNext(Movie movie) {
                view.setMovie(movie);
            }

            @Override
            public void onError(Throwable e) {
                view.showToast(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public void onDestroy() {
        getMovieById.dispose();
        view = null;
    }
}
