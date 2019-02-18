package co.com.gustavorealpe.rappimovie.domain.movie.usecase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import co.com.gustavorealpe.rappimovie.common.Movie;
import co.com.gustavorealpe.rappimovie.domain.UseCase;
import co.com.gustavorealpe.rappimovie.domain.movie.repository.MovieRepository;
import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * solicita la lista de las peliculas próximas a salir
 */
public class GetUpcoming extends UseCase<List<Movie>> {

    private MovieRepository repository;

    @Inject
    public GetUpcoming(@Named("executor_thread") Scheduler executorThread,
                       @Named("ui_thread") Scheduler uiThread,
                       MovieRepository repository) {
        super(executorThread, uiThread);
        this.repository = repository;
    }

    @Override
    protected Observable<List<Movie>> createObservableUseCase() {
        return repository.getUpcoming();
    }
}
