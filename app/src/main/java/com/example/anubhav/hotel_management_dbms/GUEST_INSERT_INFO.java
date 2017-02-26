package com.example.anubhav.hotel_management_dbms;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GUEST_INSERT_INFO extends AppCompatActivity {

    DATABASE_GUEST mydb;
    EditText e1,e2,e3,e4,e5,e6,e7,e8;
    Button add,view,up,del;static int ROOMS=4,c=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest__insert__info);
        e1=(EditText)findViewById(R.id.E1);
        e2=(EditText)findViewById(R.id.E2);
        e3=(EditText)findViewById(R.id.E3);
        e4=(EditText)findViewById(R.id.E4);
        e5=(EditText)findViewById(R.id.E5);
        e6=(EditText)findViewById(R.id.E6);
        e7=(EditText)findViewById(R.id.E7);
        e8=(EditText)findViewById(R.id.E8);
        add=(Button)findViewById(R.id.ADD);
        view=(Button)findViewById(R.id.VIEW);
        del=(Button)findViewById(R.id.delete);
        up=(Button)findViewById(R.id.UPDATE);
        mydb=new DATABASE_GUEST(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c=0;

                Cursor res=mydb.getALLData();


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
                    Toast toast = Toast.makeText(GUEST_INSERT_INFO.this, "NO ROOMS AVAILABLE", Toast.LENGTH_LONG);
                    toast.show();

                }
                else {
                        String x=e1.getText().toString();
                    if(x.equals(""))
                    {return ;}
                    else {

                        boolean IS = mydb.insertData(e1.getText().toString(), e2.getText().toString(), e3.getText().toString(), e4.getText().toString(), e5.getText().toString(), e6.getText().toString(), e7.getText().toString(), e8.getText().toString());
                        if (IS == true) {

                            Toast toast = Toast.makeText(GUEST_INSERT_INFO.this, "Inserted", Toast.LENGTH_LONG);
                            toast.show();
                        } else {
                            Toast toast = Toast.makeText(GUEST_INSERT_INFO.this, "NOT Inserted", Toast.LENGTH_LONG);
                            toast.show();
                        }
                        ROOMS = ROOMS - 1;
                    }


                }


            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=mydb.getALLData();
                if(res.getCount()==0)
                {showMessage("ERROR","NOTHING FOUND");
                    return ;
                }

                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext())
                {
                    buffer.append("ID :"+res.getString(0)+"\n");
                    buffer.append("NAME :"+res.getString(1)+"\n");
                    buffer.append("mobile no :"+res.getString(2)+"\n\n");


                }
                showMessage("DATA",buffer.toString());
            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean is=mydb.updatedata(e1.getText().toString(), e2.getText().toString(), e3.getText().toString(),e4.getText().toString(),e5.getText().toString(),e6.getText().toString(),e7.getText().toString(),e8.getText().toString());
                if(is==true)
                {
                    Toast toast = Toast.makeText(GUEST_INSERT_INFO.this, "Updated", Toast.LENGTH_LONG);
                    toast.show();

                }
                else
                {
                    Toast toast = Toast.makeText(GUEST_INSERT_INFO.this, "NOT Updated", Toast.LENGTH_LONG);
                    toast.show();
                }


            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedrows=mydb.deletefunc(e1.getText().toString());
                if(deletedrows>0)
                {
                    Toast toast = Toast.makeText(GUEST_INSERT_INFO.this, "Deleted", Toast.LENGTH_LONG);
                    toast.show();

                }
                else
                {
                    Toast toast = Toast.makeText(GUEST_INSERT_INFO.this, "NOT Deleted", Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });

    }

    public void showMessage(String title,String Message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
