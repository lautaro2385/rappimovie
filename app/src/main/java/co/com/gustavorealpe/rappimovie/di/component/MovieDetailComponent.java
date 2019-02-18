package co.com.gustavorealpe.rappimovie.di.component;

import co.com.gustavorealpe.rappimovie.di.PerActivity;
import co.com.gustavorealpe.rappimovie.di.module.MovieDetailModule;
import co.com.gustavorealpe.rappimovie.view.movie.MovieDetailActivity;
import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {MovieDetailModule.class})
public interface MovieDetailComponent {
    void inject(MovieDetailActivity activity);
}
