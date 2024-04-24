package com.example.assignment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.model.Post;
import com.example.assignment.R;
import com.example.assignment.callbacklistener.OnItemCallbackListener;
import com.example.assignment.databinding.PostViewRowBinding;

import java.util.Objects;

public class PostListAdapter extends ListAdapter<Post, PostListAdapter.ViewHolder> {

    private OnItemCallbackListener onItemClickListener;

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
        holder.rowBinding.identityNumber.setText("Id: " +getItem(position).getId().toString());
        holder.rowBinding.title.setText("Title: " +getItem(position).getTitle());
        holder.rowBinding.desc.setText("Description :" +getItem(position).getBody());

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        PostViewRowBinding rowBinding;

        public ViewHolder(@NonNull PostViewRowBinding rowBinding) {
            super(rowBinding.getRoot());
            this.rowBinding = rowBinding;
            rowBinding.moreTxt.setOnClickListener(v -> {
                String moreTitle = rowBinding.moreTxt.getText().toString().contains("more") ? "less..." : "more...";
                if(moreTitle.contains("less...")){
                    rowBinding.moreTxt.setText(moreTitle);
                    rowBinding.desc.setMaxLines(20);
                    rowBinding.title.setMaxLines(5);
                }else {
                    rowBinding.moreTxt.setText(moreTitle);
                    rowBinding.desc.setMaxLines(2);
                    rowBinding.title.setMaxLines(1);
                }
            });

            rowBinding.getRoot().setOnClickListener(v->{
                if(onItemClickListener!=null){
                    onItemClickListener.onItemClickListener(v,getItem(getAdapterPosition()),getAdapterPosition());
                }
            });
        }


    }


    public void setOnItemClickListener(OnItemCallbackListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

}
