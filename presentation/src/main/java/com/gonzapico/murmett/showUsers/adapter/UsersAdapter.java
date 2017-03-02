package com.gonzapico.murmett.showUsers.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.gonzapico.murmett.showUsers.UserModel;
import java.util.List;
import xyz.gonzapico.murmett.R;

/**
 * Created by gfernandez on 26/02/17.
 */

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {
  @BindView(R.id.ivPlayLatestAudio) ImageView ivPlayLatestAudio;
  @BindView(R.id.ivPlayPresentation) ImageView ivPlayPresentation;
  @BindView(R.id.ivRecordAudio) ImageView ivRecordAudio;
  @BindView(R.id.tvUserName) TextView tvUsername;

  private List<UserModel> listOfUsers;
  private Context mContext;
  private OnItemClickListener userClickListener;

  public UsersAdapter(List<UserModel> userModelList) {
    listOfUsers = userModelList;
  }

  @Override public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    mContext = parent.getContext();
    View v = LayoutInflater.from(mContext).inflate(R.layout.user_row, parent, false);
    ButterKnife.bind(this, v);
    return new UserViewHolder(v);
  }

  @Override public void onBindViewHolder(UserViewHolder holder, int position) {
    final UserModel currentUser = listOfUsers.get(position);
    tvUsername.setText(currentUser.getName());
    ivPlayLatestAudio.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        userClickListener.onLatestAudioClicked(currentUser);
      }
    });
    ivPlayPresentation.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        userClickListener.onPlayPresentationClicked(currentUser);
      }
    });
    ivRecordAudio.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        userClickListener.onRecordAudioClicked(currentUser);
      }
    });
  }

  public void setOnUserClickListener(UsersAdapter.OnItemClickListener onItemClickListener) {
    this.userClickListener = onItemClickListener;
  }

  @Override public int getItemCount() {
    return listOfUsers.size();
  }

  @Override public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    super.onAttachedToRecyclerView(recyclerView);
  }

  public interface OnItemClickListener {
    void onPlayPresentationClicked(UserModel userModel);

    void onRecordAudioClicked(UserModel userModel);

    void onLatestAudioClicked(UserModel userModel);
  }
}
