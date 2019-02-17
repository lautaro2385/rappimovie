package co.com.gustavorealpe.rappymovie;

import android.app.Application;

import co.com.gustavorealpe.rappymovie.di.component.AppComponent;
import co.com.gustavorealpe.rappymovie.di.component.DaggerAppComponent;
import co.com.gustavorealpe.rappymovie.di.module.AppModule;
import co.com.gustavorealpe.rappymovie.di.module.NetModule;
import lombok.Getter;


public class App extends Application {
    @Getter
    private AppComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
       applicationComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(this))
                //.mapperModule(new MapperModule())
                .build();
    }

}
