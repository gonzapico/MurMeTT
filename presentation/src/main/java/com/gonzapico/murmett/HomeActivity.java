package com.gonzapico.murmett;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import com.gonzapico.murmett.showUsers.ShowUsersPresenter;
import com.gonzapico.murmett.showUsers.ShowUsersView;
import com.gonzapico.murmett.showUsers.UserModel;
import java.util.List;
import xyz.gonzapico.murmett.R;

public class HomeActivity extends BaseMMActivity implements ShowUsersView {

  private ShowUsersPresenter showUsersPresenter;
  @BindView(R.id.rvUsers) RecyclerView rvUsers;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setUpRecyclerView(rvUsers);
    showUsersPresenter = new ShowUsersPresenter();
    showUsersPresenter.setShowUsersView(this);
    showUsersPresenter.getUsers();
  }

  @Override public int getLayoutResource() {
    return R.layout.activity_home;
  }

  @Override public void renderListOfUsers(List<UserModel> userModelList) {

  }

  private void setUpRecyclerView(RecyclerView rv) {
    rv.setHasFixedSize(true);

    LinearLayoutManager llm = new LinearLayoutManager(this);
    rv.setLayoutManager(llm);
  }

  private void setUpAdapter() {
  }
}
