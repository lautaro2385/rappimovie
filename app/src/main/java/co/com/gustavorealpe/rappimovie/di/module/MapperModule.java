package co.com.gustavorealpe.rappimovie.di.module;

import org.mapstruct.factory.Mappers;

import co.com.gustavorealpe.rappimovie.data.mapper.MovieMapper;
import dagger.Module;
import dagger.Provides;

@Module
public class MapperModule {
    public MapperModule() {
    }

    @Provides
    MovieMapper provideMovieMapper(){
        return Mappers.getMapper(MovieMapper.class);
    }
}
