package com.example.assignment.adapter.viewholders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.model.Post;
import com.example.assignment.databinding.PostViewRowBinding;

public class PostViewHolder extends RecyclerView.ViewHolder {

    private final PostViewRowBinding postViewRowBinding;
    public PostViewHolder(@NonNull PostViewRowBinding postViewRowBinding) {
        super(postViewRowBinding.getRoot());
        this.postViewRowBinding = postViewRowBinding;
    }

    public void bindTo(Post item) {
        postViewRowBinding.identityNumber.setText(String.valueOf(item.getId()));
        postViewRowBinding.title.setText(item.getTitle());
        postViewRowBinding.desc.setText(item.getBody());
        postViewRowBinding.executePendingBindings();
    }
}
