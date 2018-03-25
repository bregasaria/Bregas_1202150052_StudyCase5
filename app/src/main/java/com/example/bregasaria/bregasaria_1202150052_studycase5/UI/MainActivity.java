package com.example.bregasaria.bregasaria_1202150052_studycase5.UI;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.bregasaria.bregasaria_1202150052_studycase5.DatabaseHelper;
import com.example.bregasaria.bregasaria_1202150052_studycase5.Model.Activity;
import com.example.bregasaria.bregasaria_1202150052_studycase5.R;
import com.example.bregasaria.bregasaria_1202150052_studycase5.RecyclerAdapter;
import com.example.bregasaria.bregasaria_1202150052_studycase5.SwipeHelper;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private LinkedList<Activity> mActivity = new LinkedList<>();
    private int mCount = 0;

    // Mendeklarasikan variabel
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private DatabaseHelper databaseHandler;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Mengecek Database
        databaseHandler = new DatabaseHelper(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setRecyclerView();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Mengeset menu setting untuk dapat memilih warna
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.custom_background);
            dialog.setTitle("Change");
            dialog.setCancelable(true);

            // mengecek radio button
            final RadioButton rdRed =  dialog.findViewById(R.id.rdRed);
            final RadioButton rdBlue = dialog.findViewById(R.id.rdBlue);
            final RadioButton rdGreen = dialog.findViewById(R.id.rdGreen);
            Button btnChange = dialog.findViewById(R.id.btnChange);
            btnChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (rdRed.isChecked()){
                        mRecyclerView.setBackgroundResource(R.color.redBackgroud);
                        Toast.makeText(view.getContext(),"Red Choosen",Toast.LENGTH_SHORT).show();
                    }
                    if (rdBlue.isChecked()){
                        mRecyclerView.setBackgroundResource(R.color.blueBackgroud);
                        Toast.makeText(view.getContext(),"Blue Choosen",Toast.LENGTH_SHORT).show();
                    }
                    if (rdGreen.isChecked()){
                        mRecyclerView.setBackgroundResource(R.color.greenBackgroud);
                        Toast.makeText(view.getContext(),"Green Choosen",Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(view.getContext(),"Changed",Toast.LENGTH_SHORT).show();
                }
            });

            // menampilkan pilihan dialog yang ada
            dialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void setRecyclerView() {
        // Mengambil data dari database
        mActivity = databaseHandler.findAll();
        // Menarik data yang ada dan diberikan di RecyclerView
        mRecyclerView = findViewById(R.id.recycler);
        // Membuat adapter agar dapat ditampilkan
        mAdapter = new RecyclerAdapter(MainActivity.this, mActivity);
        // Connect adapter dengan RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // RecyclerView sebagai default dari Layout Manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper.Callback callback = new SwipeHelper(mAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1){
            Log.d("new activity : ",data.getStringExtra("activity"));
            Log.d("new desc : ",data.getStringExtra("desc"));
            Log.d("new priority : ",data.getStringExtra("priority"));
            databaseHandler.save(new com.example.bregasaria.bregasaria_1202150052_studycase5.Model.Activity(data.getStringExtra("activity"), data.getStringExtra("desc"), data.getStringExtra("priority")));
        }
        setRecyclerView();
        mAdapter.notifyDataSetChanged();
    }
}
