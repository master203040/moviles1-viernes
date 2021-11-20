package com.moviles1.loginui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moviles1.loginui.databinding.UserItemBinding;
import com.moviles1.loginui.entities.UserEntity;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private UserItemBinding userItemBinding;
    private Context context;
    private ArrayList<UserEntity> userArrayList;
    public UserAdapter(Context context, ArrayList<UserEntity> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        userItemBinding = UserItemBinding.inflate(LayoutInflater.from(context));
        return new UserViewHolder(userItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {
        UserEntity user = userArrayList.get(position);
        holder.itemBinding.tvName.setText(user.getName());
        holder.itemBinding.tvIdentificaton
                .setText(String.valueOf(user.getIdentification()));
        holder.itemBinding.tvEmail.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private UserItemBinding itemBinding;
        public UserViewHolder(@NonNull UserItemBinding itemView) {
            super(itemView.getRoot());
            this.itemBinding = itemView;
        }
    }
}
