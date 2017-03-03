package xyz.gonzapico.data.repository.datasource;

import android.content.Context;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by gfernandez on 25/02/17.
 */

@Singleton public class UserDataStoreFactory {

  private final Context context;

  @Inject public UserDataStoreFactory(Context context) {
    if (context == null) {
      throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
    }
    this.context = context.getApplicationContext();
  }

  /**
   * Create {@link UserDataStore} to retrieve data from the Cloud.
   */
  public UserDataStore createCloudDataStore() {

    return new CloudUserStore(context);
  }
}
