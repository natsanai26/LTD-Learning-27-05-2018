package com.example.windows10.ltd_learning.mActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;


import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.windows10.ltd_learning.mInterface.MyAPI;
import com.example.windows10.ltd_learning.R;
import com.example.windows10.ltd_learning.mInterface.ElearningAPI;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;



import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadPictureActivity extends AppCompatActivity {

    private Button galleryButton;
    private Button cameraButton;
    private TextView textView;

    private Button okButton;
    private TextView fileSize;
    private TextView sizeWarning;

    private int requestCode=-1;

    private String picPath;
    private String picSize;

    private SharedPreferences sharedPreferences;
    private static final String MyPREFERENCES = "MyPrefs" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_picture);
        sharedPreferences = this.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        init();

        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");

                startActivityForResult(photoPickerIntent, 1);
                sizeWarning.setVisibility(View.GONE);
            }
        });
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    startActivityForResult(takePictureIntent, 2);

            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final File file = new File(textView.getText().toString());

                if (file.length()<=1048576) {
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

                    MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

                    ElearningAPI elearningAPI = MyAPI.getAPI();

                    int memberId = sharedPreferences.getInt("idMember",-1);
                    Call<ResponseBody> responseBody = elearningAPI.uploadPicture(body, memberId);

                    responseBody.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {
                                Toast.makeText(UploadPictureActivity.this, response.body().string(), Toast.LENGTH_LONG).show();
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("photoUrl",file.getPath());
                                editor.putBoolean("picUpdate",true);
                                editor.commit();
                                finish();

                            } catch (IOException e) {
                                Log.d("error:", e.getMessage());
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.d("fail:", t.getMessage());
                        }
                    });

                }
                else
                {
                    sizeWarning.setVisibility(View.VISIBLE);
                }


            }
        });






    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==Activity.RESULT_OK)
        {
            this.requestCode = requestCode;
            if (requestCode==1)
            {
                Uri selectedPicture = data.getData();
                String filePath = getRealPathFromURI(selectedPicture);

                setViewVisibility();

                textView.setText(filePath);

                long size = new File(filePath).length();
                String s="";
                if (size<1024)
                    s=String.valueOf(size)+" B.";
                else
                if (size<1048576)
                    s=String.format("%.3f kB.",size/1024.0);
                else
                    s=String.format("%.3f MB.",size/1048576.0);

                fileSize.setText(s);
                picPath = filePath;
                picSize = s;

            }
            if (requestCode==2)
            {

                    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    thumbnail.compress(Bitmap.CompressFormat.PNG, 100, bytes);

                    File destination = new File(Environment.getExternalStorageDirectory(),"temp.png");

                    FileOutputStream fo;
                    try {
                        fo = new FileOutputStream(destination);
                        fo.write(bytes.toByteArray());
                        fo.close();
                    } catch (IOException e) {
                        Log.d("error:",e.getMessage());
                    }

                    setViewVisibility();

                    textView.setText(destination.getPath());

                    long size = destination.length();
                    String s="";
                    if (size<1024)
                        s=String.valueOf(size)+" B.";
                    else
                    if (size<1048576)
                        s=String.format("%.3f kB.",size/1024.0);
                    else
                        s=String.format("%.3f MB.",size/1048576.0);

                    fileSize.setText(s);

                    picPath = destination.getPath();
                    picSize = s;




            }
        }

    }
    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    public void init()
    {
        galleryButton = (Button)findViewById(R.id.galleryButton);
        cameraButton = (Button) findViewById(R.id.cameraButton);
        textView = (TextView)findViewById(R.id.textView);
        okButton = (Button)findViewById(R.id.okButton);
        fileSize = (TextView)findViewById(R.id.fileSize);
        sizeWarning = (TextView) findViewById(R.id.sizeWarning);
    }
    public void setViewVisibility()
    {
        textView.setVisibility(View.VISIBLE);
        fileSize.setVisibility(View.VISIBLE);
        okButton.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt("requestCode",requestCode);
        outState.putString("picPath",picPath);
        outState.putString("picSize",picSize);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.getInt("requestCode",-1)!=-1) {
            setViewVisibility();
            requestCode = savedInstanceState.getInt("requestCode",-1);
            picPath = savedInstanceState.getString("picPath","not found");
            picSize = savedInstanceState.getString("picSize","not found");
            textView.setText(picPath);
            fileSize.setText(picSize);
        }
    }


}
