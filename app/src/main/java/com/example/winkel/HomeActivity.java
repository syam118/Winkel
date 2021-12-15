package com.example.winkel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class HomeActivity extends AppCompatActivity {

    //Iinitializing variable
    DrawerLayout drawerLayout;
    //getting storage reference
    private StorageReference mStorageReference;
    SwipeRefreshLayout refreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //refresh screen
        refreshLayout = findViewById(R.id.refreshLayout);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //redirect to contact us
                redirectActivity(HomeActivity.this, NetCheck.class);
                refreshLayout.setRefreshing(false);
            }
        });

        //Assigning variable
        drawerLayout = findViewById(R.id.drawer_layout);
        mStorageReference = FirebaseStorage.getInstance().getReference().child("mainload/mainPage.PNG");

        try {
            final File localFile = File.createTempFile("mainPage","PNG");
            mStorageReference.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(HomeActivity.this,"Picture Retrived",Toast.LENGTH_SHORT).show();
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            ((ImageView)findViewById(R.id.mainImage)).setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(HomeActivity.this,"Error Occured",Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ClickMenu(View view){
        //Opening drawer
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        //opening drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickBack(View view){
        closeDrawer(drawerLayout);
    }

    public void ClickAboutUs(View view){
        //redirect to about winkel
        redirectActivity(this, AboutUs.class);
        //Intent set = new Intent(activity,AboutUs.class);
        //startActivity(new Intent(HomeActivity.this,AboutUs.class));
        /*new Thread(){
            public void run(){
                try {
                    sleep(1000);
                    startActivity(new Intent(HomeActivity.this,AboutUs.class));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();*/
    }

    public void ClickContactUs(View view){
        //redirect to contact us
        redirectActivity(this, ContactUs.class);
    }

    public void ClickLocation(View view){
        //redirect to location to find us
        redirectActivity(this, Location.class);
    }

    public void ClickSearch(View view){
        //redirect to search page
        redirectActivity(this, Search.class);
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        //initialize intent
        Intent intent = new Intent(activity,aClass);
        //setting flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //start activity
        activity.startActivity(intent);
        //finish();

    }

    @Override
    protected void onPause() {
        super.onPause();
        //closing drawer
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer((GravityCompat.START));
        }
    }
}