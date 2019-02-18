package co.com.gustavorealpe.rappimovie.data.db.entity;

import com.dbflow5.annotation.PrimaryKey;
import com.dbflow5.annotation.Table;

import java.util.List;

import co.com.gustavorealpe.rappimovie.data.db.databases.AppDatabase;
import lombok.Data;

@Data
@Table(database = AppDatabase.class, allFields = true, useBooleanGetterSetters = false)
public class MovieEntity {
    @PrimaryKey
    private Integer id;
    private Integer voteCount;
    private Boolean video;
    private Double voteAverage;
    private String title;
    private Double popularity;
    private String posterPath;
    private String originalLanguage;
    private String originalTitle;
    //private List<Integer> genreIds = null;
    private String backdropPath;
    private Boolean adult;
    private String overview;
    private String releaseDate;
}
