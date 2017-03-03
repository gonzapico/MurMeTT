package xyz.gonzapico.data.cloud;

import retrofit2.Response;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import xyz.gonzapico.data.Config;
import xyz.gonzapico.data.entity.ResponseAPIUsers;

/**
 * Created by gfernandez on 25/02/17.
 */

public interface MurMeAPIService {

  @Headers({
      "Content-Type: application/x-www-form-urlencoded", "idUser: " + Config.ID_USER,
      "pushToken: " + Config.PUSH_TOKEN, "keyAccess: " + Config.KEY_ACCESS,
      "language: " + Config.LANGUAGE
  }) @POST("/api/v1/Conversations/GetContacts") rx.Observable<Response<ResponseAPIUsers>> users();
}
