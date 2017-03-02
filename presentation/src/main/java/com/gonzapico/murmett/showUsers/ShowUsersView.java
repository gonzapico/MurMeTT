package com.gonzapico.murmett.showUsers;

import java.util.List;

/**
 * Created by gfernandez on 26/02/17.
 */

public interface ShowUsersView {

  void renderListOfUsers(List<UserModel> userModelList);

  void showRecordView(UserModel userModel);

  void showErrorMessage(String s);

  void playPresentationAudio(String pathLastAudio);

  void playLatestAudio(String pathLastAudio);
}
