package co.com.gustavorealpe.rappimovie.data.net.interceptor;

import java.io.IOException;

import co.com.gustavorealpe.rappimovie.data.net.ApiConstants;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddAuthentication implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
/*
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("Authorization", "Bearer "+ApiConstants.ACCESS_TOKEN);
        return chain.proceed(builder.build());

  */
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder()
                .addQueryParameter("api_key",ApiConstants.API_KEY)
                .addQueryParameter("language","es-MX")
                .build();
        request = request.newBuilder()
                .url(url)
                .addHeader("Authorization", "Bearer "+ApiConstants.ACCESS_TOKEN)
                .build();
        return chain.proceed(request);

    }
}
