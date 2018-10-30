package vn.edu.poly.thithat.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.thithat.model.Model;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String NAME_DATA="qlnh";
    private static final int VERSION_DATA=1;
    private static final String TABLE_NAME="Food";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_PRICE="price";
    public DatabaseHelper(Context context) {
        super(context, NAME_DATA, null, VERSION_DATA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + "("
                        + COLUMN_ID + " TEXT PRIMARY KEY,"
                        + COLUMN_NAME + " TEXT,"
                        + COLUMN_PRICE + " LONG"
                        + ")";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void insertFood(Model food) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,food.getId());
        values.put(COLUMN_NAME,food.getName());
        values.put(COLUMN_PRICE,food.getPrice());
        long id=db.insert(TABLE_NAME, null, values);
        db.close();
        // return newly inserted row id
    }
    public List<Model> getFoodAll() {
        List<Model> list1= new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;         //retrieve data from the database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor1 = db.rawQuery(selectQuery, null);
        if (cursor1.moveToFirst()) {
            do {
                Model food = new Model();
                food.setId(cursor1.getString(cursor1.getColumnIndex(COLUMN_ID)));
                food.setName(cursor1.getString(cursor1.getColumnIndex(COLUMN_NAME)));
                food.setPrice(cursor1.getLong(cursor1.getColumnIndex(COLUMN_PRICE)));

                list1.add(food);
            } while (cursor1.moveToNext());
        }
        cursor1.close();
        db.close();
        return list1;
    }
    public void deleteFood(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID+ " = ?",
                new String[]{id});
        db.close();

    }
    public int updateFood(String id,Model food) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,food.getName());
        values.put(COLUMN_PRICE,food.getPrice());

        // updating row
        return db.update(TABLE_NAME, values, COLUMN_ID + " = ?",
                new String[]{id});
    }
}
