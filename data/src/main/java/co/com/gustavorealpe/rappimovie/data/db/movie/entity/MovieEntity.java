package co.com.gustavorealpe.rappimovie.data.db.movie.entity;

import com.dbflow5.annotation.Index;
import com.dbflow5.annotation.PrimaryKey;
import com.dbflow5.annotation.Table;
import com.dbflow5.structure.BaseModel;

import java.util.Date;

import javax.inject.Inject;

import co.com.gustavorealpe.rappimovie.data.db.databases.AppDatabase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(database = AppDatabase.class, useBooleanGetterSetters = false)
public class MovieEntity extends BaseModel {
    public final static int POPULAR = 0;
    public final static int UPCOMING = 1;
    public final static int TOP_RATED = 2;

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

    @Index
    private Date updateDate = new Date();

    @Index
    private Integer type;

}
