package com.hfad.mylisttodo;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView categoryList;
    private MyAdapter mAdapter;
    private List<String> categories = new ArrayList<>();
    ToDOListDatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categoryList = (RecyclerView) findViewById(R.id.category_list);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        categoryList.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(categories);
        categoryList.setAdapter(mAdapter);

        sqlHelper = new ToDOListDatabaseHelper(this);
        userCursor = sqlHelper.getWritableDatabase().query("LIST", null, null, null,
                null, null, null);
        String[] from = new String[] {"TEXT"};
        int[] to = new int[] { R.id.category_name};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.item, userCursor, new String[]{"NAME"}, new int[]{android.R.id.text1},
                0);
        categoryList.setAdapter(mAdapter);

    }



    @Override
    public void onDestroy(){
        super.onDestroy();
        // Закрываем подключения
        db.close();
        userCursor.close();
    }

    public void onCreateCategory(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dlg_category, null);
        final EditText valueKey = (EditText) view.findViewById(R.id.category_name);
        builder.setTitle(R.string.dlg_category_title)
                .setView(view)
                .setNegativeButton(R.string.dlg_add,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                categories.add(String.valueOf(valueKey.getText()));
                                mAdapter.notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        });

        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override

    public  boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id){
            case R.id.action_create_category:
                Toast.makeText(MainActivity.this, getString(R.string.category),Toast.LENGTH_LONG).show();
                break;
            case R.id.action_create_list_to_do:
                Toast.makeText(MainActivity.this, getString(R.string.list_to_do),Toast.LENGTH_LONG).show();
                break;
            case R.id.action_create_shopping_list:
                Toast.makeText(MainActivity.this, getString(R.string.shopping_list),Toast.LENGTH_LONG).show();
                break;
            case R.id.action_settings:
                Toast.makeText(MainActivity.this, getString(R.string.action_settings),Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
