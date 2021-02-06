package com.example.debt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {


    private ArrayList<debt_data> debtList ;

    public recyclerAdapter(ArrayList<debt_data> debtList) {
        this.debtList = debtList;
    }

    public  class  MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView name;
        public  TextView amount;
        public TextView notes;
        public Button removeBt;
        private int position;


        public  MyViewHolder (final View view){
            super(view);
            name = view.findViewById(R.id.name);
            amount = view.findViewById(R.id.amount);
            notes = view.findViewById(R.id.note);
            removeBt = view.findViewById(R.id.delete_button);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.delete_button:
                    removeItem(position);
                    break;
            }
        }

        public void setListeners() {
            removeBt.setOnClickListener(MyViewHolder.this);
        }



    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list, parent , false);
        MyViewHolder viewHolder= new MyViewHolder(itemView);
        return  viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {


        debt_data debtInfo = debtList.get(position);


        holder.name.setText(debtInfo.getName());
        holder.amount.setText( String.valueOf(debtInfo.getAmount()));
        holder.notes.setText(debtInfo.getNote());
        holder.setListeners();

    }

    @Override
    public int getItemCount() {
        return debtList.size();
    }


    public void removeItem(int position) {
        debtList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, debtList.size());

    }

}
