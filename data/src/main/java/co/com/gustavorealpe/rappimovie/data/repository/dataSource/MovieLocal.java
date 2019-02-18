package co.com.gustavorealpe.rappimovie.data.repository.dataSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.com.gustavorealpe.rappimovie.common.Movie;
import co.com.gustavorealpe.rappimovie.data.db.movie.dao.MovieDao;
import co.com.gustavorealpe.rappimovie.data.db.movie.entity.MovieEntity;
import co.com.gustavorealpe.rappimovie.data.mapper.MovieMapper;
import io.reactivex.Observable;

@Singleton
public class MovieLocal implements MovieDataSource {

    private MovieMapper mapper;
    private MovieDao dao;

    @Inject
    public MovieLocal(MovieMapper mapper, MovieDao dao) {
        this.mapper = mapper;
        this.dao = dao;
    }

    @Override
    public Observable<List<Movie>> getPopular() {

        return null;
    }

    /**
     * Se insertan o actualizan las peliculas en la base de datos
     *
     * @param movies
     */
    @Override
    public void save(List<Movie> movies, int type) {
        List<MovieEntity> me = mapper.model2entity(movies);
        me.forEach(p -> p.setType(type));
        dao.bulkSave(me);
    }
}
