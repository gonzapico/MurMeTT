package com.gonzapico.murmett.showUsers;

import java.util.List;

/**
 * Created by gfernandez on 26/02/17.
 */

public interface ShowUsersView {

  void renderListOfUsers(List<UserModel> userModelList);
}
