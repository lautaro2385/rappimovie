package co.com.gustavorealpe.rappimovie.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.com.gustavorealpe.rappimovie.data.db.entity.MovieEntity;
import co.com.gustavorealpe.rappimovie.domain.movie.repository.MovieRepository;
import co.com.gustavorealpe.rappimovie.common.Movie;
import co.com.gustavorealpe.rappimovie.data.di.Cloud;
import co.com.gustavorealpe.rappimovie.data.di.Local;
import co.com.gustavorealpe.rappimovie.data.repository.dataSource.MovieDataSource;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

@Singleton
public class MovieRepositoryImpl implements MovieRepository {

    private MovieDataSource local;
    private MovieDataSource cloud;

    @Inject
    public MovieRepositoryImpl(@Local MovieDataSource local, @Cloud MovieDataSource cloud) {
        this.local = local;
        this.cloud = cloud;
    }

    @Override
    public Observable<List<Movie>> getPopular() {
        return this.cloud.getPopular()
                .doAfterNext(movies -> {
                    this.local.save(movies, MovieEntity.POPULAR);
                });
    }
}
