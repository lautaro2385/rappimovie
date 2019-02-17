package co.com.gustavorealpe.rappymovie.di.component;

import android.content.Context;

import javax.inject.Singleton;

import co.com.gustavorealpe.rappymovie.App;
import co.com.gustavorealpe.rappymovie.di.module.AppModule;
import co.com.gustavorealpe.rappymovie.di.module.NetModule;
import co.com.gustavorealpe.rappymovie.di.module.RepositoryModule;
import co.com.gustavorealpe.rappymovie.view.MainActivity;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class, RepositoryModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
    Context getContext();
    App app();
}
