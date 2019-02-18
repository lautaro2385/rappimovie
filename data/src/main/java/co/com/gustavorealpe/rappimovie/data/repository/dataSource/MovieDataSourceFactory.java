package co.com.gustavorealpe.rappimovie.data.repository.dataSource;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.joda.time.DateTime;
import org.joda.time.Minutes;

import javax.inject.Inject;

import co.com.gustavorealpe.rappimovie.data.db.movie.dao.MovieDao;
import co.com.gustavorealpe.rappimovie.data.db.movie.entity.MovieEntity_Table;
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
    private MovieDao movieDao;
    private MovieDataSource local;
    private MovieDataSource cloud;

    @Inject
    public MovieDataSourceFactory(VersionDao versionDao,
                                  ConnectivityManager connectivityManager,
                                  MovieDao movieDao,
                                  @Local MovieDataSource local,
                                  @Cloud MovieDataSource cloud) {

        this.versionDao = versionDao;
        this.connectivityManager = connectivityManager;
        this.movieDao = movieDao;
        this.local = local;
        this.cloud = cloud;
    }

    public MovieDataSource createDataSource(Integer category) {
        boolean isCon = isConnected();
        if (isCached(category) || !isCon)
            //if (hasData(category) || !isCon)
            return local;
        return cloud;
    }

    /**
     * REvisa si se envia el cache
     *
     * @return
     */
    private boolean isCached(Integer category) {
        VersionEntity ne = new VersionEntity(category, null);
        ne = versionDao.load(ne);
        if (ne == null)
            return false;
        DateTime dt = new DateTime(ne.getUpdateDate());
        Minutes min = Minutes.minutesBetween(dt, new DateTime());
        return min.isLessThan(Minutes.minutes(MINUTES_CACHE));
    }

    /**
     * REvisa si el celular tiene internet
     *
     * @return
     */
    private boolean isConnected() {
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    /**
     * tiene data
     *
     * @param category
     * @return
     */
    private boolean hasData(Integer category) {
        long val = movieDao.count(MovieEntity_Table.type.eq(category));
        return val > 0;
    }
}
