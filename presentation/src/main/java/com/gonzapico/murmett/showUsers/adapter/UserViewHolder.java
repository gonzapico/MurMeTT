package com.gonzapico.murmett.showUsers.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.ButterKnife;

/**
 * Created by gfernandez on 26/02/17.
 */

public class UserViewHolder extends RecyclerView.ViewHolder {

  UserViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }
}
