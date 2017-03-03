package xyz.gonzapico.data.repository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import retrofit2.Response;
import rx.Observable;
import xyz.gonzapico.data.entity.Contact;
import xyz.gonzapico.data.entity.ResponseAPIUsers;
import xyz.gonzapico.data.entity.UserEntity;
import xyz.gonzapico.data.entity.mapper.UserMapper;
import xyz.gonzapico.data.repository.datasource.UserDataStore;
import xyz.gonzapico.data.repository.datasource.UserDataStoreFactory;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Created by gfernandez on 3/03/17.
 */
public class UsersRepositoryTest {

  @Rule public ExpectedException expectedException = ExpectedException.none();
  private UsersRepository usersRepository;
  @Mock private UserDataStoreFactory mockUserDataStoreFactory;
  @Mock private UserMapper mockUserEntityDataMapper;
  @Mock private UserDataStore mockUserDataStore;
  @Mock private UserEntity mockUserEntity;
  @Mock private Contact mockUser;

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);
    usersRepository = new UsersRepository(mockUserDataStoreFactory, mockUserEntityDataMapper);

    given(mockUserDataStoreFactory.createCloudDataStore()).willReturn(mockUserDataStore);
  }

  @Test public void getUsers() throws Exception {
    Response<ResponseAPIUsers> entityAPIResponse = Response.success(new ResponseAPIUsers());
    given(mockUserDataStore.users()).willReturn(Observable.just(entityAPIResponse));

    usersRepository.getUsers();

    verify(mockUserDataStoreFactory).createCloudDataStore();
    verify(mockUserDataStore).users();
  }
}