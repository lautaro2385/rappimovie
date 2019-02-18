package co.com.gustavorealpe.rappimovie.data.db.version;

import com.dbflow5.annotation.PrimaryKey;
import com.dbflow5.annotation.Table;
import com.dbflow5.structure.BaseModel;

import java.util.Date;

import co.com.gustavorealpe.rappimovie.data.db.databases.AppDatabase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(database = AppDatabase.class, useBooleanGetterSetters = false)
public class VersionEntity  extends BaseModel {
    public static final Integer MOVIES = 0;

    @PrimaryKey
    private Integer idTable;

    private Date updateDate;

}
