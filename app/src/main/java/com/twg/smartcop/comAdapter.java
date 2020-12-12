package com.twg.smartcop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

public class comAdapter extends RecyclerView.Adapter<comAdapter.VeiwHolder> {
    Context context;
    List<liveComplaints> modelList;

    public comAdapter(Context context, List<liveComplaints> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public VeiwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        return new VeiwHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VeiwHolder holder, int position) {
        liveComplaints model = modelList.get(position);
        holder.location.setText(model.getLocation());
        holder.description.setText(model.getImageName());
        holder.time.setText(model.getTime());
        String url = null;
        url =  model.getImageURL();
//       Glide.with(context).load(url).into(holder.imageView);

        Picasso.get().load(model.getImageURL()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class VeiwHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView location, description, time;
        public VeiwHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgComplaint);
            location = itemView.findViewById(R.id.txtLocation);
            description = itemView.findViewById(R.id.txtDescription);
            time = itemView.findViewById(R.id.txtTime);

        }
    }
}
