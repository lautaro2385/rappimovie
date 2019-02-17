package co.com.gustavorealpe.rappymovie.business.movie.usecase;

import java.lang.annotation.Inherited;

import javax.inject.Inject;

import co.com.gustavorealpe.rappymovie.business.movie.repository.MovieRepository;

public class GetUpcoming {

    private MovieRepository repository;

    @Inject
    public GetUpcoming(MovieRepository repository) {
        this.repository = repository;
    }
}
