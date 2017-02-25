package xyz.gonzapico.data.cloud;

import java.util.List;
import retrofit2.Response;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import xyz.gonzapico.data.entity.UserEntity;

/**
 * Created by gfernandez on 25/02/17.
 */

public interface MurMeAPIService {

  @Headers({
      "language:es_ES", "Content-Type:application/x-www-form-urlencoded"
  }) @POST("/{config}/") rx.Observable<Response<List<UserEntity>>> users(
      @Path(value = "keyAccess", encoded = true) String keyAccess,
      @Path(value = "pushToken", encoded = true) String pushToken,
      @Path(value = "idUser", encoded = true) String idUser);
}
