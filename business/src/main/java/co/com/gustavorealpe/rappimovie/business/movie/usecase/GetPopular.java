package co.com.gustavorealpe.rappimovie.business.movie.usecase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.com.gustavorealpe.rappimovie.business.movie.repository.MovieRepository;
import co.com.gustavorealpe.rappimovie.common.Movie;
import io.reactivex.Observable;

@Singleton
public class GetPopular {

    private MovieRepository movieRepository;

    @Inject
    public GetPopular(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Observable<List<Movie>> execute(){
       return movieRepository.getPopular();
    }
}
