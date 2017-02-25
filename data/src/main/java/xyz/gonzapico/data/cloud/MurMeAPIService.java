package xyz.gonzapico.data.cloud;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by gfernandez on 25/02/17.
 */

public interface MurMeAPIService {

  @Headers({
      "User-Agent : "
  }) @POST("/{config}/") rx.Observable<Response<EntityConfiguration>> config(
      @Path(value = "config", encoded = true) String section);

}
