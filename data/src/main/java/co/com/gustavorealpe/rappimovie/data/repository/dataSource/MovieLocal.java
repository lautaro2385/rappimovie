package co.com.gustavorealpe.rappimovie.data.repository.dataSource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MovieLocal  implements MovieDataSource {

    @Inject
    public MovieLocal(){}

    @Override
    public void getMovies() {

    }
}
