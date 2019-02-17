package co.com.gustavorealpe.rappimovie.di.module;

import co.com.gustavorealpe.rappimovie.business.movie.repository.MovieRepository;
import co.com.gustavorealpe.rappimovie.data.di.Cloud;
import co.com.gustavorealpe.rappimovie.data.di.Local;
import co.com.gustavorealpe.rappimovie.data.repository.MovieRepositoryImpl;
import co.com.gustavorealpe.rappimovie.data.repository.dataSource.MovieCloud;
import co.com.gustavorealpe.rappimovie.data.repository.dataSource.MovieDataSource;
import co.com.gustavorealpe.rappimovie.data.repository.dataSource.MovieLocal;
import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
    @Provides
    MovieRepository provideMovieRepository(MovieRepositoryImpl movieRepository){
        return movieRepository;
    }

    @Provides
    @Local
    MovieDataSource provideMovieLocal(MovieLocal ml){
     return ml;
    }

    @Provides
    @Cloud
    MovieDataSource provideMovieCloud(MovieCloud ml){
        return ml;
    }
}
