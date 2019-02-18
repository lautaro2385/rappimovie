package co.com.gustavorealpe.rappimovie.common;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Movie {
    private Integer voteCount;
    private Integer id;
    private Boolean video;
    private Double voteAverage;
    private String title;
    private Double popularity;
    private String posterPath;
    private String originalLanguage;
    private String originalTitle;
    private List<Integer> genreIds = null;
    private String backdropPath;
    private Boolean adult;
    private String overview;
    private Date releaseDate;
}
