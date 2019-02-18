package co.com.gustavorealpe.rappimovie.data.repository.dataSource;

import java.util.List;

import co.com.gustavorealpe.rappimovie.common.Movie;
import io.reactivex.Observable;

public interface MovieDataSource {
    Observable<List<Movie>> getPopular();

    void save(List<Movie> movies, int type);
}

