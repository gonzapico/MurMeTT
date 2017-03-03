package com.gonzapico.murmett;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.gonzapico.murmett.di.components.UserComponent;
import com.gonzapico.murmett.showUsers.ShowUsersPresenter;
import com.gonzapico.murmett.showUsers.ShowUsersView;
import com.gonzapico.murmett.showUsers.UserModel;
import com.gonzapico.murmett.showUsers.adapter.UsersAdapter;
import java.util.List;
import javax.inject.Inject;
import xyz.gonzapico.murmett.R;

/**
 * Created by gfernandez on 1/03/17.
 */

public class ShowUsersFragment extends BaseMMFragment implements ShowUsersView {

  @Inject ShowUsersPresenter showUsersPresenter;
  @BindView(R.id.rvUsers) RecyclerView rvUsers;
  private UserListListener userListListener;
  private UsersAdapter.OnItemClickListener onItemClickListener =
      new UsersAdapter.OnItemClickListener() {

        @Override public void onPlayPresentationClicked(UserModel userModel) {
          if (ShowUsersFragment.this.showUsersPresenter != null && userModel != null) {
            ShowUsersFragment.this.showUsersPresenter.onPlayPresentationClicked(getActivity(),
                userModel);
          }
        }

        @Override public void onLatestAudioClicked(UserModel userModel) {
          if (ShowUsersFragment.this.showUsersPresenter != null && userModel != null) {
            ShowUsersFragment.this.showUsersPresenter.onLatestAudioClicked(getActivity(),
                userModel);
          }
        }

        @Override public void onRecordAudioClicked(UserModel userModel) {
          if (ShowUsersFragment.this.showUsersPresenter != null && userModel != null) {
            ShowUsersFragment.this.showUsersPresenter.onRecordAudioClicked(userModel, getActivity());
          }
        }
      };

  public ShowUsersFragment() {
    setRetainInstance(true);
  }

  public static ShowUsersFragment newInstance() {
    ShowUsersFragment fragment = new ShowUsersFragment();
    return fragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    this.getComponent(UserComponent.class).inject(this);

    if (getActivity() instanceof UserListListener) {
      userListListener = (UserListListener) getActivity();
    }
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof UserListListener) {
      this.userListListener = (UserListListener) context;
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_show_users, container, false);
    ButterKnife.bind(this, view);
    setUpRecyclerView();

    return view;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    showUsersPresenter.setShowUsersView(this);
    if (savedInstanceState == null) {
      showUsersPresenter.getUsers();
    }
  }

  private void setUpRecyclerView() {
    rvUsers.setHasFixedSize(true);

    LinearLayoutManager llm = new LinearLayoutManager(getActivity());
    rvUsers.setLayoutManager(llm);
  }

  private void setUpAdapter(List<UserModel> users) {
    UsersAdapter usersAdapter = new UsersAdapter(users);
    usersAdapter.setOnUserClickListener(onItemClickListener);
    rvUsers.setAdapter(usersAdapter);
  }

  @Override public void stopAudio() {
    if (this.userListListener != null) {
      this.userListListener.onStopAudio();
    }
  }

  @Override public void renderListOfUsers(List<UserModel> userModelList) {
    setUpAdapter(userModelList);
  }

  @Override public void showRecordView(UserModel userModel) {
    if (this.userListListener != null) {
      this.userListListener.onRecordAudio(userModel);
    }
  }

  @Override public void showErrorMessage(String s) {
    showToastMessage(s);
  }

  @Override public void playPresentationAudio(String pathLastAudio) {
    if (this.userListListener != null) {
      this.userListListener.onPresentationAudioClicked(pathLastAudio);
    }
  }

  @Override public void playLatestAudio(String pathLastAudio) {
    if (this.userListListener != null) {
      this.userListListener.onLatestAudioClicked(pathLastAudio);
    }
  }

  public interface UserListListener {
    void onPresentationAudioClicked(final String audioUrl);

    void onLatestAudioClicked(final String audioSrc);

    void onRecordAudio(final UserModel userModel);

    void onStopAudio();
  }
}
