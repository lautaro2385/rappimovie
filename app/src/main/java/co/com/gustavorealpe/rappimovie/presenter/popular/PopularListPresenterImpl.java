package co.com.gustavorealpe.rappimovie.presenter.popular;

import java.util.List;

import javax.inject.Inject;

import co.com.gustavorealpe.rappimovie.business.movie.usecase.GetPopular;
import co.com.gustavorealpe.rappimovie.common.Movie;
import co.com.gustavorealpe.rappimovie.view.popular.PopularListView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PopularListPresenterImpl implements PopularListPresenter {

    private PopularListView view;
    private GetPopular getPopular;
    private Disposable subscribe;

    @Inject
    public PopularListPresenterImpl(PopularListView view,
                                    GetPopular getPopular) {
        this.view = view;
        this.getPopular = getPopular;
    }

    @Override
    public void onStart() {
        view.visibleProgressBar(true);
        subscribe = getPopular.execute().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError);
    }

    @Override
    public void onDestroy() {
        if (subscribe != null)
            subscribe.dispose();
    }

    private void handleResults(List<Movie> movies) {
        view.setData(movies);
        view.visibleProgressBar(false);
    }

    private void handleError(Throwable t) {
        view.visibleProgressBar(false);
    }
}
