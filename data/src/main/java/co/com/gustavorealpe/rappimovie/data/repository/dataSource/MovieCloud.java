package co.com.gustavorealpe.rappimovie.data.repository.dataSource;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.com.gustavorealpe.rappimovie.data.net.movie.MovieService;

@Singleton
public class MovieCloud implements MovieDataSource {
    private MovieService movieService;

    @Inject
    public MovieCloud(MovieService movieService){
        this.movieService = movieService;
    }


    @Override
    public void getMovies() {
        /*this.movieService.getUpcoming().enqueue(new Callback<GenericResponseDTO<MovieDTO>>() {
            @Override
            public void onResponse(Call<GenericResponseDTO<MovieDTO>> call, Response<GenericResponseDTO<MovieDTO>> response) {

            }

            @Override
            public void onFailure(Call<GenericResponseDTO<MovieDTO>> call, Throwable t) {

            }
        });*/
    }
}
