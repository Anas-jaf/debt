package com.example.debt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class debt_page extends AppCompatActivity {

    Button bt ;
    ArrayList<debt_data> debt_data = new ArrayList<>();
    ListView listView ;
    dataList_adapter adapter ;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dept_page);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        /*find the list view*/
//        listView = (ListView) findViewById(R.id.list_items);


//        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, words);
//        adapter = new dataList_adapter(this, R.layout.adapter_list , debt_data);
//        listView.setAdapter(adapter);
        display();
    }

    public   void  display() {
        recyclerView = (RecyclerView) findViewById(R.id.list_items);
        bt = (Button) findViewById(R.id.button) ;
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the tall number from the page
                EditText name = (EditText) findViewById(R.id.name);
                String finalName = name.getText().toString();

                // get the wide number from the page
                EditText amount = (EditText) findViewById(R.id.amount);
                String strAmount = amount.getText().toString();
                double finalAmount = Integer.parseInt(strAmount);

                //get the notes from the user
                EditText notes = (EditText) findViewById(R.id.notes);
                String finalNotes = notes.getText().toString();

                if (!(finalName.equals("")|| strAmount.equals("") || finalNotes.equals(""))) {
                    // process the debt data and wide in the  array list
                    debt_data.add(new debt_data(finalName, finalAmount, finalNotes));

                    recyclerAdapter adapter = new recyclerAdapter(debt_data);
                    RecyclerView.LayoutManager lm = new LinearLayoutManager(debt_page.this);

                    recyclerView.setLayoutManager(lm);
                    recyclerView.setAdapter(adapter);

                    name.getText().clear();
                    amount.getText().clear();
                    notes.getText().clear();
                }
            }
        });
    }
}
