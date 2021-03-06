package com.example.wilso.gympad;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ThursdayAdd extends AppCompatActivity {
    DatabaseHelper MONWORKOUT;
    EditText editName,editSets,editReps, editWeight, editID;
    Button btnAddData, btnUpdateData, btnDeleteData;
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thursday_add);
        MONWORKOUT = new DatabaseHelper(this);

        editName = (EditText)findViewById(R.id.mondayInput1);
        editSets = (EditText)findViewById(R.id.mondayInput2);
        editReps = (EditText)findViewById(R.id.mondayInput3);
        editWeight = (EditText)findViewById(R.id.mondayInput4);
        editID = (EditText)findViewById(R.id.mondayInput5);
        btnAddData = (Button)findViewById(R.id.mondayAdd2Data);
        btnUpdateData = (Button)findViewById(R.id.updateMonBtn);
        btnDeleteData = (Button)findViewById(R.id.deleteBtn);

        AddData();
        updateData();
        deleteData();
        //viewData();

    }

    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = editName.getText().toString();
                        String sets =  editSets.getText().toString();
                        String reps = editReps.getText().toString();
                        String weight = editWeight.getText().toString();

                        boolean isInserted = MONWORKOUT.insertThursData(name, sets, reps, weight);
                        if(isInserted == true)
                            Toast.makeText(ThursdayAdd.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(ThursdayAdd.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void updateData(){
        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = editID.getText().toString().length();
                if (temp > 0) {
                    boolean update = MONWORKOUT.updateThursData(editID.getText().toString(), editName.getText().toString(),
                            editSets.getText().toString(), editReps.getText().toString(), editWeight.getText().toString());
                    if (update == true) {
                        Toast.makeText(ThursdayAdd.this, "Successfully Updated Data!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(ThursdayAdd.this, "Something Went Wrong :(.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(ThursdayAdd.this, "You Must Enter An ID to Update :(.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void deleteData(){
        btnDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = editID.getText().toString().length();
                if(temp > 0){
                    Integer deleteRow = MONWORKOUT.deleteThursData(editID.getText().toString());
                    if(deleteRow > 0){
                        Toast.makeText(ThursdayAdd.this, "Successfully Deleted The Data!", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(ThursdayAdd.this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(ThursdayAdd.this, "You Must Enter An ID to Delete :(.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
