package xyz.gonzapico.data.entity.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import retrofit2.Response;
import xyz.gonzapico.data.entity.UserEntity;
import xyz.gonzapico.domain.model.UserModelDomain;

/**
 * Created by gfernandez on 25/02/17.
 */

@Singleton public class UserMapper {
  @Inject public UserMapper() {

  }

  public List<UserModelDomain> transformToListOfUsers(Response<List<UserEntity>> listResponse) {
    List<UserModelDomain> listOfUsers = null;
    if (listResponse.isSuccessful()){
      listOfUsers = new ArrayList<>();
      for (UserEntity userEntity : listResponse.body()){
        UserModelDomain userModelDomain = new UserModelDomain();
        userModelDomain.setName(userEntity.getName());

        listOfUsers.add(userModelDomain);
      }
    }
    return listOfUsers;
  }
}
