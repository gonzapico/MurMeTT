package com.gonzapico.murmett.showUsers.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gonzapico.murmett.showUsers.UserModel;
import java.util.List;
import xyz.gonzapico.murmett.R;

/**
 * Created by gfernandez on 26/02/17.
 */

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {
  private List<UserModel> listOfUsers;
  private Context mContext;

  public UsersAdapter(List<UserModel> userModelList) {
    listOfUsers = userModelList;
  }

  @Override public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    mContext = parent.getContext();
    View v = LayoutInflater.from(mContext).inflate(R.layout.user_row, parent, false);
    return new UserViewHolder(v);
  }

  @Override public void onBindViewHolder(UserViewHolder holder, int position) {
  }

  @Override public int getItemCount() {
    return listOfUsers.size();
  }

  @Override public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    super.onAttachedToRecyclerView(recyclerView);
  }
}
