package com.example.assignment.adapter.viewholders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.databinding.LoadingViewBinding;

public class LoadingViewHolder extends RecyclerView.ViewHolder {
    private final LoadingViewBinding loadingViewBinding;
    public LoadingViewHolder(@NonNull LoadingViewBinding loadingViewBinding) {
        super(loadingViewBinding.getRoot());
        this.loadingViewBinding = loadingViewBinding;
    }
}
