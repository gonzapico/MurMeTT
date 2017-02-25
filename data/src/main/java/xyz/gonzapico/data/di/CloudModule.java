package xyz.gonzapico.data.di;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.gonzapico.data.cloud.MurMeAPIService;

import static okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS;

/**
 * Created by gfernandez on 25/02/17.
 */

@Module public class CloudModule {

  String mBaseUrl;
  Context context;

  public CloudModule(String baseUrl, Context context) {
    this.mBaseUrl = baseUrl;
    this.context = context;
  }

  @Provides @Singleton HttpLoggingInterceptor provideHttpLoggingInterceptor() {
    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
    httpLoggingInterceptor.setLevel(HEADERS);
    return httpLoggingInterceptor;
  }

  @Provides @Singleton Gson provideGson() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    return gsonBuilder.create();
  }

  @Provides @Singleton Retrofit provideRetrofit(Gson gson, OkHttpClient.Builder okHttpBuilder) {

    Retrofit retrofit =
        new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(mBaseUrl)
            .client(okHttpBuilder.build())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();

    retrofit.create(MurMeAPIService.class);

    return retrofit;
  }

  @Provides @Singleton Cache provideOkHttpCache() {
    int cacheSize = 10 * 1024 * 1024; // 10 MiB
    return new Cache(this.context.getCacheDir(), cacheSize);
  }

  @Provides @Singleton OkHttpClient.Builder provideOkHttpClient(Cache cache,
      HttpLoggingInterceptor loggingInterceptor) {
    OkHttpClient.Builder client = new OkHttpClient.Builder();

    client.addInterceptor(loggingInterceptor);
    client.cache(cache);
    return client;
  }
}
