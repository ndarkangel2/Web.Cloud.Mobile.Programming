package com.example.ndarkangel.firebase;
/**
 * Created by Ndarkangel on 3/4/2018.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyBudget.db";
    public static final String EXPENSE_TABLE_NAME = "Category";
    public static final String EXPENSE_COLUMN_CATEGORY_NAME = "NAME";
    public static final String EXPENSE_ADD_COLUMN_AMOUNT = "AMOUNT";
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "create table " + EXPENSE_TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, AMOUNT TEXT)";
        db.execSQL(createTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
    public boolean insertCategory(String category_name, String amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EXPENSE_COLUMN_CATEGORY_NAME, category_name);//column name, column value
        values.put(EXPENSE_ADD_COLUMN_AMOUNT, amount);
        // Inserting Row
        long result = db.insert(EXPENSE_TABLE_NAME, null, values);//tableName, nullColumnHack, ContentValues
        assert (result < -1) : "Result is " + result;
        if (result == -1) return false;
        else return true;
    }
    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + EXPENSE_TABLE_NAME, null);
        return res;
    }
    public Cursor getSum() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor_EA = db.rawQuery("select NAME , sum(AMOUNT) As Total from Category group by NAME ", null);
        return cursor_EA;
    }
    public Cursor Cleardb() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor_DT = db.rawQuery("Delete from " + EXPENSE_TABLE_NAME, null);
        return cursor_DT;
    }
}
