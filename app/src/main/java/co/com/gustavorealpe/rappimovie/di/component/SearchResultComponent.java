package co.com.gustavorealpe.rappimovie.di.component;

import co.com.gustavorealpe.rappimovie.di.PerActivity;
import co.com.gustavorealpe.rappimovie.di.module.SearchResultModule;
import co.com.gustavorealpe.rappimovie.view.search.SearchResultsActivity;
import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {SearchResultModule.class})
public interface SearchResultComponent {
    void inject(SearchResultsActivity activity);
}
