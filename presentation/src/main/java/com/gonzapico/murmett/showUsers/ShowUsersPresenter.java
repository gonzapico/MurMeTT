package com.gonzapico.murmett.showUsers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gfernandez on 26/02/17.
 */

public class ShowUsersPresenter {

  private List<UserModel> listOfUsers = new ArrayList<>();
  private ShowUsersView mShowUsersView;

  public void getUsers() {
    mShowUsersView.renderListOfUsers(listOfUsers);
  }

  public void setShowUsersView(ShowUsersView showUsersView) {
    this.mShowUsersView = showUsersView;
  }
}
