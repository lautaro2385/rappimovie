package co.com.gustavorealpe.rappimovie.di.component;

import co.com.gustavorealpe.rappimovie.di.PerActivity;
import co.com.gustavorealpe.rappimovie.di.module.MovieListModule;
import co.com.gustavorealpe.rappimovie.view.movie.MovieListFragment;
import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {MovieListModule.class})
public interface MovieListComponent {
    void inject(MovieListFragment fragment);
}
