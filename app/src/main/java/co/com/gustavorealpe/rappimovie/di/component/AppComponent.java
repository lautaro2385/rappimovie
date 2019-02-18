package co.com.gustavorealpe.rappimovie.di.component;

import android.content.Context;

import javax.inject.Singleton;

import co.com.gustavorealpe.rappimovie.App;
import co.com.gustavorealpe.rappimovie.business.movie.repository.MovieRepository;
import co.com.gustavorealpe.rappimovie.business.movie.usecase.GetPopular;
import co.com.gustavorealpe.rappimovie.di.module.AppModule;
import co.com.gustavorealpe.rappimovie.di.module.MapperModule;
import co.com.gustavorealpe.rappimovie.di.module.NetModule;
import co.com.gustavorealpe.rappimovie.di.module.RepositoryModule;
import co.com.gustavorealpe.rappimovie.view.MainActivity;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class, RepositoryModule.class, MapperModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
    Context getContext();
    App app();

    GetPopular getPopular();
}
