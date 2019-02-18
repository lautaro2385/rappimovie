package co.com.gustavorealpe.rappimovie.data.db.entity;

import com.dbflow5.annotation.Index;
import com.dbflow5.annotation.PrimaryKey;
import com.dbflow5.annotation.Table;
import com.dbflow5.reactivestreams.query.RXModelQueriable;
import com.dbflow5.reactivestreams.structure.BaseRXModel;
import com.dbflow5.structure.BaseModel;

import java.util.List;

import javax.inject.Inject;

import co.com.gustavorealpe.rappimovie.data.db.databases.AppDatabase;
import lombok.Data;

@Data
@Table(database = AppDatabase.class, useBooleanGetterSetters = false)
public class MovieEntity extends BaseModel {
    public final static int POPULAR = 0;
    public final static int UPCOMING = 0;
    public final static int TOP_RATED = 0;

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
    private Integer type;

}
