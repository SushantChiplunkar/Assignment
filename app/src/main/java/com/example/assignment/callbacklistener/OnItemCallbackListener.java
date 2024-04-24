package com.example.assignment.callbacklistener;

import android.view.View;

import com.example.assignment.model.Post;

public interface OnItemCallbackListener {
    void onItemClickListener(View view, Post post, int position);
}
