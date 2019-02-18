package co.com.gustavorealpe.rappimovie.data.mapper;

import org.mapstruct.Mapper;

import java.util.List;

import co.com.gustavorealpe.rappimovie.common.Movie;
import co.com.gustavorealpe.rappimovie.data.net.movie.MovieDTO;

@Mapper
public interface MovieMapper {
    Movie dto2model(MovieDTO dto);
    List<Movie> dto2model(List<MovieDTO> dtos);
}
