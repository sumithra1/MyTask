package com.example.viewpager_recyclerview;

import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Second_activity extends AppCompatActivity {
    TextView textView, textViewid;
    ImageView image;
    EditText newid,newcomments;
    EditText editText,getEditTextid;
    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activity);
        mydb=new DatabaseHelper(this);
        newid=(EditText)findViewById(R.id.getid);
        newcomments=(EditText)findViewById(R.id.edit_text) ;
        String fid = getIntent().getStringExtra("id");
        String fname = getIntent().getStringExtra("name");
        String fimg = getIntent().getStringExtra("img");
        String fedit = getIntent().getStringExtra("text");
        textViewid = findViewById(R.id.json_id);
        textView = findViewById(R.id.txtview);
        image = findViewById(R.id.imgd);
        editText = findViewById(R.id.edit_text);
        textViewid.setText(fid);
        textView.setText(fname);
        Picasso.get().load(fimg).into(image);
        editText.setText(fedit);
        getEditTextid=(EditText)findViewById(R.id.getid);


    }

    public void viewData(View view) {
        Cursor res=  mydb.getAllData();
        if (res.getCount()==0){
            showmessage("error","no data found");
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while (res.moveToNext()){

            buffer.append("Id:"+res.getString(0)+"\n");
            buffer.append("Name:"+res.getString(1)+"\n");
            buffer.append("Comments:"+res.getString(2)+"\n");


        }
        showmessage("Data",buffer.toString());
    }
    public void  showmessage(String title,String Message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    public void UpdateData(View view) {
        boolean isUpdate=mydb.updateDatas(newid.getText().toString(),newcomments.getText().toString());
        if(isUpdate==true){
            Toast.makeText(getApplicationContext(),"Data Updated",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Data not Updated",Toast.LENGTH_SHORT).show();

        }
    }


    public void DeletaData(View view) {
        boolean deleteRow=mydb.DeleteData(newid.getText().toString());
        if(deleteRow==true){
            Toast.makeText(getApplicationContext(),"Data Deleted",Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(getApplicationContext(),"Data not Deleted",Toast.LENGTH_SHORT).show();

        }
    }
}
