package com.example.comp_assignment1;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.comp_assignment1.DBSchema.RestaurantTable;
import com.example.comp_assignment1.DBSchema.FoodItemTable;
import com.example.comp_assignment1.DBSchema.UserTable;
import com.example.comp_assignment1.DBSchema.OrderTable;

public class DBHelper extends SQLiteOpenHelper
{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "SweetTooth.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        /* Setup "restaurant" table **/
        sqLiteDatabase.execSQL("CREATE TABLE "+ RestaurantTable.TABLE_NAME+"("+
                RestaurantTable.Cols.NAME+" TEXT UNIQUE,"+
                RestaurantTable.Cols.IMAGE+" TEXT, "+
                RestaurantTable.Cols.SPECIAL+" TEXT);");

        /* Setup "foodItem" table **/
        sqLiteDatabase.execSQL("CREATE TABLE " + FoodItemTable.TABLE_NAME+"("+
                FoodItemTable.Cols.RESTNAME+" TEXT, "+
                FoodItemTable.Cols.IMAGE+" TEXT UNIQUE, "+
                FoodItemTable.Cols.FOODNAME+" TEXT, "+
                FoodItemTable.Cols.INFORMATION+" TEXT, "+
                FoodItemTable.Cols.PRICE+" TEXT);");

        /* Setup "user" table **/
        sqLiteDatabase.execSQL("CREATE TABLE " + UserTable.TABLE_NAME + "(" +
                UserTable.Cols.EMAIL+" TEXT UNIQUE, "+
                UserTable.Cols.PASSWORD+" TEXT);");

        /* Setup "orderHistory" table **/
        sqLiteDatabase.execSQL("CREATE TABLE " + OrderTable.TABLE_NAME +"(" +
                OrderTable.Cols.DATE+ " DATE, "+
                OrderTable.Cols.RESTAURANT+ " TEXT, "+
                OrderTable.Cols.FOOD_ITEM+ " TEXT, "+
                OrderTable.Cols.TOTAL_COST+ " TEXT, "+
                OrderTable.Cols.QUANTITY+ " INTEGER, "+
                OrderTable.Cols.EMAIL+ " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RestaurantTable.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FoodItemTable.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + UserTable.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + OrderTable.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
