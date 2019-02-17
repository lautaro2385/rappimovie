package co.com.gustavorealpe.rappimovie.presenter.popular;

import javax.inject.Inject;

import co.com.gustavorealpe.rappimovie.view.popular.PopularListView;

public class PopularListPresenterImpl implements PopularListPresenter {

    private PopularListView view;

    @Inject
    public PopularListPresenterImpl(PopularListView view) {
        this.view = view;
    }

    @Override
    public void onStart() {
        view.visibleProgressBar(true);
        
    }
}
