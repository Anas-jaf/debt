package com.example.debt;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.example.debt.DataBaseStuff.infoDataBaseAdapter;

public class recyclerAdapter extends RecyclerView.Adapter<infoViewHolder> {

    private Context context;
    private ArrayList<debt_data> debtList ;
    private infoDataBaseAdapter mDatabase;


    public recyclerAdapter(Context context,ArrayList<debt_data> debtList) {
        this.context = context;
        this.debtList = debtList;
        mDatabase = new infoDataBaseAdapter(context);
    }

//    public  class  MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//
//        public TextView name;
//        public  TextView amount;
//        public TextView notes;
//        public Button removeBt;
//        private int position;
//
//
//        public  MyViewHolder (final View view){
//            super(view);
//            name = view.findViewById(R.id.name);
//            amount = view.findViewById(R.id.amount);
//            notes = view.findViewById(R.id.note);
//            removeBt = view.findViewById(R.id.delete_button);
//
//        }
//
////        @Override
////        public void onClick(View v) {
////            debt_data debtInfo = debtList.get(position);
////
////            switch (v.getId()){
////                case R.id.delete_button:
////                    removeItem(position);
////                    break;
////            }
////        }
////
////        public void setListeners() {
////            removeBt.setOnClickListener(MyViewHolder.this);
////        }
//
//
//
//    }



    @Override
    public infoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list, parent , false);
        infoViewHolder viewHolder= new infoViewHolder(itemView);
        return  viewHolder;
    }


    @Override
    public void onBindViewHolder( infoViewHolder holder, int position) {
        final debt_data debtInfo = debtList.get(position);

        holder.name.setText(debtInfo.getName());
        holder.amount.setText( String.valueOf(debtInfo.getAmount()));
        holder.notes.setText(debtInfo.getNote());

//        holder.setListeners();
        holder.deleterow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);
                mDatabase.deleteEntry(debtInfo.getId());
//                Toast.makeText(context.getApplicationContext(), position, Toast.LENGTH_LONG).show();
                Log.d("القيمة التي مررت",String.valueOf(position));
                //TODO : add here advertisement


            }
        });
    }

    @Override
    public int getItemCount() {
        return debtList.size();
    }


    public void removeItem(int position) {
        debtList.remove(position);
        notifyItemRemoved(position);
        mDatabase.deleteEntry(position);
        notifyItemRangeChanged(position, debtList.size());
//TODO : add here advertisement
    }

}
