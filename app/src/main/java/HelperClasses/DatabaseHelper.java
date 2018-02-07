package HelperClasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import Models.UserTokens;

/**
 * Created by dejad on 2017-10-27.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "user.db";
    public static final String TABLE_NAME = "user_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "ACCESS_TOKEN";
    public static final String COL_3 = "USERNAME";
    public static final String COL_4 = "TOKEN_TYPE";
    public static final String COL_5 = "PASSWORDENC";
    public static final String COL_6 = "USERROLE";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,ACCESS_TOKEN TEXT,USERNAME TEXT,TOKEN_TYPE Text,PASSWORDENC TEXT,USERROLE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public UserTokens getUserTokenSQL(){
        UserTokens usTok = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("Select " + COL_1 + "," + COL_2 + "," + COL_3 + "," + COL_4 + "," + COL_5 + "," + COL_6 + " FROM " + TABLE_NAME + " LIMIT 1",null);
        if(c.getCount() > 0){
            if(c.moveToFirst()) {
                String AccessToken = c.getString(1);
                String Username = c.getString(2);
                String Token_type = c.getString(3);
                String PassEnc = c.getString(4);
                String role = c.getString(5);
                usTok = new UserTokens(AccessToken, Token_type,Username,PassEnc,role);
                return usTok;
            }
        }
        return null;
    }

    public boolean insertData(UserTokens ustkTok) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,ustkTok.getAccess_token());
        contentValues.put(COL_3,ustkTok.getUserName());
        contentValues.put(COL_4,ustkTok.getToken_type());
        contentValues.put(COL_5,ustkTok.getEncryptedPass());
        contentValues.put(COL_6,ustkTok.getroles());
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean updateData(UserTokens ustkTok) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,ustkTok.getAccess_token());
        contentValues.put(COL_3,ustkTok.getUserName());
        contentValues.put(COL_4,ustkTok.getToken_type());
        contentValues.put(COL_5,ustkTok.getEncryptedPass());
        contentValues.put(COL_6,ustkTok.getroles());
        db.update(TABLE_NAME, contentValues, "USERNAME = ?",new String[] { ustkTok.getUserName() });
        return true;
    }

    public Integer deleteData (UserTokens usTok) {
        if(usTok != null) {
            SQLiteDatabase db = this.getWritableDatabase();
            return db.delete(TABLE_NAME, "USERNAME = ?", new String[]{usTok.getUserName()});
        }
        return 0;
    }
}
