package com.example.hackathon;

import static java.lang.Thread.sleep;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.io.IOException;

public class upload extends AppCompatActivity {

    private ImageView imgView;

    private Button select, predict;
    private TextView tv;
    private Bitmap img;
    private ProgressBar spinner;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);


        imgView = (ImageView) findViewById(R.id.imageView);
        tv = (TextView) findViewById(R.id.textView);
        select = (Button) findViewById(R.id.button);
        predict = (Button) findViewById(R.id.button2);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);



        select.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 100);
                spinner.setVisibility(View.VISIBLE);
            }
        }));

        predict.setOnClickListener((new View.OnClickListener(){


            @Override
            public void onClick(View v) {

                String uri = "@drawable/bars";
                predict.setVisibility(View.GONE);

                try {

                    sleep(2000);
                    spinner.setVisibility(View.GONE);
                    int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                    imgView= (ImageView)findViewById(R.id.imageView);
                    Drawable res = getResources().getDrawable(imageResource);
                    imgView.setImageDrawable(res);

                } catch (Resources.NotFoundException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }));



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == 100)
        {
            imgView.setImageURI(data.getData());

            Uri uri = data.getData();
            try {
                img = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

}