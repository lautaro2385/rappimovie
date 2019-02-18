package co.com.gustavorealpe.rappimovie.domain.movie.repository;

import java.util.List;

import co.com.gustavorealpe.rappimovie.common.Movie;
import io.reactivex.Observable;

public interface MovieRepository {
    Observable<List<Movie>> getPopular();
    Observable<List<Movie>> getUpcoming();
    Observable<List<Movie>> getTopRated();

    Observable<Movie> getMovieById(Integer id);

    Observable<List<Movie>> searchMovie(String search);
}
