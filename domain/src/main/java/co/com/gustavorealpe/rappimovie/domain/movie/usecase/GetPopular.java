package co.com.gustavorealpe.rappimovie.domain.movie.usecase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import co.com.gustavorealpe.rappimovie.domain.UseCase;
import co.com.gustavorealpe.rappimovie.domain.movie.repository.MovieRepository;
import co.com.gustavorealpe.rappimovie.common.Movie;
import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Solicita la información de las películas po[ulares
 */
public class GetPopular extends UseCase {

    private MovieRepository movieRepository;

    @Inject
    public GetPopular(@Named("executor_thread") Scheduler executorThread,
                      @Named("ui_thread") Scheduler uiThread,
                      MovieRepository movieRepository) {
        super(executorThread, uiThread);
        this.movieRepository = movieRepository;
    }

    @Override
    protected Observable createObservableUseCase() {
        return movieRepository.getPopular();
    }
}
