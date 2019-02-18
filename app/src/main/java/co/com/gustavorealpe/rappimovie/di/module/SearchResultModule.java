package co.com.gustavorealpe.rappimovie.di.module;

import javax.inject.Named;

import co.com.gustavorealpe.rappimovie.di.PerActivity;
import co.com.gustavorealpe.rappimovie.presenter.search.SearchResultPresenter;
import co.com.gustavorealpe.rappimovie.presenter.search.SearchResultPresenterImpl;
import co.com.gustavorealpe.rappimovie.view.search.SearchResultView;
import dagger.Module;
import dagger.Provides;

@Module
public class SearchResultModule {

    private SearchResultView view;
    private String search;

    public SearchResultModule(SearchResultView view, String search) {
        this.view = view;
        this.search = search;
    }

    @Provides
    @PerActivity
    SearchResultView provideSearchResultView() {
        return view;
    }

    @Provides
    @PerActivity
    SearchResultPresenter provideSearchResultPresenter(SearchResultPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    @Named("search")
    String provideSearch(){
        return search;
    }
}
