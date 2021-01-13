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

public class projectAdapter extends RecyclerView.Adapter<projectAdapter.ViewHolder>{
    Context context;
    List<modelProject> modelProjects;

    public projectAdapter(Context context, List<modelProject> modelProjects) {
        this.context = context;
        this.modelProjects = modelProjects;
    }

    @NonNull
    @Override
    public projectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardproject,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull projectAdapter.ViewHolder holder, int position) {
        modelProject modelProject = modelProjects.get(position);
        holder.costp.setText(modelProject.getProCost());
        holder.nameP.setText(modelProject.getProjectName());
        holder.descriptionP.setText(modelProject.getProDescription());
        String url = null;
        url =  modelProject.getImageURLpro();
        Picasso.get().load(modelProject.getImageURLpro()).into(holder.imageViewPro);


    }

    @Override
    public int getItemCount() {
       return modelProjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewPro;
        TextView nameP, descriptionP, costp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPro = itemView.findViewById(R.id.item_image_pro);
            nameP = itemView.findViewById(R.id.proName);
            descriptionP = itemView.findViewById(R.id.item_desc_pro);
            costp = itemView.findViewById(R.id.item_date);

        }
    }
}
