package co.com.gustavorealpe.rappimovie.data.repository.dataSource;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.com.gustavorealpe.rappimovie.common.Movie;
import co.com.gustavorealpe.rappimovie.data.db.movie.dao.MovieDao;
import co.com.gustavorealpe.rappimovie.data.db.movie.entity.MovieEntity;
import co.com.gustavorealpe.rappimovie.data.db.movie.entity.MovieEntity_Table;
import co.com.gustavorealpe.rappimovie.data.db.version.VersionDao;
import co.com.gustavorealpe.rappimovie.data.db.version.VersionEntity;
import co.com.gustavorealpe.rappimovie.data.mapper.MovieMapper;
import io.reactivex.Observable;

@Singleton
public class MovieLocal implements MovieDataSource {

    private MovieMapper mapper;
    private MovieDao dao;
    private VersionDao versionDao;

    @Inject
    public MovieLocal(MovieMapper mapper,
                      MovieDao dao,
                      VersionDao versionDao) {
        this.mapper = mapper;
        this.dao = dao;
        this.versionDao = versionDao;
    }

    /**
     * Obtiene las peliculas pupulares
     *
     * @return
     */
    @Override
    public Observable<List<Movie>> getPopular() {
        return getMovies(MovieEntity.POPULAR);
    }

    @Override
    public Observable<List<Movie>> getUpcoming() {
        return getMovies(MovieEntity.UPCOMING);
    }

    @Override
    public Observable<List<Movie>> getTopRated() {
        return getMovies(MovieEntity.TOP_RATED);
    }

    private Observable<List<Movie>> getMovies(Integer Category){
        return Observable.create(emitter -> {
            List<MovieEntity> data = dao.selectAll(MovieEntity_Table.type.eq(Category), MovieEntity_Table.updateDate, true);
            List<Movie> movies = mapper.entity2model(data);
            emitter.onNext(movies);
            emitter.onComplete();
        });
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
        // Actualiza la versión de la tabla de películas
        VersionEntity version = new VersionEntity(type, new Date());
        versionDao.save(version);
    }

    @Override
    public Observable<Movie> getMovieById(Integer id) {
        return Observable.create(emitter -> {
            MovieEntity me =new MovieEntity();
            me.setId(id);
            Movie movie = mapper.entity2model(dao.load(me));
            emitter.onNext(movie);
            emitter.onComplete();
        });
    }

    @Override
    public Observable<List<Movie>> searchMovie(String search) {
        return Observable.create(emitter -> {
            List<MovieEntity> data = dao.searchMovies(search);
            List<Movie> movies = mapper.entity2model(data);
            emitter.onNext(movies);
            emitter.onComplete();
        });
    }
}
