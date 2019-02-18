package co.com.gustavorealpe.rappimovie.di.module;

import javax.inject.Singleton;

//import co.com.gustavorealpe.data.net.ApiConstants;
//import co.com.gustavorealpe.data.net.interceptor.AddAuthentication;
import co.com.gustavorealpe.rappimovie.App;
import co.com.gustavorealpe.rappimovie.data.net.ApiConstants;
import co.com.gustavorealpe.rappimovie.data.net.movie.MovieService;
import co.com.gustavorealpe.rappimovie.data.net.interceptor.AddAuthentication;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {
    private final static String BASE_URL = ApiConstants.ENDPOINT;
    private final App application;

    public NetModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(){
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(),cacheSize);
        return cache;
    }

    @Provides
    OkHttpClient provideHttpClient(Cache cache){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new AddAuthentication());
        //httpClient.addInterceptor(new ReceivedCookiesInterceptor());
        httpClient.addInterceptor(logging);
        //httpClient.addNetworkInterceptor(new StethoInterceptor());
        //httpClient.connectTimeout(15, TimeUnit.SECONDS);
        //httpClient.readTimeout(15,TimeUnit.SECONDS);
        //httpClient.cache(cache);
        OkHttpClient client = httpClient.build();


//        if (BuildConfig.DEBUG) {
        //           client.networkInterceptors().add(new StethoInterceptor());
//        }
        return client;
    }

    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient){
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    MovieService provideMovieService(Retrofit retrofit){
        return retrofit.create(MovieService.class);
    }
}
