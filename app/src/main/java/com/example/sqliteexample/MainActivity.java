package com.example.sqliteexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText NameID;
    EditText NameStudent;
    Button LoadData;
    Button AddData;
    TextView DataList;
    String TAG = "abc";
    Button Update;
    Button delete;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


        final DataHandler dataHandler = new DataHandler(getApplicationContext());
        AddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                int id = Integer.parseInt(NameID.getText().toString());
                String name = NameStudent.getText().toString();

                Student student = new Student();
                student.setID(id);
                student.setName(name);

                dataHandler.addDataStudent(student);
                Log.d(TAG, "onClick: Add Data Success!");
                NameID.setText("");
                NameStudent.setText("");

            }
        });


        LoadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String result = dataHandler.LoadDataHandler();
                Log.d(TAG, "onClick: "+result);
                DataList.setText(result);
                Log.d(TAG, "onClick: Load Data Success!");
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataList.setText(dataHandler.findDataHandler(Integer.parseInt(NameID.getText().toString())));


            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result =  dataHandler.deleteStudent(Integer.parseInt(NameID.getText().toString()));
                if(result){
                    Toast.makeText(getApplicationContext(),"Delete success",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Delete fail",Toast.LENGTH_SHORT).show();
                }

            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    
            }
        });




    }

    private void init(){
        NameID = findViewById(R.id.NameID);
        NameStudent = findViewById(R.id.NameStudent);
        LoadData = findViewById(R.id.LoadData);
        AddData = findViewById(R.id.AddStudent);
        DataList = findViewById(R.id.DataList);
        Update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        search = findViewById(R.id.search);
    }


}
