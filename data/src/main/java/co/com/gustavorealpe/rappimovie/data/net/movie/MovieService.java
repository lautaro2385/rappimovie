package co.com.gustavorealpe.rappimovie.data.net.movie;

import co.com.gustavorealpe.rappimovie.data.net.GenericResponseDTO;
import retrofit2.http.GET;
import rx.Observable;

public interface MovieService {
    @GET("movie/upcoming")
    Observable<GenericResponseDTO<MovieDTO>> getUpcoming();
}
