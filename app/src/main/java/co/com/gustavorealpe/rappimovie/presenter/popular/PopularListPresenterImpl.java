package co.com.gustavorealpe.rappimovie.presenter.popular;

import java.util.List;

import javax.inject.Inject;

import co.com.gustavorealpe.rappimovie.common.Movie;
import co.com.gustavorealpe.rappimovie.domain.movie.usecase.GetPopular;
import co.com.gustavorealpe.rappimovie.view.popular.PopularListView;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class PopularListPresenterImpl implements PopularListPresenter {

    private PopularListView view;
    private GetPopular getPopular;

    @Inject
    public PopularListPresenterImpl(PopularListView view,
                                    GetPopular getPopular) {
        this.view = view;
        this.getPopular = getPopular;
    }

    @Override
    public void onStart() {
        view.visibleProgressBar(true);
        getPopular.execute(new DisposableObserver<List<Movie>>() {
            @Override
            public void onNext(List<Movie> movies) {
                view.setData(movies);
            }

            @Override
            public void onError(Throwable e) {
                view.visibleProgressBar(false);
            }

            @Override
            public void onComplete() {
                view.visibleProgressBar(false);
            }
        });
    }

    @Override
    public void onDestroy() {
        this.getPopular.dispose();
        this.view = null;
    }
}
