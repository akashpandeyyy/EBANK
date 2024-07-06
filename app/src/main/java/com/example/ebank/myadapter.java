package com.example.ebank;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder> {
    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model) {
        holder.Trans_id.setText("Transction ID :"+model.getTransction_id());
        holder.Trans_time.setText("Transction Time :"+model.getTransction_time());
        holder.Trans_to.setText("Transction To :"+model.getTransction_to());
        holder.Trans_from.setText("Transction From :"+model.getTransction_from());
        holder.Trans_ammount.setText("Transction Ammount :"+model.getTransction_amount());
        holder.Trans_idd.setText(model.getTransction_idd());

    }
    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view) ;
    }

    class myviewholder extends RecyclerView.ViewHolder{

        TextView Trans_id,Trans_time,Trans_to,Trans_from,Trans_ammount,Trans_idd;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            Trans_id=(TextView)itemView.findViewById(R.id.Trans_id);
            Trans_idd=(TextView)itemView.findViewById(R.id.Trans_idd);
            Trans_time=(TextView)itemView.findViewById(R.id.Trans_time);
            Trans_to=(TextView)itemView.findViewById(R.id.Trans_to);
            Trans_from=(TextView)itemView.findViewById(R.id.Trans_from);
            Trans_ammount=(TextView)itemView.findViewById(R.id.Trans_ammount);
        }
    }
}
