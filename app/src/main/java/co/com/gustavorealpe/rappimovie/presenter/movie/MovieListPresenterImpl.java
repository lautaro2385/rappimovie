package co.com.gustavorealpe.rappimovie.presenter.movie;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import co.com.gustavorealpe.rappimovie.common.Movie;
import co.com.gustavorealpe.rappimovie.domain.UseCase;
import co.com.gustavorealpe.rappimovie.domain.movie.usecase.GetPopular;
import co.com.gustavorealpe.rappimovie.domain.movie.usecase.GetTopRated;
import co.com.gustavorealpe.rappimovie.domain.movie.usecase.GetUpcoming;
import co.com.gustavorealpe.rappimovie.view.movie.MovieListView;
import io.reactivex.observers.DisposableObserver;

import static co.com.gustavorealpe.rappimovie.view.movie.MovieListFragment.TOP_RATED;
import static co.com.gustavorealpe.rappimovie.view.movie.MovieListFragment.UPCOMING;

public class MovieListPresenterImpl implements MovieListPresenter {

    private MovieListView view;
    private GetPopular getPopular;
    private GetTopRated getTopRated;
    private GetUpcoming getUpcoming;
    private Integer category;

    @Inject
    public MovieListPresenterImpl(MovieListView view,
                                  GetPopular getPopular,
                                  GetTopRated getTopRated,
                                  GetUpcoming getUpcoming,
                                  @Named("category") Integer category) {
        this.view = view;
        this.getPopular = getPopular;
        this.getTopRated = getTopRated;
        this.getUpcoming = getUpcoming;
        this.category = category;
    }

    @Override
    public void onStart() {
        view.visibleProgressBar(true);
        UseCase useCase;
        if (category.equals(TOP_RATED)) {
            useCase = getTopRated;
        } else if (category.equals(UPCOMING)) {
            useCase = getUpcoming;
        } else {
            useCase = getPopular;
        }

        useCase.execute(new DisposableObserver<List<Movie>>() {
            @Override
            public void onNext(List<Movie> movies) {
                view.setData(movies);
            }

            @Override
            public void onError(Throwable e) {
                view.visibleProgressBar(false);
                view.showMessage(e.getMessage());
            }

            @Override
            public void onComplete() {
                view.visibleProgressBar(false);
            }
        });
    }

    @Override
    public void onDestroy() {
        getPopular.dispose();
        getTopRated.dispose();
        getUpcoming.dispose();
        this.view = null;
    }
}
