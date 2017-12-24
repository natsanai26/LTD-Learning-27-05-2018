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
    TextView loginResult;
    SharedPreferences sharedPreferences;
    View rootView;
    LayoutInflater myInflater;
    ViewGroup myContainer;
    EditText username;
    EditText password;
    TextView email;
    public static final String MyPREFERENCES = "MyPrefs" ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //username.setVisibility(EditText.GONE);
        myInflater = inflater;
        myContainer = container;
        sharedPreferences = getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        rootView = myInflater.inflate(R.layout.profile_fragment,container,false);
        final String usernameL = sharedPreferences.getString("username","not found");
        String passwordL = sharedPreferences.getString("password","not found");

        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Button loginButton = (Button) rootView.findViewById(R.id.loginButton);
        username = (EditText) rootView.findViewById(R.id.username);
        password = (EditText)rootView.findViewById(R.id.password);

        email = (TextView)rootView.findViewById(R.id.email);

        loginResult = (TextView) rootView.findViewById(R.id.loginResult);

                loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (username.getText().toString().equals("")||password.getText().toString().equals(""))
                {
                    loginResult.setText("**username or password is empty!");
                    return;
                }
                //String url = "http://158.108.207.4/WAD_06/moocCheckLogin/chkLogin.php";
                String url = "http://158.108.207.7:8090/elearning/member/login";
                /*
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", username.getText().toString()));
                params.add(new BasicNameValuePair("password", password.getText().toString()));

                String resultServer  = getHttpPost(url,params);

                if (resultServer.equals("login success")) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", username.getText().toString());
                    editor.putString("password", password.getText().toString());
                    editor.commit();
                    //rootView = myInflater.inflate(R.layout.profile_fragment_logon,myContainer,false);

                    MainActivity.bottomNavigationItem.setCurrentItem(0);

                }
                else
                {
                    loginResult.setText(resultServer);
                }

        */      String json = POST(url,username.getText().toString(),password.getText().toString());
                Gson gson = new Gson();
                Profile profile = gson.fromJson(json,Profile.class);

                if (!json.equals(""))
                {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", profile.getMusername());
                    editor.putString("password", profile.getMpasswd());
                    editor.putString("name", profile.getMname());
                    editor.putString("surname", profile.getMsurname());
                    editor.putString("profile", profile.getMprofile());
                    editor.putString("email", profile.getMemail());

                    editor.commit();
                    Intent intent = new Intent(getContext(),MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
//                    MainActivity.bottomNavigationItem.setCurrentItem(0);
                }
                else
                {
                    loginResult.setText("login fail!");
                }

                //Toast.makeText(getContext(),POST(url,username.getText().toString(),password.getText().toString()),Toast.LENGTH_LONG).show();

            }
        });
        String emailL = sharedPreferences.getString("email","not found");
        String ppProfile = sharedPreferences.getString("profile","not found");
        String ppName = sharedPreferences.getString("name","not found");
        String ppSurname = sharedPreferences.getString("surname","not found");
        email.setText(emailL);
        Button logoutButton = (Button)rootView.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(getContext(),"logout", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(),MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
//                MainActivity.bottomNavigationItem.setCurrentItem(0);
            }
        });
        Button registerButton = (Button)rootView.findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Register.class);
                startActivity(intent);
            }
        });
        TextView pProfile = (TextView)rootView.findViewById(R.id.pprofile);
        TextView pUsername = (TextView)rootView.findViewById(R.id.pusername);
        TextView pName = (TextView)rootView.findViewById(R.id.pname);
        TextView pSurname = (TextView)rootView.findViewById(R.id.psurname);
        ImageView pImage = (ImageView)rootView.findViewById(R.id.ic_logo);
        email.setText("Email : "+emailL);
        pProfile.setText("Profile : "+ppProfile);
        pUsername.setText("Username : "+usernameL);
        pName.setText("Name : "+ppName);
        pSurname.setText("Surname : "+ppSurname);
        if (!usernameL.equals("not found")&&!passwordL.equals("not found"))
        {
            pImage.setImageResource(R.drawable.businessman);
            username.setVisibility(View.GONE);
            password.setVisibility(View.GONE);
            loginButton.setVisibility(View.GONE);
            loginResult.setVisibility(View.GONE);
            registerButton.setVisibility(View.GONE);

        }
        else
        {
            pProfile.setVisibility(View.GONE);
            pUsername.setVisibility(View.GONE);
            pName.setVisibility(View.GONE);
            pSurname.setVisibility(View.GONE);

            logoutButton.setVisibility(View.GONE);
            email.setVisibility(View.GONE);
        }

        return rootView;
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
            jsonObject.accumulate("musername", username);
            jsonObject.accumulate("mpasswd", password);


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
