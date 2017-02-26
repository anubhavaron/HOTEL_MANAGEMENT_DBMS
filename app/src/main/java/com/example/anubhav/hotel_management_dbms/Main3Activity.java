package com.example.anubhav.hotel_management_dbms;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main3Activity extends AppCompatActivity {
    Button b1,b2,b3,b4;int c=0;int rooms=4;
    DATABASE_GUEST p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        b1=(Button)findViewById(R.id.i1);
        b2=(Button)findViewById(R.id.i2);
        b3=(Button)findViewById(R.id.B3);
        b4=(Button)findViewById(R.id.bill);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i6;
                i6 = new Intent(Main3Activity.this,BILLING.class);
                startActivity(i6);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i5;
                i5 = new Intent(Main3Activity.this,GUEST_INSERT_INFO.class);
                startActivity(i5);
            }

        });
        p=new DATABASE_GUEST(this);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c=0;
                Cursor res=p.getALLData();


                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext())
                {   if(res.getString(0).equals(""))
                    {}
                    else
                    {
                        c++;


                    }



                }

                if(c==4)
                {
                    buffer.append("  0 ROOMS  \n");shoMessage("NO ROOMS AVAILABLE",buffer.toString());

                }
                else {
                    buffer.append((rooms-c)+"   \n");
                    shoMessage("ROOMS AVAILABLE", buffer.toString());

                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=p.getALLData();
                if(res.getCount()==0)
                {shoMessage("ERROR","NOTHING FOUND");
                    return ;
                }

                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext())
                {
                    buffer.append("ID :"+res.getString(0)+"\n");
                    buffer.append("NAME :"+res.getString(1)+"\n");
                    buffer.append("mobile no :"+res.getString(2)+"\n\n");


                }
                shoMessage("DATA",buffer.toString());
            }
        });


    }
    public void shoMessage(String title,String Message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
