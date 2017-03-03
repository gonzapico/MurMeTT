package xyz.gonzapico.data.entity.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import retrofit2.Response;
import xyz.gonzapico.data.entity.Contact;
import xyz.gonzapico.data.entity.ResponseAPIUsers;
import xyz.gonzapico.domain.model.UserModelDomain;

/**
 * Created by gfernandez on 25/02/17.
 */

@Singleton public class UserMapper {
  @Inject public UserMapper() {

  }

  public List<UserModelDomain> transformToListOfUsers(Response<ResponseAPIUsers> listResponse) {
    List<UserModelDomain> listOfUsers = null;
    if (listResponse.isSuccessful()) {
      listOfUsers = new ArrayList<>();
      listOfUsers.addAll(transformContactListToUserList(listResponse.body().getContacts()));
    }
    return listOfUsers;
  }

  private Collection<UserModelDomain> transformContactListToUserList(List<Contact> contacts) {
    List<UserModelDomain> resultOfTransformation = new ArrayList<>();

    for (Contact contact : contacts){
      UserModelDomain userModelDomain = new UserModelDomain();

      userModelDomain.setAlias(contact.getAlias());
      userModelDomain.setIdUser(contact.getIdUser());
      userModelDomain.setPresentation(contact.getPresentation());
      resultOfTransformation.add(userModelDomain);
    }

    return resultOfTransformation;
  }
}
