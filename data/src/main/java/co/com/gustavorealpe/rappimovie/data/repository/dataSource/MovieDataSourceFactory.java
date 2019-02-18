package co.com.gustavorealpe.rappimovie.data.repository.dataSource;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.joda.time.DateTime;
import org.joda.time.Minutes;

import javax.inject.Inject;

import co.com.gustavorealpe.rappimovie.data.db.version.VersionDao;
import co.com.gustavorealpe.rappimovie.data.db.version.VersionEntity;
import co.com.gustavorealpe.rappimovie.data.di.Cloud;
import co.com.gustavorealpe.rappimovie.data.di.Local;

public class MovieDataSourceFactory {
    /**
     * la cantidad de minutos pasada una actualización antes de solicitar al servidor
     */
    private final static Integer MINUTES_CACHE = 10;
    private VersionDao versionDao;
    private ConnectivityManager connectivityManager;
    private MovieDataSource local;
    private MovieDataSource cloud;

    @Inject
    public MovieDataSourceFactory(VersionDao versionDao,
                                  ConnectivityManager connectivityManager,
                                  @Local MovieDataSource local,
                                  @Cloud MovieDataSource cloud) {

        this.versionDao = versionDao;
        this.connectivityManager = connectivityManager;
        this.local = local;
        this.cloud = cloud;
    }

    public MovieDataSource createDataSource() {
        if (isCached() || !isConnected())
            return local;
        return cloud;
    }

    /**
     * REvisa si se envia el cache
     * @return
     */
    private boolean isCached() {
        VersionEntity ne = new VersionEntity(VersionEntity.MOVIES, null);
        ne = versionDao.load(ne);
        if (ne == null)
            return false;
        DateTime dt = new DateTime(ne.getUpdateDate());
        Minutes min = Minutes.minutesBetween(dt, new DateTime());
        return min.isLessThan(Minutes.minutes(MINUTES_CACHE));
    }

    /**
     * REvisa si el celular tiene internet
     * @return
     */
    private boolean isConnected() {
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
