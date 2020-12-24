    package com.example.viewpager_recyclerview;
    import android.content.ContentValues;
    import android.content.Context;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;

    import androidx.annotation.Nullable;

    public class DatabaseHelper extends SQLiteOpenHelper {
        public static final String Database_name = "Networks.db";
        public static final String Table_name = "User_table";
        public static final String col1 = "MyId";
        public static final String col2 = "Name";
        public static final String col3 = "Comments";

        public DatabaseHelper(@Nullable Context context) {
            super(context, Database_name, null, 1);


        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("Create table "+Table_name+"(MyId Text,Name Text,Comments Text)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(" DROP TABLE IF EXISTS "+Table_name);
            onCreate(db);

        }
        public boolean insertData(String MyId,String Name,String Comments){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues=new ContentValues();
            contentValues.put(col1,MyId);
            contentValues.put(col2,Name);
           contentValues.put(col3,Comments);
            long result=db.insert(Table_name,null,contentValues);
            if (result==-1){
                return false;
            }
            else
                return true;
        }
        public Cursor getAllData(){
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res=db.rawQuery("Select * from "+Table_name,null);
            return res;
        }
        public boolean updateDatas(String MyId,String comments){
            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues contentValues =new ContentValues();
            contentValues.put(col1,MyId);
    //        contentValues.put(col2,name);
           contentValues.put(col3,comments);
            db.update(Table_name,contentValues,"MyId = ?",new String[]{MyId});
            return true;

        }
        public  boolean DeleteData(String Myid){
            SQLiteDatabase db=this.getWritableDatabase();
             db.delete(Table_name,"MyId = ?",new String[]{Myid});
            return true;

        }

    }

