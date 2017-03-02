package com.gonzapico.murmett.showUsers;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.gonzapico.murmett.exception.AudioNotFoundException;
import com.gonzapico.murmett.exception.ErrorMessageFactory;
import com.gonzapico.murmett.navigation.Navigator;
import com.gonzapico.murmett.showUsers.mapper.DomainUserMapper;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import xyz.gonzapico.domain.exception.DefaultErrorBundle;
import xyz.gonzapico.domain.interactor.BaseUseCase;
import xyz.gonzapico.domain.interactor.DefaultSubscriber;
import xyz.gonzapico.domain.interactor.GetUsers;
import xyz.gonzapico.domain.model.UserModelDomain;

/**
 * Created by gfernandez on 26/02/17.
 */

public class ShowUsersPresenter {

  private BaseUseCase getUsersUseCase;

  private DomainUserMapper domainUserMapper;

  private ShowUsersView mShowUsersView;

  @Inject public ShowUsersPresenter(@Named("users") BaseUseCase useCaseGetUsers,
      DomainUserMapper domainMovieMapper) {
    this.getUsersUseCase = useCaseGetUsers;
    this.domainUserMapper = domainMovieMapper;
  }

  public void getUsers() {
    ((GetUsers) this.getUsersUseCase).setParameters(
        domainUserMapper.getParamsAPIModel("M_rM3Q", "es_ES",
            "e2FDw4A_OyQ:APA91bERDeEBdys28VminIdDslu7as1lOHS_8kSoQgtXSyCxu6AGWMj_cTFuTu74O2e7pE6Ds_Jb7rlaBo3z0v1Eyj_d2N2WjZTZ8eSkCHKvYuIYEVDubL7Yc86otoy0TpV5YYThQ8v2",
            "application/x-www-form-urlencoded", "3f797096-8ddd-4aaa-8135-66f652c8a8b2"));
    this.getUsersUseCase.execute(new GetUsersSuscriber());
  }

  public void setShowUsersView(ShowUsersView showUsersView) {
    this.mShowUsersView = showUsersView;
  }

  private void showListOfUsers(List<UserModelDomain> userDomainEntityList) {
    if (userDomainEntityList == null) {
      mShowUsersView.renderListOfUsers(fakeListOfUsers());
    } else {
      mShowUsersView.renderListOfUsers(
          domainUserMapper.transformToListOfUsers(userDomainEntityList));
    }
  }

  private List<UserModel> fakeListOfUsers() {
    List<UserModel> fakeList = new ArrayList<>();

    UserModel userModelOne = new UserModel();
    userModelOne.setName("paco");
    fakeList.add(userModelOne);

    return fakeList;
  }

  public void onRecordAudioClicked(UserModel userModel, Context context) {
    Navigator navigator = new Navigator();
    navigator.navigateToRecordView(context, userModel.getName());
    //mShowUsersView.showRecordView(userModel);
  }

  public void onPlayPresentationClicked(Context context, UserModel userModel) {
    if (TextUtils.isEmpty(userModel.getUrlPresentation())) {
      mShowUsersView.showErrorMessage(
          ErrorMessageFactory.create(context, new AudioNotFoundException()));
    } else {
      mShowUsersView.playPresentationAudio(userModel.getPathLastAudio());
    }
  }

  public void onLatestAudioClicked(Context context, UserModel userModel) {
    try {
      String path = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC).getAbsolutePath();
      path = path + "/mm_" + userModel.getName() + ".3gp";

      mShowUsersView.playLatestAudio(path);
    } catch (Exception e) {
      mShowUsersView.showErrorMessage(
          ErrorMessageFactory.create(context, new AudioNotFoundException()));
    }
  }

  private final class GetUsersSuscriber extends DefaultSubscriber<List<UserModelDomain>> {
    public GetUsersSuscriber() {

    }

    @Override public void onCompleted() {

    }

    @Override public void onError(Throwable e) {
      showErrorMessage(new DefaultErrorBundle((Exception) e));

      ShowUsersPresenter.this.showListOfUsers(null);
    }

    @Override public void onNext(List<UserModelDomain> userDomainEntityList) {
      ShowUsersPresenter.this.showListOfUsers(userDomainEntityList);
    }

    private void showErrorMessage(DefaultErrorBundle errorBundle) {
      String errorMessage = ErrorMessageFactory.create(null, errorBundle.getException());
      mShowUsersView.showErrorMessage(errorMessage);
    }
  }
}
