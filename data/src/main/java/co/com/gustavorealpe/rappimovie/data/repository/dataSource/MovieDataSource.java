package co.com.gustavorealpe.rappimovie.data.repository.dataSource;

import java.util.List;

import co.com.gustavorealpe.rappimovie.common.Movie;
import io.reactivex.Observable;

public interface MovieDataSource {
    void getMovies();

    Observable<List<Movie>> getPopular();
}

