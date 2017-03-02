package xyz.gonzapico.data.repository;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import xyz.gonzapico.data.entity.mapper.UserMapper;
import xyz.gonzapico.data.repository.datasource.UserDataStore;
import xyz.gonzapico.data.repository.datasource.UserDataStoreFactory;
import xyz.gonzapico.domain.model.RequestAPIModelDomain;
import xyz.gonzapico.domain.model.UserModelDomain;
import xyz.gonzapico.domain.repository.UsersDomainRepository;

/**
 * Created by gfernandez on 25/02/17.
 */

@Singleton public class UsersRepository implements UsersDomainRepository {

  private final UserDataStoreFactory userDataStoreFactory;
  private final UserMapper userMapper;

  @Inject public UsersRepository(UserDataStoreFactory userDataStoreFactory, UserMapper userMapper) {
    this.userDataStoreFactory = userDataStoreFactory;
    this.userMapper = userMapper;
  }

  @Override public Observable<List<UserModelDomain>> getUsers(RequestAPIModelDomain requestAPIModelDomain) {
    final UserDataStore userDataStore = this.userDataStoreFactory.createCloudDataStore();
    return userDataStore.users(requestAPIModelDomain).map(this.userMapper::transformToListOfUsers);
  }
}
