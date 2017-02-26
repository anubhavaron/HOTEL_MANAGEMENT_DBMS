package com.example.anubhav.hotel_management_dbms;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BILLING extends AppCompatActivity {
    EditText E;
    Button B;
    int h=0;
    DATABASE_GUEST BILL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);
        E=(EditText)findViewById(R.id.EE1);
        B=(Button)findViewById(R.id.BB1);
        BILL=new DATABASE_GUEST(this);
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=BILL.getALLData();
                String x=E.getText().toString();
                if(res.getCount()==0)
                {showMessage("ERROR","NOTHING FOUND");
                    return ;
                }

                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext())
                {   if(x.equals(res.getString(0))) {


                    h=1;
                    if((res.getString(6)).equals("GOLD")) {
                        buffer.append("ID :" + res.getString(0) + "\n");
                        buffer.append("NAME :" + res.getString(1) + "\n");
                        int b=(Integer.parseInt(res.getString(4))-Integer.parseInt(res.getString(3))+1)*3000;
                        buffer.append("BILL :" + b + "\n\n");
                    }
                    else

                        {

                            buffer.append("ID :" + res.getString(0) + "\n");
                            buffer.append("NAME :" + res.getString(1) + "\n");
                            int b=(Integer.parseInt(res.getString(4))-Integer.parseInt(res.getString(3))+1)*2000;
                            buffer.append("BILL :" + b + "\n\n");

                        }

                }

                }
                if(h==0)
                {   showMessage("ERROR","NOTHING FOUND");


                }
                else
                {
                    showMessage("DATA",buffer.toString());
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
