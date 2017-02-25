package xyz.gonzapico.data.repository.datasource;

import java.util.List;
import retrofit2.Response;
import rx.Observable;
import xyz.gonzapico.data.entity.UserEntity;

/**
 * Created by gfernandez on 25/02/17.
 */

public class CloudUserStore implements UserDataStore {
  @Override public Observable<Response<List<UserEntity>>> users() {
    return null;
  }
}
