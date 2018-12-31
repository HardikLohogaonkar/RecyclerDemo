package com.hardik.recycleranimation.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hardik.recycleranimation.R;
import com.hardik.recycleranimation.entity.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolderUser> {

    private List<User> userList;
    private Context context;
    private boolean isRow;

    public UserAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolderUser onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (isRow == true) {

            View view = LayoutInflater.from(context).inflate(R.layout.layout_grid, viewGroup, false);
            ViewHolderUser viewHolderUser = new ViewHolderUser(view);
            return viewHolderUser;
        } else {

            View view = LayoutInflater.from(context).inflate(R.layout.layout_home_activity, viewGroup, false);
            ViewHolderUser viewHolderUser = new ViewHolderUser(view);
            return viewHolderUser;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolderUser holder, int i) {

        final User user = userList.get(i);

        holder.mTxtName.setText(user.getName());
        holder.mTxtChef.setText("By " + user.getChef());
        holder.mTxtTimestamp.setText(user.getTimestamp());
        holder.mTxtDesc.setText(user.getDescription());
        holder.mTxtPrice.setText("Price: ₹" + user.getPrice());
        Picasso.with(context).load(user.getThumbnail()).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolderUser extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTxtName, mTxtDesc, mTxtPrice, mTxtChef, mTxtTimestamp;

        public ViewHolderUser(@NonNull View itemView) {
            super(itemView);

            mImageView = this.itemView.findViewById(R.id.img_dish);
            mTxtName = this.itemView.findViewById(R.id.txt_name);
            mTxtChef = this.itemView.findViewById(R.id.txt_chef);
            mTxtTimestamp = this.itemView.findViewById(R.id.txt_timestamp);
            mTxtDesc = this.itemView.findViewById(R.id.txt_desc);
            mTxtPrice = this.itemView.findViewById(R.id.txt_price);
        }
    }
}
