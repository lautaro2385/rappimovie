package co.com.gustavorealpe.rappimovie.domain.movie.usecase;

import javax.inject.Inject;

import co.com.gustavorealpe.rappimovie.domain.movie.repository.MovieRepository;

public class GetUpcoming {

    private MovieRepository repository;

    @Inject
    public GetUpcoming(MovieRepository repository) {
        this.repository = repository;
    }
}
