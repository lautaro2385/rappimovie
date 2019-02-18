package co.com.gustavorealpe.rappimovie.data.net.movie;

import co.com.gustavorealpe.rappimovie.data.net.GenericResponseDTO;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MovieService {
    @GET("movie/upcoming")
    Observable<GenericResponseDTO<MovieDTO>> getUpcoming();

    @GET("movie/popular")
    Observable<GenericResponseDTO<MovieDTO>> getPopular();

    @GET("movie/top_rated")
    Observable<GenericResponseDTO<MovieDTO>> getTopRated();
}
