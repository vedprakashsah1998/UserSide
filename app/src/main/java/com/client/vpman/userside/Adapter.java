package com.client.vpman.userside;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.AdapterHolder> {

    List<TournamentDetail> complaintMessages;
    Context context;
    StorageReference mAuth;
    DatabaseReference databaseReference;
    StorageReference photoRef;
    private FirebaseStorage mStorage;
    private List<TournamentDetail> tournamentDetails;
    public static final String Database_Path = "Tournament_detail";
    List<String> key;
    private OnItemClickListener onItemClickListener;


    public Adapter(List<TournamentDetail> complaintMessages, Context context, List<String> key) {
        this.complaintMessages = complaintMessages;
        this.key=key;
        this.context = context;
    }
    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
       // AdapterHolder commplaintHolder = new AdapterHolder(view);
        return new AdapterHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(final AdapterHolder holder, final int position) {
        final TournamentDetail complaintMessage = complaintMessages.get(position);
        //holder.problemTextView.setText(complaintMessage.get());
        //holder.areaTextView.setText(complaintMessage.get());
        holder.NameHolder1.setText(complaintMessage.getNameHolder1());
       // Toast.makeText(context,"heyare "+complaintMessages.size(),Toast.LENGTH_LONG).show();
        holder.DateHold1.setText(complaintMessage.getDateHold1());
        holder.TimeHold1.setText(complaintMessage.getTimeHold1());
        holder.DescHold1.setText(complaintMessage.getDescHold1());
        holder.MoneyHold1.setText(complaintMessage.getMoneyHold1());
        /*boolean isPhoto = complaintMessage.getImage() != null;
        if (isPhoto) {
            holder.photoImageView.setVisibility(View.VISIBLE);
            Glide.with(holder.photoImageView.getContext())
                    .load(complaintMessage.getImage())
                    .into(holder.photoImageView);
        } else {
            holder.photoImageView.setVisibility(View.VISIBLE);
        }*/
        mStorage=FirebaseStorage.getInstance();
        databaseReference=FirebaseDatabase.getInstance().getReference("Tournament_detail");

        Glide.with(context)
                .load(complaintMessage.getImage())
                .into(holder.photoImageView);

        mStorage=FirebaseStorage.getInstance();

        /*holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               *//* TournamentDetail tournamentDetail=tournamentDetails.get(position);
                final String selectedKey=tournamentDetail.getmKey();
                StorageReference imageRef=mStorage.getReferenceFromUrl(tournamentDetail.getImage());
                imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        databaseReference.child(selectedKey).removeValue();
                        Toast.makeText(context,"Deleted",Toast.LENGTH_LONG).show();
                    }
                });*//*

                FirebaseDatabase.getInstance().getReference()
                        .child("Tournament_detail").child(key.get(position)).removeValue();
            }
        });*/



    }

    public interface OnItemClickListener
    {
        void onItemClick(View view,int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    public int getItemCount() {
        return complaintMessages.size();
    }


    class AdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView photoImageView;
        TextView NameHolder1, DescHold1, DateHold1, TimeHold1, MoneyHold1;
        Button delete;
        OnItemClickListener onItemClickListener;

        public AdapterHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            itemView.setOnClickListener(this);
            photoImageView = itemView.findViewById(R.id.img);
            NameHolder1 = itemView.findViewById(R.id.author);
            DescHold1 = itemView.findViewById(R.id.desc1);
            DateHold1 = itemView.findViewById(R.id.date02);
            TimeHold1 = itemView.findViewById(R.id.source);
            MoneyHold1 = itemView.findViewById(R.id.title);
            delete=itemView.findViewById(R.id.delete);
            this.onItemClickListener=onItemClickListener;
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v,getAdapterPosition());
        }
    }

}
