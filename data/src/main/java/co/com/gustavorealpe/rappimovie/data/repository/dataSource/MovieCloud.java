package co.com.gustavorealpe.rappimovie.data.repository.dataSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.com.gustavorealpe.rappimovie.common.Movie;
import co.com.gustavorealpe.rappimovie.data.mapper.MovieMapper;
import co.com.gustavorealpe.rappimovie.data.net.movie.MovieService;
import io.reactivex.Observable;

@Singleton
public class MovieCloud implements MovieDataSource {
    private MovieService movieService;
    private MovieMapper movieMapper;

    @Inject
    public MovieCloud(MovieService movieService,
                      MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }


    @Override
    public Observable<List<Movie>> getPopular() {
        return this.movieService.getPopular().map(dto -> movieMapper.dto2model(dto.getResults()));
    }

    @Override
    public Observable<List<Movie>> getUpcoming() {
        return this.movieService.getUpcoming().map(dto -> movieMapper.dto2model(dto.getResults()));
    }

    @Override
    public Observable<List<Movie>> getTopRated() {
        return this.movieService.getTopRated().map(dto -> movieMapper.dto2model(dto.getResults()));
    }

    @Override
    public void save(List<Movie> movies, int type) {
        throw new UnsupportedOperationException();
    }
}
