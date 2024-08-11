package com.example.sample.ui.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sample.R;
import com.example.sample.data.response.userrealm.DataItems;
import com.example.sample.databinding.ItemUserBinding;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private final Context contexts;
    private final LayoutInflater inflater;
    private final List<DataItems> mDataItem;
    private final UserClickListener mUserClickListener;

    public UserAdapter(Context contexts, UserClickListener mUserClickListener) {
        this.contexts = contexts;
        inflater = LayoutInflater.from(contexts);
        this.mUserClickListener = mUserClickListener;
        mDataItem = new ArrayList<>();
    }

    public void setUserAdapter(List<DataItems> item) {

        if (item == null)
            return;
        mDataItem.clear();
        mDataItem.addAll(item);
        notifyDataSetChanged();
    }

    public interface UserClickListener {
        void onItemClick(int absoluteAdapterPosition);
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUserBinding itemBinding = ItemUserBinding.inflate(inflater, parent, false);
        return new UserHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.setDataToView(mDataItem.get(position), contexts);
    }

    @Override
    public int getItemCount() {
        return mDataItem.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ItemUserBinding binding;

        public UserHolder(ItemUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(this);

        }

        public void setDataToView(DataItems mDataItem, Context contexts) {
            binding.tvName.setText(mDataItem.getFirstName() + mDataItem.getLastName());
            binding.tvPhone.setText(mDataItem.getEmail());
            Glide.with(contexts)
                    .load(mDataItem.getAvatar())
                    .placeholder(R.drawable.bg_no_image) // Optional placeholder image while loading
                    .error(R.drawable.bg_no_image) // Optional error image if loading fails
                    .into(binding.imgUser);
        }

        @Override
        public void onClick(View v) {
            if (mUserClickListener != null) {
                mUserClickListener.onItemClick(getAbsoluteAdapterPosition());
            }
        }
    }
}
