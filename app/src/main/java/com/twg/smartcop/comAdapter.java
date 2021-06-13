package com.twg.smartcop;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

public class comAdapter extends RecyclerView.Adapter<comAdapter.VeiwHolder> {
    Context context;
    List<liveComplaints> modelList;
    LocationTrack locationTrack;


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
        double longitude,latitude;
        longitude = model.getLongitude();
        latitude = model.getLatitude();
        holder.btnloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (latitude > 0) {

                    String urlloc = "https://www.google.com/maps/dir/?api=1&destination=" + longitude + "," + latitude + "&travelmode=driving";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlloc));
                    context.startActivity(intent);

                }else {
                    Toast.makeText(context, "Location not found", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
        Button btnloc;
        public VeiwHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgComplaint);
            location = itemView.findViewById(R.id.txtLocation);
            description = itemView.findViewById(R.id.txtDescription);
            time = itemView.findViewById(R.id.txtTime);
            btnloc = itemView.findViewById(R.id.btnLoc);


        }
    }
}
