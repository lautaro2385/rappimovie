package co.com.gustavorealpe.rappimovie.data.db;

import com.dbflow5.config.DBFlowDatabase;
import com.dbflow5.config.FlowManager;
import com.dbflow5.database.DatabaseWrapper;
import com.dbflow5.query.SQLOperator;
import com.dbflow5.query.SQLite;
import com.dbflow5.structure.BaseModel;
import com.dbflow5.transaction.ProcessModelTransaction;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import co.com.gustavorealpe.rappimovie.data.db.movie.entity.MovieEntity;
import co.com.gustavorealpe.rappimovie.data.db.movie.entity.MovieEntity_Table;
import kotlin.Unit;

public abstract class GenericEntityDao<Q extends BaseModel, T extends DBFlowDatabase> {

    protected abstract Class<T> getDatabaseClass();

    protected abstract Class<Q> getEntityClass();

    DatabaseWrapper dw = FlowManager.getDatabase(getDatabaseClass()).getWritableDatabase();

    /**
     * Hace la carga de la entidad por id
     * @param entity
     * @return
     */
    public Q load(Q entity) {
        return entity.load(dw);
        //return FlowManager.getModelAdapter(getEntityClass()).load(entity, dw);
    }

    /**
     * Inserta una entidad
     * @param entity
     * @return
     */
    public long insert(Q entity) {
        return entity.insert(dw);
    }

    /**
     * Actualiza una entidad
     * @param entity
     * @return
     */
    public boolean update(Q entity){
        return entity.update(dw);
    }

    /**
     * Actualiza o inserta una entidad
     * @param entity
     * @return
     */
    public boolean save(Q entity){
        return entity.save(dw);
    }

    /**
     * Actualiza en bloque
     * @param items
     * @return
     */
    public Unit bulkSave(List<Q> items){
        ProcessModelTransaction<Q> processModelTransaction =
                new ProcessModelTransaction.Builder<>(Q::save).addAll(items).build();
        return FlowManager.getDatabase(getDatabaseClass()).executeTransaction(processModelTransaction);
    }

    public List<Q> selectAll(SQLOperator sqlOperator){
        return SQLite.select().from(getEntityClass()).where(sqlOperator).queryList(dw);
    }
}
