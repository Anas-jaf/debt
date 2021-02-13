package com.example.debt;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class infoViewHolder extends RecyclerView.ViewHolder {
public TextView name,amount, notes;
    public Button deleterow;
    public infoViewHolder( View itemView) {
        super(itemView);
        name= (TextView)itemView.findViewById(R.id.name);
        amount= (TextView)itemView.findViewById(R.id.amount);
        notes =(TextView)itemView.findViewById(R.id.note);
        deleterow = (Button) itemView.findViewById(R.id.delete_button);
    }
}
