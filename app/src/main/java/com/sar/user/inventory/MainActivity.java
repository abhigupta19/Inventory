package com.sar.user.inventory;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.editText3)
    EditText editText3;
    @BindView(R.id.editText4)
    EditText editText4;
    @BindView(R.id.textView)
    TextView textView;
    DatabaseHelper databaseHelper;
    StringBuffer stringBuffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        databaseHelper=new DatabaseHelper(this);


    }

    @OnClick({R.id.button, R.id.button2, R.id.button3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                //add data

                boolean a=databaseHelper.insert(editText.getText().toString(),editText2.getText().toString(),editText3.getText().toString(),editText4.getText().toString());
                if (a==true)
                    Toast.makeText(MainActivity.this,"scucces",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"unscucces",Toast.LENGTH_SHORT).show();

                break;
            case R.id.button2:
                //show
                Cursor res=databaseHelper.showdata();
                if(res.getCount()==0)
                {
                    Toast.makeText(MainActivity.this,"no data",Toast.LENGTH_SHORT).show();

                }
                    stringBuffer=new StringBuffer();
                while (res.moveToNext())
                {

                    stringBuffer.append("innovice= "+res.getString(0)+'\n');
                    stringBuffer.append("name= "+res.getString(1)+'\n');
                    stringBuffer.append("company= "+res.getString(2)+'\n');
                    stringBuffer.append("quentity= "+res.getString(3)+'\n');

                }
                textView.setText(stringBuffer);
                break;
            case R.id.button3:
                //delete
                Cursor cursor=databaseHelper.delete(editText.getText().toString());
                if(cursor.getCount()==0)
                {
                    Toast.makeText(MainActivity.this,"nothenig",Toast.LENGTH_SHORT).show();

                }
                StringBuffer stringBuffer1=new StringBuffer();
                while (cursor.moveToNext())
                {
                    stringBuffer1.append("innovice= "+cursor.getString(0)+'\n');
                    stringBuffer1.append("name= "+cursor.getString(1)+'\n');
                    stringBuffer1.append("company= "+cursor.getString(2)+'\n');
                    stringBuffer1.append("quentity= "+cursor.getString(3)+'\n');
                }

                break;
        }
    }
}
