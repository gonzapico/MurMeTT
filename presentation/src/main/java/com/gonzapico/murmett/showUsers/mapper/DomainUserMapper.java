package com.gonzapico.murmett.showUsers.mapper;

import com.gonzapico.murmett.di.PerActivity;
import com.gonzapico.murmett.showUsers.UserModel;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import xyz.gonzapico.domain.model.RequestAPIModelDomain;
import xyz.gonzapico.domain.model.UserModelDomain;

/**
 * Created by gfernandez on 1/03/17.
 */

@PerActivity public class DomainUserMapper {

  @Inject public DomainUserMapper() {

  }

  public List<UserModel> transformToListOfUsers(List<UserModelDomain> userDomainEntityList) {
    List<UserModel> userModelListTransformation = new ArrayList<>();

    for (UserModelDomain userModelDomain : userDomainEntityList) {
      UserModel userModel = new UserModel();
      userModelListTransformation.add(userModel);
    }

    return userModelListTransformation;
  }

  public RequestAPIModelDomain getParamsAPIModel(String keyAccess, String language,
      String pushToken, String contentType, String idUser) {
    RequestAPIModelDomain requestAPIModelDomain = new RequestAPIModelDomain();

    requestAPIModelDomain.setIdUser(idUser);
    requestAPIModelDomain.setContentType(contentType);
    requestAPIModelDomain.setPushToken(pushToken);
    requestAPIModelDomain.setLanguage(language);
    requestAPIModelDomain.setKeyAccess(keyAccess);
    return requestAPIModelDomain;
  }
}
