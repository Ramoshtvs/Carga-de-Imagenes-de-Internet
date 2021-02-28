package com.example.imagenes_heroes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class adapterHeroe extends RecyclerView.Adapter<adapterHeroe.MyViewHolder> {


    //Constructor con los objetos
    ArrayList<pojoHeroe> persons;

    adapterHeroe(ArrayList<pojoHeroe> persons){
        this.persons = persons;
    }

    @NonNull
    @Override
    public adapterHeroe.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image, parent, false);
        MyViewHolder pvh = new MyViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull adapterHeroe.MyViewHolder holder, int position) {
        holder.personName.setText(persons.get(position).getName());
        Picasso.get().load(persons.get(position).getImage_url()).resize(500, 500).into(holder.personPhoto);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView personName;
        ImageView personPhoto;

        MyViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.textView2);

            personPhoto = (ImageView)itemView.findViewById(R.id.imageView);
        }
    }
}
