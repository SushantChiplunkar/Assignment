package com.example.assignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.databinding.PostViewRowBinding;

import java.util.Objects;

public class PostListAdapter extends ListAdapter<Post, PostListAdapter.ViewHolder> {

    public PostListAdapter() {
        super(diffCallBack);
    }

    private static DiffUtil.ItemCallback<Post> diffCallBack = new DiffUtil.ItemCallback<Post>() {
        @Override
        public boolean areItemsTheSame(@NonNull Post oldItem, @NonNull Post newItem) {
            return Objects.equals(oldItem.getId(), newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Post oldItem, @NonNull Post newItem) {
            return Objects.deepEquals(oldItem, newItem);
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_view_row, parent, false);
        PostViewRowBinding rowBinding = PostViewRowBinding.bind(view);
        return new ViewHolder(rowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.rowBinding.identityNumber.setText(getItem(position).getId() == 0 ? "" : String.valueOf(getItem(position).getId()));
//        holder.rowBinding.title.setText(getItem(position).getTitle() == null ? "" : getItem(position).getTitle());
//        holder.rowBinding.desc.setText(getItem(position).getBody() == null ? "" : getItem(position).getBody());
        holder.rowBinding.identityNumber.setText(getItem(position).getId().toString());
        holder.rowBinding.title.setText(getItem(position).getTitle());
        holder.rowBinding.desc.setText(getItem(position).getBody());
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        PostViewRowBinding rowBinding;

        public ViewHolder(@NonNull PostViewRowBinding rowBinding) {
            super(rowBinding.getRoot());
            this.rowBinding = rowBinding;
        }
    }
}
