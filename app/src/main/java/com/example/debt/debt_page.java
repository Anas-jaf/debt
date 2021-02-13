package com.example.debt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.debt.DataBaseStuff.infoDataBaseAdapter;

import java.util.ArrayList;

public class debt_page extends AppCompatActivity {

    Button bt ;
    ArrayList<debt_data> debt_data = new ArrayList<>();
    ListView listView ;
    ListView_adapter adapter ;
    private RecyclerView recyclerView;
    infoDataBaseAdapter infoDataBaseAdapter;
    private infoDataBaseAdapter mDatabase;
    recyclerAdapter RVadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dept_page);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        infoDataBaseAdapter=new infoDataBaseAdapter(this);

        infoDataBaseAdapter=infoDataBaseAdapter.open();
        /*find the list view*/
//        listView = (ListView) findViewById(R.id.list_items);

        recyclerView = (RecyclerView) findViewById(R.id.list_items);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setHasFixedSize(true);
//        mDatabase = new infoDataBaseAdapter(this);
//        debt_data = mDatabase.listContacts();
//        RVadapter = new  recyclerAdapter(this , debt_data);
//        recyclerView.setAdapter(RVadapter);

        if (infoDataBaseAdapter.isNotEmpty()){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            mDatabase = new infoDataBaseAdapter(this);
            debt_data = mDatabase.listContacts();
            RVadapter = new  recyclerAdapter(this , debt_data);
            recyclerView.setAdapter(RVadapter);
        }

//        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, words);
//        adapter = new dataList_adapter(this, R.layout.adapter_list , debt_data);
//        listView.setAdapter(adapter);
        display();
    }


    public   void  display() {
        recyclerView = (RecyclerView) findViewById(R.id.list_items);
        bt = (Button) findViewById(R.id.button) ;

        recyclerAdapter adapter = new recyclerAdapter(this , debt_data);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : add here advertisement

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
                    debt_data newData = new debt_data(finalName, finalAmount, finalNotes);

                    infoDataBaseAdapter.insertEntry(newData);

                    Toast.makeText(getApplicationContext(), "تمت العملية بنجاح", Toast.LENGTH_LONG).show();

                    debt_data.add(newData);

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

    @Override
    protected void onDestroy() {

        super.onDestroy();

        infoDataBaseAdapter.close();
    }

}
