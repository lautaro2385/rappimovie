package co.com.gustavorealpe.rappymovie.data.repository.dataSource;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.com.gustavorealpe.rappymovie.data.net.GenericResponseDTO;
import co.com.gustavorealpe.rappymovie.data.net.movie.MovieDTO;
import co.com.gustavorealpe.rappymovie.data.net.movie.MovieService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class MovieCloud implements  MovieDataSource{
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
