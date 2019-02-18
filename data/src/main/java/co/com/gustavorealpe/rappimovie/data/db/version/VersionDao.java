package co.com.gustavorealpe.rappimovie.data.db.version;

import com.dbflow5.annotation.Index;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.com.gustavorealpe.rappimovie.data.db.GenericEntityDao;
import co.com.gustavorealpe.rappimovie.data.db.databases.AppDatabase;

@Singleton
public class VersionDao extends GenericEntityDao<VersionEntity, AppDatabase> {

    @Inject
    public VersionDao() {
    }

    @Override
    protected Class<AppDatabase> getDatabaseClass() {
        return AppDatabase.class;
    }

    @Override
    protected Class<VersionEntity> getEntityClass() {
        return VersionEntity.class;
    }
}
