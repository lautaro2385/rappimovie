package co.com.gustavorealpe.rappimovie.data.db.databases;

import com.dbflow5.annotation.Database;
import com.dbflow5.config.DBFlowDatabase;

@Database(version = AppDatabase.VERSION)
public abstract class  AppDatabase extends DBFlowDatabase {
    public static final String NAME = "RappiMovieDatabase";

    public static final int VERSION = 1;
}
