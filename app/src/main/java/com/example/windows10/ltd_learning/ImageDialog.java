package com.example.windows10.ltd_learning;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Windows10 on 5/8/2018.
 */

public class ImageDialog extends Activity{
    private String url;
    private ImageView mDialog;
    private SharedPreferences sharedPreferences;
    private static final String MyPREFERENCES = "MyPrefs" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_dialog_layout);
        sharedPreferences = this.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        mDialog = (ImageView)findViewById(R.id.dialog_image);
        mDialog.setClickable(true);
        Intent intent = getIntent();
        url = intent.getStringExtra("url_pic");
        Picasso.with(this).load(url).into(mDialog);
        mDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("type","");
        editor.commit();
        super.onDestroy();
    }
}
