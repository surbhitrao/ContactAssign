package com.example.surbhit.searchview.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.surbhit.searchview.R;
import com.example.surbhit.searchview.model.Contact;
import com.example.surbhit.searchview.viewmodel.ContactAdapter;
import com.example.surbhit.searchview.viewmodel.DatabaseAdapter;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

public class ContactActivity extends AppCompatActivity {


    DatabaseAdapter mydb;
    ContactAdapter suAdapter;

    RecyclerView recyclerView;
    MaterialSearchView search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mydb  = new DatabaseAdapter(getApplicationContext());
        recyclerView = (RecyclerView)findViewById(R.id.contacts_list);
        setRecyclerview();
        search = (MaterialSearchView)findViewById(R.id.search_view);
        search.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String stext) {
                suAdapter.filter(stext);
                return false;
            }
        });






        checkRunTimePermission();

    }

    private void checkRunTimePermission() {
        String[] permissionArrays = new String[]{Manifest.permission.READ_CONTACTS};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissionArrays, 11111);
        } else {
            // if already permition granted
            // PUT YOUR ACTION (Like Open cemara etc..)
        }
    }


















    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        search.setMenuItem(item);

        return true;
    }



    @Override
    public void onResume(){
        super.onResume();
        mydb  = new DatabaseAdapter(getApplicationContext());
        recyclerView = (RecyclerView)findViewById(R.id.contacts_list);
        setRecyclerview();

    }

    private void setRecyclerview() {
        new ConttactLoader().execute();
    }

    public class ConttactLoader extends AsyncTask<Void, Void, List<Contact>> {

        @Override
        protected List<Contact> doInBackground(Void... voids) {
            List<Contact> MHList = mydb.getData();
            return MHList;
        }

        @Override
        protected void onPostExecute(List<Contact> selectUsers) {
            if (selectUsers.isEmpty()==false){
                suAdapter = new ContactAdapter(ContactActivity.this, selectUsers);

                recyclerView.setLayoutManager(new LinearLayoutManager(ContactActivity.this));
                recyclerView.setAdapter(suAdapter);
            }
        }
    }
}
