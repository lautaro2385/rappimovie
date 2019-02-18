package co.com.gustavorealpe.rappimovie;

import android.app.Application;
import android.content.Context;

import com.dbflow5.config.DatabaseConfig;
import com.dbflow5.config.FlowConfig;
import com.dbflow5.config.FlowManager;
import com.dbflow5.database.AndroidSQLiteOpenHelper;

import co.com.gustavorealpe.rappimovie.data.db.databases.AppDatabase;
import co.com.gustavorealpe.rappimovie.di.component.AppComponent;
import co.com.gustavorealpe.rappimovie.di.component.DaggerAppComponent;
import co.com.gustavorealpe.rappimovie.di.module.AppModule;
import co.com.gustavorealpe.rappimovie.di.module.NetModule;
import lombok.Getter;


public class App extends Application {
    @Getter
    private AppComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
        Context context = this.getApplicationContext();

        FlowManager.init(new FlowConfig.Builder(context)
                .database(DatabaseConfig.builder(AppDatabase.class, AndroidSQLiteOpenHelper.createHelperCreator(context))
                        .databaseName(AppDatabase.NAME)
                        .build())
                .build());
    }

    private void initializeInjector() {
        applicationComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(this))
                //.mapperModule(new MapperModule())
                .build();
    }

}
