package com.tugaybakay.packyourbag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.tugaybakay.packyourbag.Adapters.Adapter;
import com.tugaybakay.packyourbag.Constans.MyConstans;
import com.tugaybakay.packyourbag.Data.AppData;
import com.tugaybakay.packyourbag.Database.ItemDb;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ItemDb database;
    List<String> titles;
    List<Integer> images;
    Adapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        setConstans();
        persistAppData();
        database = ItemDb.getDb(this);
        adapter = new Adapter(titles,images);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2,GridLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void setConstans(){
        titles = new ArrayList<>();
        titles.add(MyConstans.BASIC_NEEDS_CAMEL_CASE);
        titles.add(MyConstans.CLOTHING_CAMEL_CASE);
        titles.add(MyConstans.PERSONAL_CARE_CAMEL_CASE);
        titles.add(MyConstans.BABY_NEEDS_CAMEL_CASE);
        titles.add(MyConstans.HEALTH_CAMEL_CASE);
        titles.add(MyConstans.BASIC_NEEDS_CAMEL_CASE);
        titles.add(MyConstans.FOOD_CAMEL_CASE);
        titles.add(MyConstans.BEACH_SUPPLIES_CAMEL_CASE);
        titles.add(MyConstans.CAR_SUPPLIES_CAMEL_CASE);
        titles.add(MyConstans.NEEDS_CAMEL_CASE);
        titles.add(MyConstans.MY_LIST_CAMEL_CASE);
        titles.add(MyConstans.MY_SELECTIONS_CAMEL_CASE);

        images = new ArrayList<>();
        images.add(R.drawable.p1);
        images.add(R.drawable.p2);
        images.add(R.drawable.p3);
        images.add(R.drawable.p4);
        images.add(R.drawable.p5);
        images.add(R.drawable.p6);
        images.add(R.drawable.p7);
        images.add(R.drawable.p8);
        images.add(R.drawable.p9);
        images.add(R.drawable.p10);
        images.add(R.drawable.p11);
        images.add(R.drawable.p12);

    }

    private void persistAppData(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        database = ItemDb.getDb(this);
        AppData appData = new AppData(database);
        int last = sharedPreferences.getInt(AppData.LAST_VERSION,0);
        if(!sharedPreferences.getBoolean(MyConstans.FIRST_TIME_CAMEL_CASE,false)){
            appData.persistAllData();
            editor.putBoolean(MyConstans.FIRST_TIME_CAMEL_CASE,true);
            editor.commit();
        }else if(last<AppData.NEW_VERSION){
            database.getDao().deleteAllSystemItems();
            appData.persistAllData();
            editor.putInt(AppData.LAST_VERSION,AppData.NEW_VERSION);
            editor.commit();

        }
    }

}