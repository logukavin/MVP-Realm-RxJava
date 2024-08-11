package com.example.sample.pager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sample.R;
import com.example.sample.data.response.user.DataItem;

public class ItemAdapter extends PagedListAdapter<DataItem, ItemAdapter.ItemViewHolder> {
    private final Context mcontexts;
    public ItemAdapter(Context contexts) {
        super(DIFF_CALLBACK);
        this.mcontexts = contexts;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_user, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        DataItem item = getItem(position);

        if (item != null) {
            holder.bind(item,mcontexts);
        }
    }



    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private TextView textViewPhone;
        private ImageView imgUser;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUser = itemView.findViewById(R.id.imgUser);
            textViewName = itemView.findViewById(R.id.tvName);
            textViewPhone = itemView.findViewById(R.id.tvPhone);
        }

        void bind(DataItem item, Context contexts) {
            textViewName.setText(item.getFirstName());
            textViewPhone.setText(item.getEmail());
            Glide.with(contexts)
                    .load(item.getAvatar())
                    .placeholder(R.drawable.bg_no_image) // Optional placeholder image while loading
                    .error(R.drawable.bg_no_image) // Optional error image if loading fails
                    .into(imgUser);
        }
    }

    private static DiffUtil.ItemCallback<DataItem> DIFF_CALLBACK = new DiffUtil.ItemCallback<DataItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull DataItem oldItem, @NonNull DataItem newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull DataItem oldItem, @NonNull DataItem newItem) {
            return oldItem.equals(newItem);
        }
    };
}
