package co.com.gustavorealpe.rappimovie.di.component;

import co.com.gustavorealpe.rappimovie.di.PerActivity;
import co.com.gustavorealpe.rappimovie.di.module.PopularListModule;
import co.com.gustavorealpe.rappimovie.view.popular.PopularListListFragment;
import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {PopularListModule.class})
public interface PopularListComponent {
    void inject(PopularListListFragment fragment);
}
