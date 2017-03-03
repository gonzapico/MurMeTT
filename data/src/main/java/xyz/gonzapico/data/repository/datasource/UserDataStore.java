package xyz.gonzapico.data.repository.datasource;

import java.util.List;
import retrofit2.Response;
import rx.Observable;
import xyz.gonzapico.data.entity.ResponseAPIUsers;
import xyz.gonzapico.data.entity.UserEntity;

/**
 * Created by gfernandez on 25/02/17.
 */

public interface UserDataStore {

  /***
   * Get an {@link Observable} which will emit a {@link List} of {@link UserEntity}.
   * @return
   */
  Observable<Response<ResponseAPIUsers>> users();
}
