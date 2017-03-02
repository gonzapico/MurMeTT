package xyz.gonzapico.data.repository.datasource;

import java.util.List;
import retrofit2.Response;
import rx.Observable;
import xyz.gonzapico.data.entity.UserEntity;
import xyz.gonzapico.domain.model.RequestAPIModelDomain;

/**
 * Created by gfernandez on 25/02/17.
 */

public interface UserDataStore {

  /***
   * Get an {@link Observable} which will emit a {@link List} of {@link UserEntity}.
   * @param requestAPIModelDomain with the params to send in the request
   * @return
   */
  Observable<Response<List<UserEntity>>> users(RequestAPIModelDomain requestAPIModelDomain);
}
