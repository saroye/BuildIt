package com.example.scout;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> mImageNmaes = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<Post> posts = new ArrayList<>();
    private Context mContext;
    private OnNoteListener mOnNoteListener;

    public RecyclerViewAdapter(ArrayList<Post> posts, OnNoteListener onNoteListener) {
        this.posts = posts;
        mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.posting_item, parent, false);
        ViewHolder holder = new ViewHolder(view, mOnNoteListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
    {
        Log.d(TAG, "onBindHolder: called.");

        Post currentPost = posts.get(position);

        holder.image.setImageResource(currentPost.getImage());
        holder.name.setText(currentPost.getPostName());
        holder.descript.setText(currentPost.getDescrip());


    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        OnNoteListener onNoteListener;
        ImageView image;
        TextView descript;
        TextView name;
//        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);



            image = itemView.findViewById(R.id.postImage);
            descript = itemView.findViewById(R.id.postDes);
            name = itemView.findViewById(R.id.postName);
            this.onNoteListener = onNoteListener;
//            parentLayout = itemView.findViewById(R.id.parent_layout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, getAdapterPosition()+"");
            onNoteListener.onNoteClick(getAdapterPosition());
        }

    }
    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}
