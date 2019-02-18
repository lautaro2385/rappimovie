package co.com.gustavorealpe.rappimovie.data.db.movie.dao;


import javax.inject.Inject;
import javax.inject.Singleton;

import co.com.gustavorealpe.rappimovie.data.db.GenericEntityDao;
import co.com.gustavorealpe.rappimovie.data.db.databases.AppDatabase;
import co.com.gustavorealpe.rappimovie.data.db.movie.entity.MovieEntity;

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
}
