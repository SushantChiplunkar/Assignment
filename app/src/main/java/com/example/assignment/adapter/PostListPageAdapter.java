package com.example.assignment.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.utils.AppConstants;
import com.example.assignment.model.Post;
import com.example.assignment.R;
import com.example.assignment.adapter.viewholders.LoadingViewHolder;
import com.example.assignment.adapter.viewholders.PostViewHolder;
import com.example.assignment.databinding.LoadingViewBinding;
import com.example.assignment.databinding.PostViewRowBinding;

import java.util.Objects;

public class PostListPageAdapter extends PagedListAdapter<Post, RecyclerView.ViewHolder> {

    private static final int TYPE_LOAD = 1;
    private static final int TYPE_POST = 2;

    //Loading state : ONGOING, FAILED, SUCCESS defined in the constants file
    private Integer state;

    public PostListPageAdapter() {
        super(diffCallback);
    }

    private static DiffUtil.ItemCallback<Post> diffCallback = new DiffUtil.ItemCallback<Post>() {
        @Override
        public boolean areItemsTheSame(@NonNull Post oldItem, @NonNull Post newItem) {
            return Objects.equals(oldItem.getId(), newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Post oldItem, @NonNull Post newItem) {
            return Objects.equals(oldItem.getId(), newItem.getId());
        }
    };

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_POST) {
            PostViewRowBinding postViewRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.post_view_row, parent, false);


           // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_view_row, parent, false);
           // PostViewRowBinding rowBinding = PostViewRowBinding.bind(view);
            return new PostViewHolder(postViewRowBinding);
        } else {
            LoadingViewBinding loadingViewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.loading_view, parent, false);
            //View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_view, parent, false);
            //LoadingViewBinding binding = LoadingViewBinding.bind(view2);
            return new LoadingViewHolder(loadingViewBinding);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof PostViewHolder){
            PostViewHolder postViewHolder = (PostViewHolder) holder;
            postViewHolder.bindTo(getItem(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount()-1  && state != null && !state.equals(AppConstants.SUCCESS))
            return TYPE_LOAD;
        else
            return TYPE_POST;
    }

    public void setLoadState(Integer state) {
        this.state = state;
    }
}
