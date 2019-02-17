package co.com.gustavorealpe.rappimovie.di.module;

import co.com.gustavorealpe.rappimovie.di.PerActivity;
import co.com.gustavorealpe.rappimovie.presenter.popular.PopularListPresenter;
import co.com.gustavorealpe.rappimovie.presenter.popular.PopularListPresenterImpl;
import co.com.gustavorealpe.rappimovie.view.popular.PopularListView;
import dagger.Module;
import dagger.Provides;

@Module
public class PopularListModule {
    private PopularListView popularListView;

    public PopularListModule(PopularListView popularListView) {
        this.popularListView = popularListView;
    }

    @Provides
    @PerActivity
    PopularListView provideView(){
        return popularListView;
    }

    @Provides
    @PerActivity
    PopularListPresenter providePopularListPresenter(PopularListPresenterImpl popularListPresenter){
        return popularListPresenter;
    }
}
