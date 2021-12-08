package com.example.datasync.offline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.datasync.model.SQLiteModelClass;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "details";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "userdetails";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String NUMBER_COL = "number";
    private static final String EMAIL_COL = "email";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + "("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + NUMBER_COL + " TEXT,"
                + EMAIL_COL + " TEXT)";

        db.execSQL(query);
    }

    public void addNewUSer(String userName, String contNumber, String emailAddress) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NAME_COL, userName);
        values.put(NUMBER_COL, contNumber);
        values.put(EMAIL_COL, emailAddress);

        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    public ArrayList<SQLiteModelClass> readDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<SQLiteModelClass> sqLiteModelClassArrayList = new ArrayList<>();

        if (cursorCourses.moveToFirst()) {
            do {
                sqLiteModelClassArrayList.add(new SQLiteModelClass(cursorCourses.getString(1), cursorCourses.getString(2), cursorCourses.getString(3)));
            } while (cursorCourses.moveToNext());
        }

        cursorCourses.close();
        return sqLiteModelClassArrayList;
    }

    public void updateDetails(String OriginalName, String userName, String contNumber, String emailAddress) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME_COL, userName);
        values.put(NUMBER_COL, contNumber);
        values.put(EMAIL_COL, emailAddress);

        db.update(TABLE_NAME, values, "name=?", new String[]{OriginalName});
        db.close();

    }

    public void deleteDetails(String userName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "name=?", new String[]{userName});
        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
