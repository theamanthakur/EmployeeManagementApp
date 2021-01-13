package com.ttl.ritz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class taskAdapter extends RecyclerView.Adapter<taskAdapter.VeiwHolder> {
    Context context;
    List<modelTask> modelList;

    public taskAdapter(Context context, List<modelTask> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public taskAdapter.VeiwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardtask,parent,false);
        return new VeiwHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull taskAdapter.VeiwHolder holder, int position) {
        modelTask  model = modelList.get(position);
        holder.nameE.setText(model.getName());
        holder.description.setText(model.gettaskAssigned());
        holder.time.setText(model.getTime());
        String url = null;
        url =  model.getImageURL();
        Picasso.get().load(model.getImageURL()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class VeiwHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView nameE, description, time;
        public VeiwHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
            nameE = itemView.findViewById(R.id.textName);
            description = itemView.findViewById(R.id.item_desc);
            time = itemView.findViewById(R.id.item_date);
        }
    }
}
