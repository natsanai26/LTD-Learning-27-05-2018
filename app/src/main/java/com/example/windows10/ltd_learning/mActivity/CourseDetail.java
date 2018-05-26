package com.example.windows10.ltd_learning.mActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.listener.VideoControlsSeekListener;
import com.devbrackets.android.exomedia.ui.widget.VideoView;
import com.example.windows10.ltd_learning.mInterface.ElearningAPI;
import com.example.windows10.ltd_learning.mModel.AddProgress;
import com.example.windows10.ltd_learning.mModel.IsRegis;
import com.example.windows10.ltd_learning.mModel.IsRegis2;
import com.example.windows10.ltd_learning.mInterface.MyAPI;

import com.example.windows10.ltd_learning.mInterface.MySingleton;
import com.example.windows10.ltd_learning.R;
import com.example.windows10.ltd_learning.mModel.UpdateProgress;
import com.example.windows10.ltd_learning.mModel.CourseIdOnce;
import com.example.windows10.ltd_learning.mModel.DummyChildDataItem;
import com.example.windows10.ltd_learning.mModel.DummyParentDataItem;
import com.example.windows10.ltd_learning.mModel.SectionList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Windows10 on 12/5/2017.
 */

public class CourseDetail extends AppCompatActivity implements OnPreparedListener,VideoControlsSeekListener {
    private boolean check_onenroll_notlogin = false;
    private ImageView commentBox,fullscreen,image_teacher;
    private boolean checkPreviewed = true;
    private SharedPreferences sharedPreferences;
    private String previewURLVideo;
    private List<String> btn_name;
    private List<String> sub_section_name;
    boolean visible;
    List<List<SectionList.SubsectionBean>> table_section ;
    LinearLayout dynamic_lay;
    LinearLayout parent;
    Button[] b1;
    TextView[] t1;
    private double ratingCourse;
    protected VideoView videoView;
    private Button btn,btn2;
    private TextView txt1,txt2,txt3,txt4,txt5,txt6;
    private SharedPreferences sp;
    private boolean enrolled;
    private List<SectionList> sectionLists;
    private String courseName;
    private Button ratingButton;
    private CourseIdOnce courseInCourseDetail;
    private static final String MyPREFERENCES = "MyPrefs" ;
    private Button enroll;
    private TextView tx_name,tx_detail,testIdUser,testIdCourse,tvDetailCourse;
    private int idUser,idCourse;
    private static final String URL_getURLPicture = "http://158.108.207.7:8080/api/stream?content=";
    private static String URL_unEnroll = "http://158.108.207.7:8090/elearning/course/unenroll";
    private static String URL_courseID = "http://158.108.207.7:8090/elearning/course?courseId=";
    private static String URL_isRegis = "http://158.108.207.7:8090/elearning/course/isRegis";
    private static String URL_addRegis ="http://158.108.207.7:8090/elearning/course/addRegis";
    private double ratingCurrent;
    private double courseVoter;
    private int progress_section_id;
    private TextView voter,teacherName;
    private RatingBar ratingBar;
    private ExpandableListView listView;
    private List<String> listDataHeader;
    private HashMap<String,List<SectionList.SubsectionBean>> listHash;
    private ProgressBar progressBar;
    private int progressValue,checkId2;
    private final int FULL_SCREEN_REQUEST_CODE = 1;
    private ScrollView scrollView;
    private RecyclerView mRecyclerView;
    CourseDetail.RecyclerDataAdapter recyclerDataAdapter;

    View.OnClickListener ocl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_detail_layout);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        sharedPreferences = this.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//        setUpTopFragment();
        setupVideoView();
        //----------------- PDF TEST-----------------------
        enroll = (Button) findViewById(R.id.button_enroll);
//        testIdUser = (TextView) findViewById(R.id.testIdUser);
//        testIdCourse = (TextView) findViewById(R.id.testIdCourse);
        tx_name = (TextView) findViewById(R.id.course_name);
