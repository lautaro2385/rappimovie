package co.com.gustavorealpe.rappimovie.data.db.movie.dao;


import com.dbflow5.query.SQLite;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.com.gustavorealpe.rappimovie.data.db.GenericEntityDao;
import co.com.gustavorealpe.rappimovie.data.db.databases.AppDatabase;
import co.com.gustavorealpe.rappimovie.data.db.movie.entity.MovieEntity;
import co.com.gustavorealpe.rappimovie.data.db.movie.entity.MovieEntity_Table;

@Singleton
public class MovieDao extends GenericEntityDao<MovieEntity, AppDatabase> {

    @Inject
    public MovieDao() {
    }

    @Override
    protected Class<AppDatabase> getDatabaseClass() {
        return AppDatabase.class;
    }

    @Override
    protected Class<MovieEntity> getEntityClass() {
        return MovieEntity.class;
    }

    public List<MovieEntity> searchMovies(String data) {
        data = "%"+data.replace(" ", "%")+"%";
        return SQLite.select().from(getEntityClass())
                .where(MovieEntity_Table.originalTitle.like(data))
                .or(MovieEntity_Table.title.like(data))
                .queryList(dw);
    }
}
