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

public class clientAdapter  extends RecyclerView.Adapter<clientAdapter.ViewHolder>{
    Context context;
    List<modelClient> modelClientList;

    public clientAdapter(Context context, List<modelClient> modelClientList) {
        this.context = context;
        this.modelClientList = modelClientList;
    }

    @NonNull
    @Override
    public clientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardclient,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull clientAdapter.ViewHolder holder, int position) {
        modelClient modelClient = modelClientList.get(position);
        holder.dateC.setText(modelClient.getCliDate());
        holder.nameC.setText(modelClient.getCliName());
        holder.descriptionC.setText(modelClient.getCliDescription());
        String url = null;
        url =  modelClient.getImageURLcli();
        Picasso.get().load(modelClient.getImageURLcli()).into(holder.imageViewCli);

    }

    @Override
    public int getItemCount() {
        return modelClientList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewCli;
        TextView nameC, descriptionC, dateC;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewCli = itemView.findViewById(R.id.item_image_cli);
            nameC = itemView.findViewById(R.id.cliName);
            descriptionC = itemView.findViewById(R.id.item_desc_cli);
            dateC = itemView.findViewById(R.id.item_date_cli);
        }
    }
}
