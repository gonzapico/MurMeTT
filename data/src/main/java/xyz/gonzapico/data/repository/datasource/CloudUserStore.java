package xyz.gonzapico.data.repository.datasource;

import android.content.Context;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import xyz.gonzapico.data.Config;
import xyz.gonzapico.data.cloud.MurMeAPIService;
import xyz.gonzapico.data.di.CloudModule;
import xyz.gonzapico.data.di.DaggerCloudComponent;
import xyz.gonzapico.data.entity.UserEntity;

/**
 * Created by gfernandez on 25/02/17.
 */

public class CloudUserStore implements UserDataStore {

  @Inject Retrofit retrofit;
  private MurMeAPIService restApi;
  private Context context;

  /**
   * Construct a {@link CloudUserStore} based on connections to the api (Cloud).
   */
  @Inject public CloudUserStore(Context context) {
    this.context = context;
    initDagger();
    initRetrofit();
  }

  private void initRetrofit() {
    restApi = retrofit.create(MurMeAPIService.class);
  }

  private void initDagger() {
    DaggerCloudComponent.builder()
        // list of modules that are part of this component need to be created here too
        .cloudModule(new CloudModule(Config.API_URL_BASE, this.context)).build().inject(this);
  }

  @Override public Observable<Response<List<UserEntity>>> users() {
    return restApi.users("", "", "");
  }
}
