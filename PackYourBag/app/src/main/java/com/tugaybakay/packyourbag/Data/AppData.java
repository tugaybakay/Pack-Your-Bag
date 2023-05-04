package com.tugaybakay.packyourbag.Data;

import android.app.Application;
import android.content.Context;
import com.tugaybakay.packyourbag.Constans.MyConstans;
import com.tugaybakay.packyourbag.Database.ItemDb;
import com.tugaybakay.packyourbag.Models.Item;
import java.util.ArrayList;
import java.util.List;

public class AppData {

    ItemDb itemDb;
    String category;
    Context context;

    public static final String LAST_VERSION = "LAST_VERSION";
    public static int NEW_VERSION = 2;

    public AppData(ItemDb itemDb) {
        this.itemDb = itemDb;
    }

    public AppData(ItemDb itemDb, Context context) {
        this.itemDb = itemDb;
        this.context = context;
    }

    public List<Item> getBasicData(){
        String [] data = {"Visa","Passport"};
        return prepareItemList(MyConstans.BASIC_NEEDS_CAMEL_CASE,data);
    }

    public List<Item> getNeedsData(){
        String[] data = {"Backpack","Travel Lock"};
        return prepareItemList(MyConstans.NEEDS_CAMEL_CASE,data);
    }

    public List<Item> getCarSuppliesData(){
        String[] data = {"Pump","Car Jack"};
        return prepareItemList(MyConstans.CAR_SUPPLIES_CAMEL_CASE,data);
    }

    public List<Item> getBeachSupplies(){
        String[] data = {"Sea Glasses","Sea Bed"};
        return prepareItemList(MyConstans.BEACH_SUPPLIES_CAMEL_CASE,data);
    }

    public List<Item> getHealthData(){
        String[] data = {"Aspirin","Drugs Used"};
        return prepareItemList(MyConstans.HEALTH_CAMEL_CASE,data);
    }

    public List<Item> getFoodData(){
        String[] data = {"Snack","Juice","Coffee"};
        return prepareItemList(MyConstans.FOOD_CAMEL_CASE,data);
    }

    public List<Item> getTechnologyData(){
        String[] data = {"Mobile Phone","Camera","Headphone"};
        return prepareItemList(MyConstans.TECHNOLOGY_CAMEL_CASE,data);
    }

    public List<Item> getBabyNeedsData(){
        String[] data = {"Snapsuit","Baby Hat","Diaper"};
        return prepareItemList(MyConstans.BABY_NEEDS_CAMEL_CASE,data);
    }

    public List<Item> getClothingNeeds(){
        String[] data = {"Stockings","Underwear","Parjamas","Bursa"};
        return prepareItemList(MyConstans.CLOTHING_CAMEL_CASE,data);
    }

    public List<Item> getPersonelData(){
        String[] data = {"Tooth-brush","Razor Blade","Hair Clip"};
        return prepareItemList(MyConstans.PERSONAL_CARE_CAMEL_CASE,data);
    }


    List<Item> prepareItemList(String category,String[] data){
        List<Item> dataList = new ArrayList<>();
        for(int i=0;i<data.length;i++){
            dataList.add(new Item(data[i],category,false));
        }
        return  dataList;
    }


    public List<List<Item>> getAllItemLists(){
        List<List<Item>> lists = new ArrayList<>();
        lists.add(getBasicData());
        lists.add(getClothingNeeds());
        lists.add(getPersonelData());
        lists.add(getBabyNeedsData());
        lists.add(getHealthData());
        lists.add(getTechnologyData());
        lists.add(getFoodData());
        lists.add(getBeachSupplies());
        lists.add(getCarSuppliesData());
        lists.add(getNeedsData());
        return lists;
    }

    public void persistAllData(){
        List<List<Item>> lists = getAllItemLists();
        for(List<Item> list : lists){
            for(Item item : list) itemDb.getDao().insert(item);
        }
    }

}
