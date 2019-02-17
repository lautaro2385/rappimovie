package co.com.gustavorealpe.rappimovie.data.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by grealpe on 28/06/17.
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Cloud {
}
