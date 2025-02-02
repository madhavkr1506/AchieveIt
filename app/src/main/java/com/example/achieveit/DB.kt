package com.example.achieveit

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.achieveit.Update

open class DB(context: Context?, name: String? = "School", factory: SQLiteDatabase.CursorFactory? = null, version: Int = 1) :
    SQLiteOpenHelper(context, name, factory, version) {
        private val tableName : String = "Class";
    private val EntryTimestamp : String = "Timestamp";
    private val NarrativeEntry : String = "Narrative";
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table $tableName( $EntryTimestamp text, $NarrativeEntry text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.execSQL("drop table if exists $tableName")
        onCreate(db);
    }

    fun onInsert(logDate : String, reflectionNote : String){
        val content : ContentValues = ContentValues();
        content.put(EntryTimestamp,logDate);
        content.put(NarrativeEntry,reflectionNote);

        val database : SQLiteDatabase = this.writableDatabase;
        database.insert(tableName,null,content);

        database.close();
    }


    fun onRead() : Cursor{
        val database : SQLiteDatabase = this.readableDatabase;
        val cursor : Cursor = database.rawQuery("select * from $tableName",null);
        return cursor;
    }

    fun onDelete(date : String){
        val database : SQLiteDatabase = this.writableDatabase;
        database.delete(tableName,"$EntryTimestamp = ?" , arrayOf(date));
        database.close();
    }

    fun onSendContent(date : String, updateFragment : Update){
        val database : SQLiteDatabase = this.readableDatabase;
        val cursor = database.rawQuery("select * from $tableName where $EntryTimestamp = ?",
            arrayOf(date)
        );
        if(cursor.moveToFirst()){
            val content = cursor.getString(1);
            updateFragment.activity?.runOnUiThread{
                updateFragment.getContent(content);
            }

        }
        cursor.close()
        database.close();
    }

    fun onUpdate(date : String, content : String){
        val database : SQLiteDatabase = this.writableDatabase;
        val contentValue : ContentValues = ContentValues();
        contentValue.put(EntryTimestamp,date);
        contentValue.put(NarrativeEntry,content);
        database.update(tableName,contentValue,"$EntryTimestamp = ?", arrayOf(date));
        database.close();
    }
}