//        tx_detail = (TextView)findViewById(R.id.detail);
        sp = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        image_teacher = (ImageView) findViewById(R.id.image_teacher);
        teacherName = (TextView) findViewById(R.id.nameTxt_teacher);
        tvDetailCourse = (TextView)findViewById(R.id.tv_detailCourse);
        tvDetailCourse.setMovementMethod(new ScrollingMovementMethod());
        scrollView = (ScrollView) findViewById(R.id.container_detail);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                tvDetailCourse.getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });
        tvDetailCourse.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                tvDetailCourse.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        idUser = sp.getInt("idMember",-1);
        progressValue = sp.getInt("progressNow",100);
        Intent intent  = getIntent();
        idCourse = intent.getIntExtra("course_id",-2);
        ratingCourse = intent.getDoubleExtra("course_rating",-1);
        courseVoter = intent.getDoubleExtra("course_voter",-1);
        progress_section_id = intent.getIntExtra("progress_section_id",-1);
        Log.d("Section","check section progress "+progress_section_id);
//        testIdUser.setText("UserID : "+idUser);
//        testIdCourse.setText("CourseID :"+idCourse);
        commentBox = (ImageView) findViewById(R.id.image_comment);
        progressBar = (ProgressBar) findViewById(R.id.progressInDetail);
        fullscreen = (ImageView) findViewById(R.id.full_screen);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));
        }

        progressBar.setProgress(0);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        voter = (TextView) findViewById(R.id.voter);
        if((int)courseVoter <= 1){
            voter.setText( (int)courseVoter+" vote");
        }
        else {
            voter.setText( (int)courseVoter+" votes");
        }


        //ratingBar.setRating((float) ratingCourse);
        courseName = getIntent().getStringExtra("course_name");
        tx_name.setText(getIntent().getStringExtra("course_name"));
        ratingButton = (Button)findViewById(R.id.rateButton) ;
        ratingButton.setEnabled(false);

        fullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.pause();
                Intent intent = new Intent(CourseDetail.this,FullScreenVideoActivity.class);
                intent.putExtra("video_path",sharedPreferences.getString("myVideo",""));
                intent.putExtra("video_seek",videoView.getCurrentPosition());
                startActivityForResult(intent,FULL_SCREEN_REQUEST_CODE);
            }
        });
        commentBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseDetail.this,CommentActivity.class);
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("id_course_comment",idCourse);
                editor.putInt("id_comment",idUser);
                intent.putExtra("course_id",idCourse);
                intent.putExtra("member_id",idUser);
                intent.putExtra("checkEnroll", sharedPreferences.getBoolean("checkEnroll",false));
                editor.commit();
                startActivity(intent);
            }
        });

        ratingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(CourseDetail.this,AddRatingActivity.class);
                intent1.putExtra("course_id",idCourse);
                intent1.putExtra("member_id",idUser);
                startActivity(intent1);
            }
        });
        enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(idUser == -1){
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("check_for_enroll",true);
                    Toast.makeText(CourseDetail.this,"Please Register or Login First", Toast.LENGTH_LONG).show();
                    check_onenroll_notlogin = true;
                    finish();
                }else if(!enrolled){
                    String dataJson = getRegister();
                    ratingButton.setEnabled(true);
                    enroll.setText("Unenroll");
                    final ElearningAPI elearningAPI = MyAPI.getAPI();
                    finish();
                    startActivity(getIntent());
                }else if(enrolled){
                    Toast.makeText(CourseDetail.this,"UnEnroll Doing!!", Toast.LENGTH_LONG).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(CourseDetail.this);

                    builder.setMessage("Are you sure for uneneoll").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            enroll.setText("enroll");
                            enroll.setTextColor(Color.parseColor("#000000"));
                            ratingButton.setEnabled(false);
                            unEnrolled();
                        }
                    }).setNegativeButton("Cancel",null);
                    AlertDialog alert = builder.create();
                    alert.show();
                }


            }
        });

        getDetailCourseById(idCourse);
    }
    public void reLoad(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }


    @Override
    protected void onPause() {
        Log.d("JSON","this pause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        videoView.pause();
        super.onStop();
    }

    @Override
    protected void onStart() {


        super.onStart();
    }

    @Override
    protected void onResume() {
        if(idUser != -1){
            getStatusEnroll();
        }
        APIonResume();
        super.onResume();
    }
    public void APIonResume(){
        Log.d("IsRegis2","OnResume++");
        final ElearningAPI elearningAPI = MyAPI.getAPI();
        try {
            JSONObject jsonObject = new JSONObject(String.format("{\"memberId\":%d,\"courseId\":%d}",idUser,idCourse));
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(jsonObject).toString());
            Call<ResponseBody> response = elearningAPI.isRegis(body);
            response.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> rawResponse) {
                    try {
                        String json = rawResponse.body().string();
                        Gson gson = new Gson();
                        IsRegis2 isRegis2 = gson.fromJson(json,IsRegis2.class);
                        double rating = isRegis2.getCourse().getRating();
                        Log.d("rating",""+rating);
                        if(isRegis2.getCourse().getProgress() != null){
                            Log.d("IsRegis2","onResponse "+isRegis2.getCourse().getProgress().getSectionId());
                            boolean checkEnroll = sharedPreferences.getBoolean("checkEnroll",false);
                            Log.d("Booo","tusEntroll "+checkEnroll);
                            checkId2 = isRegis2.getCourse().getProgress().getSectionId();
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("id_section_progress",checkId2);
                            editor.putInt("progressNow",isRegis2.getCourse().getProgress().getPercent());
                            editor.commit();
                            if(!checkEnroll){
                                progressBar.setProgress(0);
                            }else
                                progressBar.setProgress(isRegis2.getCourse().getProgress().getPercent());

                        }
                        if(isRegis2.getCourse().getTeacher().getPhotoUrl()!=null){
                            if (isRegis2.getCourse().getTeacher().getPhotoUrl().contains("https://"))
                                Picasso.with(CourseDetail.this).load(isRegis2.getCourse().getTeacher().getPhotoUrl()).into(image_teacher);
                            else
                                Picasso.with(CourseDetail.this).load(MyAPI.BASE_URL_ELEARNNING+"elearning/"+isRegis2.getCourse().getTeacher().getPhotoUrl()).into(image_teacher);
                        }
                        teacherName.setText(isRegis2.getCourse().getTeacher().getName()+" "+isRegis2.getCourse().getTeacher().getSurname());
                        tvDetailCourse.setText(isRegis2.getCourse().getDetail());
                        ratingBar.setRating((float) rating);
                        if((int)isRegis2.getCourse().getVoter() <= 1){
                            voter.setText( (float)rating+" from "+(int)isRegis2.getCourse().getVoter()+" vote");
                        }
                        else {
                            voter.setText( (float)rating+" from "+(int)isRegis2.getCourse().getVoter()+" votes");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("JSON","this resume");
    }

    private void setupVideoView() {
        // Make sure to use the correct VideoView import

        videoView = (VideoView)findViewById(R.id.video_view);
        videoView.setOnPreparedListener(this);
        String path = sharedPreferences.getString("myVideo","");
        String type = sharedPreferences.getString("type","");
        checkPreviewed = sharedPreferences.getBoolean("checkVideo",true);
        Log.d("JSON","On set Up video View ===> "+path+" bool "+checkPreviewed+" Type "+type);
        //For now we just picked an arbitrary item to play
        if(type.equals("VIDEO")){
            videoView.setVideoURI(Uri.parse(MyAPI.BASE_URL_COURSE_API+path));
        }else if(type.equals("DOCUMENT")){
            Log.d("JSON","This is doc");
            setPdfView(MyAPI.BASE_URL_COURSE_API+path);
        }else if(type.equals("PICTURE")){
            Intent intent = new Intent(CourseDetail.this,ImageDialog.class);
            intent.putExtra("url_pic",MyAPI.BASE_URL_COURSE_API+path);
            startActivity(intent);
        }
    }

    private  void setPdfView(String url){
        Intent intent = new Intent(this,PdfActivityActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);
    }

    @Override
    public void onPrepared() {
        //Starts the video playback as soon as it is ready
        checkPreviewed = sharedPreferences.getBoolean("checkVideo",true);
        Log.d("JSON","fromOnPrepared +-+-> "+checkPreviewed);
        if(checkPreviewed){
            videoView.pause();
        }else{
            videoView.start();
        }
    }

    public void getDetailCourseById(int course_id){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_courseID+String.valueOf(course_id), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("##JSON","fromCourseDetail +-+-> "+response);
                GsonBuilder builder = new GsonBuilder();
                Gson gson = new Gson();
                CourseIdOnce data = gson.fromJson(response,CourseIdOnce.class);
//                Course.StatusBean response_data = data.getStatus();
                setCourseFromID(data);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JSON", "Error JSON");
                    }
                });
        MySingleton.getInstance(this).addToReauestQue(stringRequest);

    }

    @Override
    protected void onDestroy() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean checkBool ;
        checkBool = sharedPreferences.getBoolean("destroyInCourseDetail",true);
        editor.putBoolean("checkVideo",true);
        if(!checkBool){
            editor.putBoolean("destroyInCourseDetail",true);
        }
        editor.commit();
        if(idUser == -1&&check_onenroll_notlogin){
            MainActivity.bottomNavigationItem.setCurrentItem(2);
        }
        Log.d("JSON","Check Bool "+checkBool);
        super.onDestroy();
    }

    private void setCourseFromID(CourseIdOnce course){
        courseInCourseDetail = course;
        List<SectionList> sectionLists = getSectionListFromID();
        checkPreviewed = sharedPreferences.getBoolean("checkVideo",true);
        if(checkPreviewed){
            if(sectionLists.size() != 0){
//            previewURLVideo = sectionLists.get(1).getSubsection().get(0).getContent();
                previewURLVideo = sharedPreferences.getString("url_video_preview","W-p_0Bxmvt-on0_izXbH-g");
            Log.d("##Video","IN Course From ID === "+courseInCourseDetail+" AND +++>"+previewURLVideo);
            getURLVideo(previewURLVideo);
            }
        }else{
            getURLVideo(sharedPreferences.getString("myVideo",null));
        }
        if(getSectionListFromID().size() != 0){
//            showLesson();
            setUpSectionLayout();

        }
    }
    //----------------------------Section View-----------------------------------------------------
    private void setUpSectionLayout(){
        List<SectionList> sectionLists = getSectionListFromID();
//        Log.d("DuDu","Check CourseDetail ===> "+sectionLists.size());
//        Log.d("DuDu","Check CourseDetail ===> "+sectionLists.get(1).getSubsection().get(0).getName());
//        Log.d("DuDu","Check CourseDetail ===> "+sectionLists.get(1).getSubsection().get(0).getContent());

        sub_section_name = new ArrayList<String>();
        btn_name = new ArrayList<String>();
        table_section = new ArrayList<List<SectionList.SubsectionBean>>();

        Log.d("JSON","Check Size Section ===> "+sectionLists.size());
        int k = 0,sizesub;
        sizesub = sectionLists.get(1).getSubsection().size();
        for(int i = 2; i<sectionLists.size() ;i++){
            btn_name.add(sectionLists.get(i).getName());
            sizesub = sectionLists.get(i).getSubsection().size();
            table_section.add(new ArrayList<SectionList.SubsectionBean>());
        }
        Log.d("table_sec",table_section.size()+"");
        Log.d("JSON","Check Size Sub ===> "+sectionLists.get(1).getSubsection().size());
        String previewVideoContent =  sectionLists.get(1).getContent();
        String previewVideoContentType = sectionLists.get(1).getContentType();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("url_video_preview",previewVideoContent);
        editor.commit();
        getURLPath(previewVideoContent);
        for (int j=2;j<sectionLists.size();j++) {
            //sub_section_name = new ArrayList<String>();
            for (int i = 0; i < sectionLists.get(j).getSubsection().size(); i++) {
                //Log.d("JSON", "Check CourseDetail ===> " + sectionLists.get(j-1).getSubsection().get(i).getContent());
                //sub_section_name.add(sectionLists.get(j).getSubsection().get(i).getContent());
                //table_section.get(j)
                //if(sectionLists.get(j).getSubsection().get(i).getContentType().equals("TEXT"))
                table_section.get(j-2).add(sectionLists.get(j).getSubsection().get(i));

            }



        }

        listHash = new HashMap<>();
        for (int i=0;i<btn_name.size();i++)
        {
            Log.d("DuDu","-->"+btn_name.get(i));
            listHash.put(btn_name.get(i),table_section.get(i));
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerDataAdapter = new CourseDetail.RecyclerDataAdapter(getDummyDataToPass(listHash));



        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(recyclerDataAdapter);
        mRecyclerView.setHasFixedSize(true);

        checkId2 = sharedPreferences.getInt("id_section_progress",-1);
        Log.d("IsRegis2","OUT "+checkId2);
        final int position = findCurrentSection(checkId2);
        if (position!=-1){
            expandViewAtProsition(position-2);
        }
    }
    private ArrayList<DummyParentDataItem> getDummyDataToPass(HashMap<String,List<SectionList.SubsectionBean>> listHash) {
        ArrayList<DummyParentDataItem> dummyDataItems = new ArrayList<>();
        ArrayList<DummyChildDataItem> dummyChildDataItems;
        DummyParentDataItem dummyParentDataItem;
        DummyChildDataItem dummyChildDataItem;

        for (String header:btn_name)
        {
            dummyParentDataItem = new DummyParentDataItem();
            dummyParentDataItem.setParentName(header);

            dummyChildDataItems = new ArrayList<>();

            for (SectionList.SubsectionBean child:listHash.get(header))
            {
                dummyChildDataItem = new DummyChildDataItem();
                dummyChildDataItem.setChildName(child.getName());
                dummyChildDataItem.setChildContent(child.getContentType()+" "+ child.getContent()+" "+child.getId());
                dummyChildDataItem.setChildContentType(child.getContentType());
                dummyChildDataItem.setChildId(child.getId());
                dummyChildDataItems.add(dummyChildDataItem);
            }

            dummyParentDataItem.setChildDataItems(dummyChildDataItems);
            dummyDataItems.add(dummyParentDataItem);

        }
        return dummyDataItems;
    }

    @Override
    public boolean onSeekStarted() {
        return false;
    }

    @Override
    public boolean onSeekEnded(long seekTime) {
        return false;
    }

    public class RecyclerDataAdapter extends RecyclerView.Adapter<CourseDetail.RecyclerDataAdapter.MyViewHolder> {
        private ArrayList<DummyParentDataItem> dummyParentDataItems;
        private RecyclerDataAdapter recyclerDataAdapter1;

        private ArrayList<TextView> expandHeader = new ArrayList<TextView>();
        boolean chk=true;
        RecyclerDataAdapter(ArrayList<DummyParentDataItem> dummyParentDataItems) {
            this.dummyParentDataItems = dummyParentDataItems;




        }
        public void expand(int parentPosition)
        {
//            expandHeader.get(parentPosition).performClick();
            Toast.makeText(CourseDetail.this,"size:"+expandHeader.size(),Toast.LENGTH_SHORT).show();
        }
        @Override
        public CourseDetail.RecyclerDataAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parent_child_listing, parent, false);
            return new CourseDetail.RecyclerDataAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(CourseDetail.RecyclerDataAdapter.MyViewHolder holder, int position) {
            DummyParentDataItem dummyParentDataItem = dummyParentDataItems.get(position);
            holder.textView_parentName.setText(dummyParentDataItem.getParentName());
            checkId2 = sharedPreferences.getInt("id_section_progress",-1);
            //
            //expandHeader.add(holder.textView_parentName);
            int noOfChildTextViews = holder.linearLayout_childItems.getChildCount();
            int noOfChild = dummyParentDataItem.getChildDataItems().size();
            if (noOfChild < noOfChildTextViews) {
                for (int index = noOfChild; index < noOfChildTextViews; index++) {
//                    TextView currentTextView = (TextView) holder.linearLayout_childItems.getChildAt(index);
//                    currentTextView.setVisibility(View.GONE);
                    LinearLayout linearLayout  = (LinearLayout) holder.linearLayout_childItems.getChildAt(index);
                    TextView textView = (TextView) linearLayout.getChildAt(1);
                    linearLayout.setVisibility(View.GONE);
                }
            }
            for (int textViewIndex = 0; textViewIndex < noOfChild; textViewIndex++) {
//                TextView currentTextView = (TextView) holder.linearLayout_childItems.getChildAt(textViewIndex);
//                currentTextView.setText(dummyParentDataItem.getChildDataItems().get(textViewIndex).getChildName());
//                currentTextView.setHint(dummyParentDataItem.getChildDataItems().get(textViewIndex).getChildContent());
                LinearLayout linearLayout  = (LinearLayout) holder.linearLayout_childItems.getChildAt(textViewIndex);
                ImageView imageView = (ImageView) linearLayout.getChildAt(0);
                switch (dummyParentDataItem.getChildDataItems().get(textViewIndex).getChildContentType())
                {
                    case "VIDEO":
                        imageView.setImageResource(R.drawable.ic_action_playback_red);
                        break;
                    case "PICTURE":
                        imageView.setImageResource(R.drawable.ic_action_picture_yel);
                        break;
                    case "DOCUMENT":
                        imageView.setImageResource(R.drawable.ic_action_document_blu);
                        break;

                }
                TextView textView = (TextView) linearLayout.getChildAt(1);
                textView.setText(dummyParentDataItem.getChildDataItems().get(textViewIndex).getChildName());
                textView.setHint(dummyParentDataItem.getChildDataItems().get(textViewIndex).getChildContent());
                if (dummyParentDataItem.getChildDataItems().get(textViewIndex).getChildId()!=checkId2 && chk){
                    textView.setTextColor(Color.parseColor("#918c8c"));
                    switch (dummyParentDataItem.getChildDataItems().get(textViewIndex).getChildContentType())
                    {
                        case "VIDEO":
                            imageView.setImageResource(R.drawable.ic_action_playback_play_g);
                            break;
                        case "PICTURE":
                            imageView.setImageResource(R.drawable.ic_picture_g);
                            break;
                        case "DOCUMENT":
                            imageView.setImageResource(R.drawable.ic_action_document_g);
                            break;

                    }
                }
                if(dummyParentDataItem.getChildDataItems().get(textViewIndex).getChildId()==checkId2 ){
                    chk = false;
                }

            }
        }

        @Override
        public int getItemCount() {
            return dummyParentDataItems.size();
        }


        class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private int checkId;
            private Context context;
            public TextView textView_parentName;
            private LinearLayout linearLayout_childItems;

            MyViewHolder(View itemView) {
                super(itemView);
                context = itemView.getContext();
                textView_parentName = itemView.findViewById(R.id.tv_parentName);
                linearLayout_childItems = itemView.findViewById(R.id.ll_child_items);
                linearLayout_childItems.setVisibility(View.GONE);
                sharedPreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                checkId = sharedPreferences.getInt("id_section_progress",-1);
                Log.d("JSON","OnViewHold----->"+checkId);
                int intMaxNoOfChild = 0;
                for (int index = 0; index < dummyParentDataItems.size(); index++) {
                    int intMaxSizeTemp = dummyParentDataItems.get(index).getChildDataItems().size();
                    if (intMaxSizeTemp > intMaxNoOfChild) intMaxNoOfChild = intMaxSizeTemp;
                }
                for (int indexView = 0; indexView < intMaxNoOfChild; indexView++) {
                    LinearLayout linearLayout = new LinearLayout(context);
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    ImageView imageView = new ImageView(context);
                    imageView.setImageResource(R.drawable.ic_action_video);
                    linearLayout.addView(imageView);

                    TextView textView = new TextView(context);
                    textView.setText(indexView+"");
                    textView.setTextColor(Color.parseColor("#000000"));
                    textView.setTextSize(15);
                    textView.setId(indexView);
                    textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    textView.setPadding(0, 10, 0, 10);
                    //textView.setGravity(Gravity.CENTER);
                    //textView.setBackground(ContextCompat.getDrawable(context, R.drawable.background_sub_module_text));

                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                    textView.setOnClickListener(this);

                    linearLayout.addView(textView);

                    //linearLayout_childItems.addView(textView, layoutParams);
                    linearLayout_childItems.addView(linearLayout,layoutParams);
                }
                textView_parentName.setOnClickListener(this);

                expandHeader.add(textView_parentName);

            }




            @Override
            public void onClick(View view) {
                boolean checkEnroll = sharedPreferences.getBoolean("checkEnroll",false);
                Log.d("OnClickSub","CheckEnroll "+checkEnroll);


                if (view.getId() == R.id.tv_parentName) {
                    if (linearLayout_childItems.getVisibility() == View.VISIBLE) {
                        linearLayout_childItems.setVisibility(View.GONE);
                    } else {
                        linearLayout_childItems.setVisibility(View.VISIBLE);
                    }

                }
                else {
                    if(checkEnroll) {
                        TextView textViewClicked = (TextView) view;
                        textViewClicked.setBackgroundColor(Color.parseColor("#918c8c"));
//                        Toast.makeText(context, "This is " + textViewClicked.getHint(), Toast.LENGTH_SHORT).show();

                        String contentSection = (String) textViewClicked.getHint();
                        Log.d("SubJson", "Check content " + contentSection);
                        String[] parts = contentSection.split(" ");
                        String type = parts[0];
                        String content = parts[1];
                        String id = parts[2];
                        String MyPREFERENCES = "MyPrefs";
                        int idInt;
                        idInt = Integer.parseInt(id);
//                    String type = listHash.get(listDataHeader.get(parent)).get(child).getContentType();
                        Log.d("SubJson", "OnClick Subsection " + type + "---" + content + "----" + idInt + "----->" + checkId);
                        if (checkId == -1) {
                            Log.d("SubJson", "OnAddProgress " + idUser + "  " + idCourse + "  " + idInt);
                            AddProgress(idInt);
                        } else {
                            UpdateProgress(idInt);
                        }
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("nowID", idInt);
                        editor.putString("type", type);
                        editor.putBoolean("checkVideo", false);
                        editor.commit();
                        getURLVideo(content);
                    }
                    else if(!checkEnroll){
                        Toast.makeText(context,"Please Enroll First..", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        }
    }


    //---------------------------------- Get Video View-----------------------------------

    public void getURLVideo(final String content){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_getURLPicture + content, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("JSON","url --> "+content+" response "+response);
                setURLOnPreferences(response);
            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JSON", "Error JSON");
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("token", "key999");
                return params;
            }
        };
        MySingleton.getInstance(this).addToReauestQue(stringRequest);
    }

    private List<SectionList> getSectionListFromID(){
        sectionLists = courseInCourseDetail.getCourse().getSectionList();
        return sectionLists;
    }

    private void setVideoPreview(String url){
        videoView.setVideoURI(Uri.parse(MyAPI.BASE_URL_COURSE_API+url));
        videoView.pause();
    }
    public void getURLPath(final String content){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_getURLPicture + content, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("JSON","url --> "+content+" response "+response);
                setVideoPreview(response);
            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JSON", "Error JSON");
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("token", "key999");
                return params;
            }
        };
        MySingleton.getInstance(this).addToReauestQue(stringRequest);
    }


    public void setURLOnPreferences(String url){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("myVideo",url);
        editor.commit();
        setupVideoView();
    }



    private void getStatusEnroll(){
        String json = POST2(URL_isRegis,idUser,idCourse);
        Log.d("JSON", "####From getStatus"+json);
        Gson gson = new Gson();
        IsRegis isRegis = gson.fromJson(json,IsRegis .class);
        Log.d("JSON", "####From getStatus2"+isRegis.isIsRegis());
        enrolled = isRegis.isIsRegis();
        SharedPreferences.Editor editor = sp.edit();
        Log.d("JSON", "####From getStatus3"+enrolled);
        editor.putBoolean("checkEnroll",enrolled);
        editor.commit();

        changButtonEnroll();
    }

    private void changButtonEnroll(){
        if(enrolled){
            enroll.setText("Unenroll");
            enroll.setBackgroundColor(Color.parseColor("#ff305a"));
            ratingButton.setEnabled(true);
            progressBar.setProgress(0);
//            showSectionCourse();
        }
    }

    private void unEnrolled(){
        String json = POST(URL_unEnroll,idCourse,idUser);
        finish();
        startActivity(getIntent());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id_section_progress",-1);
        editor.commit();
        Log.d("JSON", "####From unEnrolled");

    }
    private String getRegister(){
        String json = POST(URL_addRegis,idCourse,idUser);
        Toast.makeText(CourseDetail.this,"Enroll Doing!!", Toast.LENGTH_LONG).show();
        Log.d("JSON", "####From getRegister");
        return json;

    }

    public static String POST(String url,int courseId,int memberId){
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            String json = "";

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("courseId", courseId);
            jsonObject.accumulate("memberId", memberId);


            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);

            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }
    public static String POST2(String url,int memberId,int courseId){
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            String json = "";

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("memberId", memberId);
            jsonObject.accumulate("courseId", courseId);


            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);

            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public void AddProgress(final int sectionId) {
        final ElearningAPI elearningAPI = MyAPI.getAPI();
        try {
            JSONObject jsonObject = new JSONObject(String.format("{\"memberId\":%d,\"courseId\":%d,\"sectionId\":%d}",idUser,idCourse,sectionId));
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(jsonObject).toString());
            Call<ResponseBody> response = elearningAPI.addProgress(body);
            response.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> rawResponse) {
                    try {
                        String json = rawResponse.body().string();
                        Log.d("SubJson","On Rest Add "+json);
                        Gson gson = new Gson();
                        AddProgress addProgress = gson.fromJson(json,AddProgress.class);
                        UpdateProgressBar(addProgress.getProgress().getPercent());

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("id_section_progress",addProgress.getProgress().getSectionId());
                        editor.commit();
                        RecyclerDataAdapter recyclerDataAdapter2 = new RecyclerDataAdapter(getDummyDataToPass(listHash));
                        mRecyclerView.setAdapter(recyclerDataAdapter2);


                        final int position = findCurrentSection(sectionId);

                        if (position!=-1){
                            expandViewAtProsition(position-2);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    int position;
    public void UpdateProgress(final int sectionId) {
        final ElearningAPI elearningAPI = MyAPI.getAPI();
        try {
            JSONObject jsonObject = new JSONObject(String.format("{\"memberId\":%d,\"courseId\":%d,\"sectionId\":%d}",idUser,idCourse,sectionId));
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(jsonObject).toString());
            Call<ResponseBody> response = elearningAPI.updateProgress(body);
            response.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> rawResponse) {
                    try {
                        String json = rawResponse.body().string();
                        Log.d("SubJson","On Rest Update"+json);
                        Gson gson = new Gson();
                        UpdateProgress updateProgress = gson.fromJson(json,UpdateProgress.class);
                        UpdateProgressBar(updateProgress.getProgress().getPercent());

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("id_section_progress",updateProgress.getProgress().getSectionId());
                        editor.commit();
                        RecyclerDataAdapter recyclerDataAdapter2 = new RecyclerDataAdapter(getDummyDataToPass(listHash));
                        //recyclerDataAdapter2.setOnClickListenner(ocl);
                        mRecyclerView.setAdapter(recyclerDataAdapter2);

                        //recyclerDataAdapter2.expand(1);
                        //mRecyclerView.findViewHolderForAdapterPosition(1).itemView.findViewById(R.id.tv_parentName).performClick();

                        final int position = findCurrentSection(sectionId);

                        if (position!=-1){
                            expandViewAtProsition(position-2);
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void UpdateProgressBar(int percent){
        progressBar.setProgress(percent);
    }

    public void expandViewAtProsition(final int position)
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.tv_parentName).performClick();
            }
        },1);
    }

    public int findCurrentSection(int subSectionId)
    {
        int position = 0;

        for (SectionList sectionList:getSectionListFromID())
        {
            for (SectionList.SubsectionBean subsectionBean1:sectionList.getSubsection())
            {
                if (subsectionBean1.getId()==subSectionId)
                {
                    return position;
                }
            }

            position++;
        }
        return -1;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("resultCode",resultCode+"");
        if (resultCode==RESULT_OK)
        {
            if (requestCode==FULL_SCREEN_REQUEST_CODE)
            {
                videoView.seekTo(data.getLongExtra("video_seek",0));
                Log.d("video_seek : ",data.getLongExtra("video_seek",0)+"");
            }
        }

        super.onActivityResult(requestCode, resultCode, data);

    }
}




