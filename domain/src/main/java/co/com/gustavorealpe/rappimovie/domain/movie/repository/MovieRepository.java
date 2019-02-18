package co.com.gustavorealpe.rappimovie.domain.movie.repository;

import java.util.List;

import co.com.gustavorealpe.rappimovie.common.Movie;
import io.reactivex.Observable;

public interface MovieRepository {
    Observable<List<Movie>> getPopular();
}
