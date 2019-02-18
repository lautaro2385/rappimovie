package co.com.gustavorealpe.rappimovie.domain.movie.usecase;

import javax.inject.Inject;
import javax.inject.Named;

import co.com.gustavorealpe.rappimovie.common.Movie;
import co.com.gustavorealpe.rappimovie.domain.UseCase;
import co.com.gustavorealpe.rappimovie.domain.movie.repository.MovieRepository;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import lombok.Setter;

/**
 * Solicita la infrormación de uns poelícula por id
 */
public class GetMovieById extends UseCase<Movie> {
    private MovieRepository movieRepository;
    @Setter
    private Integer id;

    @Inject
    public GetMovieById(@Named("executor_thread") Scheduler executorThread,
                        @Named("ui_thread") Scheduler uiThread,
                        MovieRepository movieRepository) {
        super(executorThread, uiThread);
        this.movieRepository = movieRepository;
    }

    @Override
    protected Observable<Movie> createObservableUseCase() {
        return movieRepository.getMovieById(id);
    }
}
