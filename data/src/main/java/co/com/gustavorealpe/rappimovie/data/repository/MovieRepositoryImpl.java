package co.com.gustavorealpe.rappimovie.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.com.gustavorealpe.rappimovie.common.Movie;
import co.com.gustavorealpe.rappimovie.data.db.movie.entity.MovieEntity;
import co.com.gustavorealpe.rappimovie.data.di.Cloud;
import co.com.gustavorealpe.rappimovie.data.di.Local;
import co.com.gustavorealpe.rappimovie.data.repository.dataSource.MovieCloud;
import co.com.gustavorealpe.rappimovie.data.repository.dataSource.MovieDataSource;
import co.com.gustavorealpe.rappimovie.data.repository.dataSource.MovieDataSourceFactory;
import co.com.gustavorealpe.rappimovie.domain.movie.repository.MovieRepository;
import io.reactivex.Observable;

@Singleton
public class MovieRepositoryImpl implements MovieRepository {

    private MovieDataSource local;
    private MovieDataSource cloud;
    private MovieDataSourceFactory factory;

    @Inject
    public MovieRepositoryImpl(@Local MovieDataSource local,
                               @Cloud MovieDataSource cloud,
                               MovieDataSourceFactory factory) {
        this.local = local;
        this.cloud = cloud;
        this.factory = factory;
    }

    @Override
    public Observable<List<Movie>> getPopular() {
        MovieDataSource dataSource = factory.createDataSource(MovieEntity.POPULAR);
        return dataSource.getPopular()
                .doAfterNext(movies -> {
                    if (dataSource instanceof MovieCloud)
                        this.local.save(movies, MovieEntity.POPULAR);
                });
    }

    @Override
    public Observable<List<Movie>> getUpcoming() {
        MovieDataSource dataSource = factory.createDataSource(MovieEntity.UPCOMING);
        return dataSource.getUpcoming()
                .doAfterNext(movies -> {
                    if (dataSource instanceof MovieCloud)
                        this.local.save(movies, MovieEntity.UPCOMING);
                });
    }

    @Override
    public Observable<List<Movie>> getTopRated() {
        MovieDataSource dataSource = factory.createDataSource(MovieEntity.TOP_RATED);
        return dataSource.getTopRated()
                .doAfterNext(movies -> {
                    if (dataSource instanceof MovieCloud)
                        this.local.save(movies, MovieEntity.TOP_RATED);
                });
    }
}
