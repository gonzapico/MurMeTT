package xyz.gonzapico.data.cloud;

import java.util.List;
import retrofit2.Response;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import xyz.gonzapico.data.entity.UserEntity;

/**
 * Created by gfernandez on 25/02/17.
 */

public interface MurMeAPIService {

  @Headers("Content-Type: application/x-www-form-urlencoded")
  @POST("/api/v1/Conversations/GetContacts") rx.Observable<Response<List<UserEntity>>> users(
      @Query(value = "keyAccess", encoded = true) String keyAccess,
      @Query(value = "pushToken", encoded = true) String pushToken,
      @Query(value = "idUser", encoded = true) String idUser,
      @Query(value = "language", encoded = true) String language);
}
