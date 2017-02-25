package xyz.gonzapico.data.entity.mapper;

import java.util.List;
import retrofit2.Response;
import xyz.gonzapico.data.entity.UserEntity;
import xyz.gonzapico.domain.model.UserModelDomain;

/**
 * Created by gfernandez on 25/02/17.
 */

public class UserMapper {
  public List<UserModelDomain> transformToListOfUsers(Response<List<UserEntity>> listResponse) {
    return null;
  }
}
