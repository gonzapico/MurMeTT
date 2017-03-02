package com.gonzapico.murmett.di.modules;

import com.gonzapico.murmett.di.PerActivity;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import xyz.gonzapico.domain.interactor.BaseUseCase;
import xyz.gonzapico.domain.interactor.GetUsers;

/**
 * /**
 * Dagger module that provides user related collaborators.
 */
@Module public class UserModule {

  public UserModule() {

  }

  @Provides @PerActivity @Named("users") BaseUseCase provideGetUsersUseCase(
      GetUsers getUsers) {
    return getUsers;
  }
}
