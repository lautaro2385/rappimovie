package co.com.gustavorealpe.rappimovie.presenter.search;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import co.com.gustavorealpe.rappimovie.common.Movie;
import co.com.gustavorealpe.rappimovie.domain.movie.usecase.SearchMovie;
import co.com.gustavorealpe.rappimovie.view.search.SearchResultView;
import io.reactivex.observers.DisposableObserver;

public class SearchResultPresenterImpl implements SearchResultPresenter {
    private SearchResultView view;
    private SearchMovie searchMovie;
    private String search;

    @Inject
    public SearchResultPresenterImpl(SearchResultView view,
                                     SearchMovie searchMovie,
                                     @Named("search") String search) {
        this.view = view;
        this.searchMovie = searchMovie;
        this.search = search;
    }

    @Override
    public void onInit() {
        view.visibleProgressBar(true);
        searchMovie.setSearch(search);
        searchMovie.execute(new DisposableObserver<List<Movie>>() {
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
        searchMovie.dispose();
        view = null;
    }
}
