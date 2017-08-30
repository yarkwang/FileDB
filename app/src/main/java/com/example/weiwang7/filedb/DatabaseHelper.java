package com.example.weiwang7.filedb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 *  Created by wei.wang7 on 2017/8/18.
 */

class DatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME="file_db.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_FILE="file";
    private static final String KEY_FILE_NAME="file_name";
    private static final String KEY_FILE_EXTENSION="file_extension";
    private static final String KEY_MD5="md5";
    private static final String KEY_POSITION="position";
    private static final String KEY_LENGTH="length";
    private static final String KEY_SIZE="size";
    private static final String KEY_KEYWORD="keyword";
    private final String TAG = this.getClass().getSimpleName();

    DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE_CALL = "CREATE TABLE IF NOT EXISTS " + TABLE_FILE + "(" + KEY_FILE_NAME + " TEXT," + KEY_FILE_EXTENSION + " TEXT,"
                + KEY_MD5 + " TEXT," + KEY_POSITION + " TEXT," + KEY_LENGTH + " TEXT," + KEY_SIZE + " TEXT," + KEY_KEYWORD
                + " TEXT" + ")";
        Log.v(TAG, "CREATE TABLE CALL: " + CREATE_TABLE_CALL);
        db.execSQL(CREATE_TABLE_CALL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FILE);
        onCreate(db);
    }

    /* Method for fetching records from database */
    ArrayList<FileRecord> getAllFiles(){
        String query="SELECT * FROM " + TABLE_FILE + " ORDER BY " + KEY_FILE_NAME;
        ArrayList<FileRecord> files=new ArrayList<>();
        SQLiteDatabase database=getReadableDatabase();
        Cursor c=database.rawQuery(query, null);
        if(c != null){
            while(c.moveToNext()){
                String file_name=c.getString(c.getColumnIndex(KEY_FILE_NAME));
                String file_extension=c.getString(c.getColumnIndex(KEY_FILE_EXTENSION));
                String md5=c.getString(c.getColumnIndex(KEY_MD5));
                String position=c.getString(c.getColumnIndex(KEY_POSITION));
                String length=c.getString(c.getColumnIndex(KEY_LENGTH));
                String size=c.getString(c.getColumnIndex(KEY_SIZE));
                String keyword=c.getString(c.getColumnIndex(KEY_KEYWORD));

                FileRecord fileRecord=new FileRecord();
                fileRecord.setFile_name(file_name);
                fileRecord.setFile_extension(file_extension);
                fileRecord.setMd5(md5);
                fileRecord.setPosition(position);
                fileRecord.setLength(length);
                fileRecord.setSize(size);
                fileRecord.setKeyword(keyword);

                files.add(fileRecord);
            }
        }

//        c.moveToFirst();
//        if(! c.isAfterLast()){
//            do{
//                String file_name=c.getString(c.getColumnIndex(KEY_FILE_NAME));
//                String file_extension=c.getString(c.getColumnIndex(KEY_FILE_EXTENSION));
//                String md5=c.getString(c.getColumnIndex(KEY_MD5));
//                String position=c.getString(c.getColumnIndex(KEY_POSITION));
//                String length=c.getString(c.getColumnIndex(KEY_LENGTH));
//                String size=c.getString(c.getColumnIndex(KEY_SIZE));
//                String keyword=c.getString(c.getColumnIndex(KEY_KEYWORD));
//
//                FileRecord fileRecord=new FileRecord();
//                fileRecord.setFile_name(file_name);
//                fileRecord.setFile_extension(file_extension);
//                fileRecord.setMd5(md5);
//                fileRecord.setPosition(position);
//                fileRecord.setLength(length);
//                fileRecord.setSize(size);
//                fileRecord.setKeyword(keyword);
//
//                files.add(fileRecord);
//            }while(c.moveToNext());
//        }
        if(c!=null){
            c.close();
        }
        return files;
    }
}
