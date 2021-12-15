package com.example.winkel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DisplayItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_item);
        getIncomingIntent();
    }

    private void getIncomingIntent(){
        if (getIntent().hasExtra("imgname")&&getIntent().hasExtra("imgavb")&&getIntent().hasExtra("img")&&getIntent().hasExtra("root")){
            String imgname = getIntent().getStringExtra("imgname");
            String imgavb = getIntent().getStringExtra("imgavb");
            String img = getIntent().getStringExtra("img");
            String root = getIntent().getStringExtra("root");
            setContent(imgname,imgavb,img,root);
        }
    }
    private void setContent(String imgname, String imgavb,String img,String root){
        TextView name = findViewById(R.id.itname);
        name.setText(imgname);
        TextView avb = findViewById(R.id.itavb);
        avb.setText(imgavb);
        ImageView itemimage = findViewById(R.id.img2);
        Picasso.with(this).load(img).into(itemimage);
        ImageView rootimage = findViewById(R.id.img1);
        Picasso.with(this).load(root).into(rootimage);
    }
   /* @Override
    protected void onPause() {
        super.onPause();
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }*/
    public void ClickCompass(View view){

        //redirect to compass
        //redirectActivity(this, SetCompass.class);

        startActivity(new Intent(DisplayItem.this, SetCompass.class));
    }

   /* public static void redirectActivity(Activity activity, Class aClass) {
        //initialize intent
        Intent intent = new Intent(activity,aClass);
        //setting flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //start activity
        activity.startActivity(intent);
        //finish();

    }*/
}