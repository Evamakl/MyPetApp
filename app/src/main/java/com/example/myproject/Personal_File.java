package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

/*import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;*/
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Personal_File extends AppCompatActivity  {
    ImageView dog_image;
    private TextInputLayout dog_name,city,Gender,BirthDay,type;
    private ImageView DogPic,addDogPic;
    private ImageView MenuItem, BackItem;
    private Menu menu;
    private DrawerLayout drawerLayout;
    private Button Done;
    private User user;
    private Dog dog;
    private Intent intent;
    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private Uri uri = null;
    private DatePickerDialog datePickerDialog;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
    private com.google.android.material.navigation.NavigationView NavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_file);
        init();
    }
    private void init(){
        setID();
        MenuIcon();
        BackIcon();
        NavigationView();
        setDogs();
        setButtons();
        AddImage();
    }
    private void setID(){
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        dog = (Dog)intent.getSerializableExtra("dog");
        dog_name = findViewById(R.id.TextInputLayoutName);
        dog_name.getEditText().setText(dog.getName());
        city = findViewById(R.id.TextInputLayoutMed_Backg);
        city.getEditText().setText(dog.getCity());
        Gender = findViewById(R.id.TextInputLayoutGender);
        Gender.getEditText().setText(dog.getGender());
        BirthDay = findViewById(R.id.TextInputLayoutDate);
        BirthDay.getEditText().setText(dog.getBirthDay());
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        int year = calendar.get(java.util.Calendar.YEAR);
        int month = calendar.get(java.util.Calendar.MONTH) + 1 ;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        BirthDay.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(Personal_File.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String date = day+"/"+(month+1)+"/"+year;
                        BirthDay.getEditText().setText(date);
                    }
                }, year,month,day);
                datePickerDialog.show();
            }
        });
        Done = findViewById(R.id.Done);
        type = findViewById(R.id.TextInputLayoutType);
        type.getEditText().setText(dog.getType());
        DogPic = findViewById(R.id.DogPic);
        if(dog.getImage().equals("")){
            DogPic.setImageResource(R.mipmap.ic_launcher);
        }
        /*else {
            Glide.with(Personal_File.this).asBitmap().load(dog.getImage()).into(new CustomTarget<Bitmap>() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable com.bumptech.glide.request.transition.Transition<? super Bitmap> transition) {
                    DogPic.setBackground(new BitmapDrawable(getResources(), resource));
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) { }
            });
        }*/
        addDogPic = findViewById(R.id.addpicdog);
        MenuItem = findViewById(R.id.MenuItem);
        BackItem = findViewById(R.id.BackItem);
        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView = findViewById(R.id.NavigationView);
        menu = NavigationView.getMenu();
        menu.findItem(R.id.hello).setTitle( "שלום, " + user.getUsername());
    }
    private void setButtons(){
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckValues()){
                    UploadImage();
                }
            }
        });
    }
    private void StartActivity(Class Dest){
        Intent intent = new Intent(Personal_File.this, Dest);
        intent.putExtra("dog",dog);
        intent.putExtra("user",user);
        startActivity(intent);
        finish();
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
                StartActivity(PetOwnerOptionsOfDog.class);
            }
        });
    }
    public void NavigationView() {
        NavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {
                int id = item.getItemId();
                new OwnerNavigation(Personal_File.this,id,user,item);
                return false;
            }
        });
    }
    private void setDogs(){
        menu = NavigationView.getMenu();
        for(int i=0; i<user.getDogs().size();i++)
            menu.findItem(R.id.Dogs).getSubMenu().add(user.getDogs().get(i).getName());
    }
    private void AddImage(){
        addDogPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { chooseProfilePicture(); }
        });
    }
    private void chooseProfilePicture() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Personal_File.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_profile_picture,null);
        builder.setCancelable(false);
        builder.setView(dialogView);
        ImageView Cammera = dialogView.findViewById(R.id.Cammera);
        ImageView Gallery = dialogView.findViewById(R.id.Gallery);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
        Cammera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkAndRequestPermissions())
                    CammeraPicture();
                alertDialog.cancel();
            }
        });
        Gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryPicture();
                alertDialog.cancel();
            }
        });
    }
    private void GalleryPicture(){
        Intent photo = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        photo.setType("image/*");
        startActivityForResult(photo, 1);
    }
    private void CammeraPicture(){
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePicture.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takePicture, 2);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    uri = data.getData();
                    Bitmap bitmap = null;
                    try { bitmap = MediaStore.Images.Media.getBitmap(Personal_File.this.getContentResolver(), uri);
                    } catch (IOException e) { e.printStackTrace(); }
                    DogPic.setBackground(new BitmapDrawable(getResources(), bitmap));
                }
                break;
            case 2:
                if(resultCode == RESULT_OK){
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    DogPic.setBackground(new BitmapDrawable(getResources(), bitmap));
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    try {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 500, bytes);
                        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, user.getUid(), null);
                        uri = Uri.parse(path);
                    }catch (Exception e) { e.printStackTrace(); }
                }
                break;
        }
    }
    private Boolean checkAndRequestPermissions(){
        if(Build.VERSION.SDK_INT >= 23 ){
            int cameraPermission = ActivityCompat.checkSelfPermission(Personal_File.this, Manifest.permission.CAMERA);
            if(cameraPermission == PackageManager.PERMISSION_DENIED){
                ActivityCompat.requestPermissions(Personal_File.this,new String[]{Manifest.permission.CAMERA},20);
                return false;
            }
        }
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 20 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            CammeraPicture();
        else{ }
    }
    private void UploadImage(){
        storageReference = firebaseStorage.getReference();
        StorageReference ImageRefrence = storageReference.child(firebaseAuth.getCurrentUser().getUid()+".jpg");
        ImageRefrence.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                task.getResult().getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        int index = -1;
                        for(int i=0;i<user.getDogs().size();i++)
                            if(user.getDogs().get(i).equals(dog))
                                index = i;
                        dog.setImage(uri.toString());
                        dog.setType(type.getEditText().getText().toString());
                        dog.setCity(city.getEditText().getText().toString());
                        dog.setName(dog_name.getEditText().getText().toString());
                        dog.setBirthDay(BirthDay.getEditText().getText().toString());
                        dog.setGender(Gender.getEditText().getText().toString());
                        user.getDogs().get(index).setDog(dog);
                        updateData();
                    }
                });
            }
        });
    }
    private void updateData(){
        databaseReference.child(user.getUid()).setValue(user);
        Intent intent = new Intent(Personal_File.this, PetOwnerOptionsOfDog.class);
        intent.putExtra("dog",dog);
        intent.putExtra("user",user);
        Toast.makeText(Personal_File.this, "Information updated!", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
    private Boolean CheckValues(){
        if(dog_name.getEditText().getText().length() == 0) {
            dog_name.setHelperText("חובה להזין את שם הכלב");
            return false;
        }else if(!dog_name.getEditText().getText().toString().matches("[a-zA-Z ]+")) {
            dog_name.setHelperText("ENTER ONLY ALPHABETICAL CHARACTER");
            return  false;
        }else
            dog_name.setHelperText("");


        if (type.getEditText().getText().length() == 0){
            type.setHelperText("חובה להזין את סוג הכלב");
            return false;
        }else if(!type.getEditText().getText().toString().matches("[a-zA-Z ]+"))
        {
            type.setHelperText("ENTER ONLY ALPHABETICAL CHARACTER");
            return false;
        }else
            type.setHelperText("");


        if (Gender.getEditText().getText().length() == 0){
            Gender.setHelperText("חובה להזין את מין הכלב");
            return false;
        }else if(!Gender.getEditText().getText().toString().matches("[a-zA-Z ]+"))
        {
            Gender.setHelperText("ENTER ONLY ALPHABETICAL CHARACTER");
            return false;
        }else
            Gender.setHelperText("");


        if (BirthDay.getEditText().getText().length() == 0) {
            BirthDay.setHelperText("חובה להזין את תאריך לידה של הכלב");
            return false;
        }/*else if(BirthDay.getEditText().getText().length() != 0)
        {
            SimpleDateFormat sdf = new SimpleDateFormat(BirthDay.getEditText().getText().toString());
            Date strDate = sdf.parse();
            boolean your_date_is_outdated;
            if (new Date().after(strDate)) {
                your_date_is_outdated = true;
            }
            else{
                your_date_is_outdated = false;
            }
        }*/
        else
            BirthDay.setHelperText("");

        if (city.getEditText().getText().length() == 0){
            city.setHelperText("חובה להזין את עיר הכלב");
            return false;
        }else if(!city.getEditText().getText().toString().matches("[a-zA-Z ]+"))
        {
            city.setHelperText("ENTER ONLY ALPHABETICAL CHARACTER");
            return false;
        }else
            city.setHelperText("");

        return true;
    }

    private boolean isValidName(String name) {
        String expression = "^[a-zA-Z]*$";
        CharSequence inputStr = name;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }

    private boolean isValidCity(String city) {
        String expression = "^[a-zA-Z]*$";
        CharSequence inputStr = city;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }

    private boolean isValidtime_out(String time) {
        String expression = "^[1-24]:[0-59]*$";
        CharSequence inputStr = time;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }

    private boolean isValidSex(String s) {
        String expression = "^[M/F]*$";
        CharSequence inputStr = s;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }
    private boolean isValidDate(String d) {
        String expression = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$";
        CharSequence inputStr = d;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }

}
/*
class date {
    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:ms");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

 */