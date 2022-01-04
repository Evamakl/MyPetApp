package com.example.myproject;

import androidx.annotation.NonNull;
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
import android.view.Menu;
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

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
    private com.google.android.material.navigation.NavigationView NavigationView;
    private Menu menu;
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
        setDogs();
        NavigationView();
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
        NavigationView = findViewById(R.id.NavigationView);
        menu = NavigationView.getMenu();
        menu.findItem(R.id.hello).setTitle( "שלום, " + user.getUsername());
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
        for(int i=0; i<dog.getVaccines().size();i++)
            select.add(dog.getVaccines().get(i));
        ShowVaccine(select);
    }
    private void ShowVaccine(List<vaccine> selects){
        VaccineAdapter vaccineAdapter = new VaccineAdapter(this,selects);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(vaccineAdapter);
    }
    private void DogPick(){
        ArrayList<String> vaccine_name = new ArrayList();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Vaccine");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot temp : snapshot.getChildren()){
                    String vac = (String)temp.getValue().toString();
                    vaccine_name.add(vac);
                }
                DogClick(vaccine_name);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

    }
    private void DogClick(ArrayList<String> vaccine_name){
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
                ArrayAdapter<String> adapter = new ArrayAdapter<>(Vaccines.this, R.layout.dropdown_item, vaccine_name);
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
                        String date = day+"/"+(month+1)+"/"+year;
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
                            for(int i=0; i<user.getDogs().size();i++){
                                if(user.getDogs().get(i).equals(dog)){
                                    user.getDogs().get(i).AddVaccine(new vaccine(TextViewDog.getText().toString(), TextViewVaccineDate.getText().toString()));
                                    dog.AddVaccine(new vaccine(TextViewDog.getText().toString(), TextViewVaccineDate.getText().toString()));
                                    databaseReference.child(user.getUid()).setValue(user);
                                }
                            }
                            select.add(new vaccine(TextViewDog.getText().toString(), TextViewVaccineDate.getText().toString()));
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
    private void StartActivity(Class Dest){
        Intent intent = new Intent(Vaccines.this, Dest);
        intent.putExtra("dog",dog);
        intent.putExtra("user",user);
        startActivity(intent);
        finish();
    }
    public void NavigationView() {
        NavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {
                int id = item.getItemId();
                new OwnerNavigation(Vaccines.this,id,user,item);
                return false;
            }
        });
    }
    private void setDogs(){
        menu = NavigationView.getMenu();
        for(int i=0; i<user.getDogs().size();i++)
            menu.findItem(R.id.Dogs).getSubMenu().add(user.getDogs().get(i).getName());
    }
};
