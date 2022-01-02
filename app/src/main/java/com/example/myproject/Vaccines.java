package com.example.myproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Vaccines extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<vaccine> select;
    private ImageView addVaccine;
    private TextView TextViewVaccineDate,TextViewDog,TextViewSearch, Title;
    private ListView ListViewSearch;
    private DatePickerDialog datePickerDialog;
    private EditText EditTextSearch;
    private ImageView MenuItem, BackItem;
    private DrawerLayout drawerLayout;
    private Dialog dialog;
    private Button buttonAddvaccine;
    private User user = new User();
    private Dog dog;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccines);
        init();
    }
    private void init(){
        setID();
        getInfo();
        addVaccine();
        MenuIcon();
        BackIcon();
    }
    private void setID(){
        select = new ArrayList<>();
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        dog = (Dog)intent.getSerializableExtra("dog");
        recyclerView = findViewById(R.id.rv);
        addVaccine = findViewById(R.id.addVaccine);
        MenuItem = findViewById(R.id.MenuItem);
        BackItem = findViewById(R.id.BackItem);
        drawerLayout = findViewById(R.id.drawerLayout);
        Title = findViewById(R.id.Title);
        Title.setText("חיסונים");
    }
    private void MenuIcon(){
        MenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });
    }
    private void BackIcon(){
        BackItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vaccines.this, Vaccines_and_Medicines_slide.class);
                intent.putExtra("user",user);
                intent.putExtra("dog",dog);
                startActivity(intent);
                finish();
            }
        });
    }
    private void getInfo(){
        for(int i=0; i<3;i++) //vaccine from dogs
            select.add(new vaccine("SIMBA", "1/1/2020"));
        ShowVaccine(select);
    }
    private void ShowVaccine(List<vaccine> selects){
        VaccineAdapter vaccineAdapter = new VaccineAdapter(this,selects);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(vaccineAdapter);
    }
    private void DogPick(){
        ArrayList<String> dog = new ArrayList();
        for(int i=0; i < user.getDogs().size() ; i++)
            dog.add(user.getDogs().get(i).getName());
        TextViewDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(Vaccines.this);
                dialog.setContentView(R.layout.dialog_search_spinner);
                dialog.getWindow().setLayout(1000,1000);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                EditTextSearch = dialog.findViewById(R.id.EditTextSearch);
                ListViewSearch = dialog.findViewById(R.id.ListViewSearch);
                TextViewSearch = dialog.findViewById(R.id.TextViewSearch);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(Vaccines.this, R.layout.dropdown_item, dog);
                ListViewSearch.setAdapter(adapter);
                EditTextSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        adapter.getFilter().filter(charSequence);
                    }
                    @Override
                    public void afterTextChanged(Editable editable) { }
                });
                ListViewSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        dialog.dismiss();
                        TextViewDog.setText(adapterView.getItemAtPosition(i).toString());
                    }
                });
            }
        });
    }
    private void DatePick(){
        TextViewVaccineDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java.util.Calendar calendar = java.util.Calendar.getInstance();
                int year = calendar.get(java.util.Calendar.YEAR);
                int month = calendar.get(java.util.Calendar.MONTH) + 1 ;
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(Vaccines.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String date = day+"/"+month+"/"+year;
                        TextViewVaccineDate.setText(date);
                    }
                }, year,month,day);
                datePickerDialog.show();
            }
        });
    }
    private void addVaccine(){
        addVaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Vaccines.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.addvaccine_dialog,null);
                builder.setCancelable(false);
                builder.setView(dialogView);
                TextViewDog = dialogView.findViewById(R.id.TextViewDog);
                TextViewVaccineDate = dialogView.findViewById(R.id.TextViewVaccineDate);
                buttonAddvaccine = dialogView.findViewById(R.id.buttonAddvaccine);
                AlertDialog alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.show();
                DogPick();
                DatePick();
                buttonAddvaccine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(TextViewVaccineDate.getText().length() > 0 && TextViewDog.getText().length() > 0) {
                            alertDialog.cancel();
                            //save vaccine in dog and firebase
                            select.add(new vaccine(TextViewDog.getText().toString(), TextViewVaccineDate.toString()));
                            ShowVaccine(select);
                        }
                        else{
                            Toast.makeText(Vaccines.this, "Please pick dog and date!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
};
