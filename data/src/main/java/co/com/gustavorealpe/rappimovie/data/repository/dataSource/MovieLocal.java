package co.com.gustavorealpe.rappimovie.data.repository.dataSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.com.gustavorealpe.rappimovie.common.Movie;
import io.reactivex.Observable;

@Singleton
public class MovieLocal  implements MovieDataSource {

    @Inject
    public MovieLocal(){}

    @Override
    public void getMovies() {

    }

    @Override
    public Observable<List<Movie>> getPopular() {

        return null;
    }
}
