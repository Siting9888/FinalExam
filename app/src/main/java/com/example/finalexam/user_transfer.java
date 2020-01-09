package com.example.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class user_transfer extends AppCompatActivity {
    private MyOpenHelper dbHelper;
    private EditText userNumber;
    private EditText moneyValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_transfer);
        dbHelper=new MyOpenHelper(this);
        userNumber = (EditText) findViewById(R.id.userNumber);
        moneyValue = (EditText) findViewById(R.id.moneyValue);
        findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                transferMoney();
                findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirm();
                    }
                });
            }
        });
    }
    private void transferMoney(){
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("cash", getIntent().getIntExtra("cash",0) + Integer.parseInt(moneyValue.getText().toString()));
        db.update("usertable", values, "username=?", new String[]{userNumber.getText().toString()});
        ContentValues values2 = new ContentValues();
        values2.put("cash", getIntent().getIntExtra("cash",2000) - Integer.parseInt(moneyValue.getText().toString()));
        db.update("usertable",values2,"username=?",new String[]{getIntent().getStringExtra("username")});
    }
    public void confirm() {
        finish();
    }
}
