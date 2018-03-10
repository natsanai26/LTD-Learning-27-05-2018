package com.example.windows10.ltd_learning.mFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows10.ltd_learning.MainActivity;
import com.example.windows10.ltd_learning.Profile;
import com.example.windows10.ltd_learning.R;
import com.example.windows10.ltd_learning.Register;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Windows10 on 10/11/2017.
 */

public class ProfileFragment extends Fragment {
    private SharedPreferences sharedPreferences;
    private final String URL_LOGIN = "http://158.108.207.7:8090/elearning/member/login";
    private final String URL_UPDATE_MEMBER = "http://158.108.207.7:8090/elearning/member/update";
    //login
    private EditText username;
    private EditText password;
    private Button loginButton;
    private Button registerButton;
    private TextView loginResult;
    //logon
    private TextView pProfile;
    private TextView pUsername;
    private TextView pName;
    private TextView pSurname;
    private TextView pEmail;
    private static final String MyPREFERENCES = "MyPrefs" ;

    private Button changePasswd;
    private Button logoutButton;
    private Button editProfile;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //username.setVisibility(EditText.GONE);
        sharedPreferences = getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final int idMember = sharedPreferences.getInt("idMember",-1);
        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        View rootView;
        if (idMember==-1)
        {
            rootView = inflater.inflate(R.layout.profile_fragment, container, false);
            bindViewLogin(rootView);

            registerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //mCallback.someEvent(new RegisterFragment());
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_id,new RegisterFragment()).commit();
                }
            });
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (username.getText().toString().equals("")||password.getText().toString().equals(""))
                    {
                        loginResult.setText("**username or password is empty!");
                        return;
                    }
                    String json = POST(URL_LOGIN,username.getText().toString(),password.getText().toString());
                    if(json.contains("false"))
                    {
                        loginResult.setText("login fail!");
                    }
                    else {
                        if(json.equals(""))
                        {
                            loginResult.setText("INTERNET_DISCONNECTED");
                            return;
                        }
                        Gson gson = new Gson();
                        Profile profile = gson.fromJson(json,Profile.class);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("checkLogin",true);
                        editor.putInt("idMember",profile.getIdmember());
                        editor.putString("pProfile",profile.getProfile());
                        editor.putString("pUsername",profile.getUsername());
                        editor.putString("pName",profile.getName());
                        editor.putString("pSurname",profile.getSurname());
                        editor.putString("pEmail",profile.getEmail());
                        editor.commit();
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                   // putExtraName();
                }
            });
        }
        else
        {
            rootView = inflater.inflate(R.layout.profile_logon, container, false);
            bindViewLogon(rootView);
            pProfile.setText(sharedPreferences.getString("pProfile","not found"));
            pUsername.setText(sharedPreferences.getString("pUsername","not found"));
            pName.setText(sharedPreferences.getString("pName","not found"));
            pSurname.setText(sharedPreferences.getString("pSurname","not found"));
            pEmail.setText(sharedPreferences.getString("pEmail","not found"));
            logoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.putBoolean("checkLogin",false);
                    editor.commit();
                    Intent intent = new Intent(getContext(),MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }
            });
            editProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String txt = String.format("{\n" +
                                    "        \"idmember\":%d,\n" +
                                    "        \"name\": \"%s\",\n" +
                                    "        \"surname\": \"%s\",\n" +
                                    "        \"email\": \"%s\",\n" +
                                    "        \"profile\": \"%s\"\n" +
                                    "}",
                            idMember,
                            pName.getText().toString(),
                            pSurname.getText().toString(),
                            pEmail.getText().toString(),
                            pProfile.getText().toString());
                    Gson gson = new Gson();
                    Profile p = gson.fromJson(sendPut(txt,URL_UPDATE_MEMBER),Profile.class);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("pProfile",p.getProfile());
                    editor.putString("pName",p.getName());
                    editor.putString("pSurname",p.getSurname());
                    editor.putString("pEmail",p.getEmail());
                    editor.commit();

                    Toast.makeText(getContext(),"Saved!",Toast.LENGTH_SHORT).show();

                }
            });
            changePasswd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getFragmentManager().beginTransaction().replace(R.id.content_id,new ChangePasswordFragment()).addToBackStack(null).commit();
                }
            });
        }
        return rootView;
    }

    public void bindViewLogin(View view)
    {
        username = (EditText)view.findViewById(R.id.username);
        password = (EditText)view.findViewById(R.id.password);
        loginButton = (Button)view.findViewById(R.id.loginButton);

        registerButton = (Button)view.findViewById(R.id.registerButton);
        loginResult = (TextView)view.findViewById(R.id.loginResult);
    }
    public void bindViewLogon(View view)
    {
        pProfile = (TextView)view.findViewById(R.id.pprofile);
        pUsername = (TextView)view.findViewById(R.id.pusername);
        pName = (TextView)view.findViewById(R.id.pname);
        pSurname = (TextView)view.findViewById(R.id.psurname);
        pEmail = (TextView)view.findViewById(R.id.pemail) ;

        changePasswd = (Button)view.findViewById(R.id.changePasswd);
        logoutButton = (Button)view.findViewById(R.id.logoutButton);
        editProfile = (Button) view.findViewById(R.id.editProfile);
    }
    public String sendPut(String data, String url) {
        int responseCode = -1;
        String s="";
        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpPut request = new HttpPut(url);
            StringEntity params =new StringEntity(data,"UTF-8");
            params.setContentType("application/json");
            request.addHeader("content-type", "application/json");
            request.addHeader("Accept", "*/*");
            request.addHeader("Accept-Encoding", "gzip,deflate,sdch");
            request.addHeader("Accept-Language", "en-US,en;q=0.8");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            responseCode = response.getStatusLine().getStatusCode();
            if (response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 204) {

                BufferedReader br = new BufferedReader(
                        new InputStreamReader((response.getEntity().getContent())));

                String output;

                // System.out.println("Output from Server ...." + response.getStatusLine().getStatusCode() + "\n");
                while ((output = br.readLine()) != null) {
                    s+=output;
                }
                //Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
            }
            else{

            }

        }catch (Exception ex) {

        }

        return s;

    }

    private void putExtraName(){
        int pid = sharedPreferences.getInt("idMember",0);
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.putExtra("user_id",pid);

        SharedPreferences sp = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("checkLogin",true);
        editor.putInt("user_ID",pid);
        editor.commit();

        Log.d("JSON","##IDfromPro"+String.valueOf(pid));
        startActivity(intent);

    }
    private void putLogOut(){
        SharedPreferences sp = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("checkLogin",false);
        editor.commit();

    }
    public static String POST(String url, String username,String password){
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
            jsonObject.accumulate("username", username);
            jsonObject.accumulate("passwd", password);


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
}
