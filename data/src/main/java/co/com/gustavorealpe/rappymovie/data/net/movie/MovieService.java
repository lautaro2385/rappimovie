package co.com.gustavorealpe.rappymovie.data.net.movie;

import co.com.gustavorealpe.rappymovie.data.net.GenericResponseDTO;
import retrofit2.http.GET;
import rx.Observable;

public interface MovieService {
    @GET("movie/upcoming")
    Observable<GenericResponseDTO<MovieDTO>> getUpcoming();
}
