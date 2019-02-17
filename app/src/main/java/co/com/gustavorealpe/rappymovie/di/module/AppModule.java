package co.com.gustavorealpe.rappymovie.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import co.com.gustavorealpe.rappymovie.App;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Application providerApplication() {
        return app;
    }

    @Provides
    @Singleton
    App providerApp() {
        return app;
    }

    @Provides
    @Singleton
    Context providerContext() {
        return app;
    }
}
