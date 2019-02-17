package co.com.gustavorealpe.rappymovie.data.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.com.gustavorealpe.rappymovie.business.movie.repository.MovieRepository;
import co.com.gustavorealpe.rappymovie.data.di.Cloud;
import co.com.gustavorealpe.rappymovie.data.di.Local;
import co.com.gustavorealpe.rappymovie.data.repository.dataSource.MovieDataSource;

@Singleton
public class MovieRepositoryImpl implements MovieRepository {

    private MovieDataSource local;
    private MovieDataSource cloud;

    @Inject
    public MovieRepositoryImpl(@Local MovieDataSource local, @Cloud MovieDataSource cloud){
        this.local = local;
        this.cloud = cloud;
    }

    public void getMovies(){
        this.cloud.getMovies();
    }
}
