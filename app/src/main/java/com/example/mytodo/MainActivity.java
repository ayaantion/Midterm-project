package com.example.mytodo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.example.mytodo.Adapter.TodoAdapter;
import com.example.mytodo.Adapter.onTodoClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import static com.example.mytodo.SecondActivity.EXTRA_D;
import static com.example.mytodo.SecondActivity.EXTRA_T;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TodoAdapter adapter = new TodoAdapter();
     private ArrayList<String>list = new ArrayList<>();
     private String tittle;
     private  String desc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null){
            tittle = savedInstanceState.getString("t");
            desc = savedInstanceState.getString("d");
            list.add(tittle);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent,1);
            }
        });
        setupRecycler();
        adapter.onItemClick(new onTodoClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("list", list.get(position));
                startActivity(intent);
            }

            @Override
            public void onLongClick(int position) {
                    adapter.notifyItemRemoved(position);
            }
        });



    }

    private void setupRecycler() {
        recyclerView =findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.updateTodo(list);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1&& resultCode == RESULT_OK && data != null){
             tittle = data.getStringExtra(EXTRA_T);
            desc = data.getStringExtra(EXTRA_D);
            list.add(tittle);
          //  list.add(desc);
            Log.e("---------", tittle + " " + desc);

        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("t", tittle);
        outState.putString("d", desc);
    }
}
