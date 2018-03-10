package com.example.windows10.ltd_learning.mFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows10.ltd_learning.MainActivity;
import com.example.windows10.ltd_learning.Profile;
import com.example.windows10.ltd_learning.R;
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
 * Created by natsanai on 1/28/2018.
 */

public class RegisterFragment extends Fragment
{
    private final String URL_ADD_MEMBER = "http://158.108.207.7:8090/elearning/member/add";
    private SharedPreferences sharedPreferences;
    private static final String MyPREFERENCES = "MyPrefs" ;
    private EditText name;
    private EditText surname;
    private EditText username;
    private EditText password;
    private EditText retypePassword;
    private EditText profile;
    private EditText email;
    private TextView txtWarning ;
    private Button registerButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_register,container,false);
        sharedPreferences = getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        bindView(rootView);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().equals("")||surname.getText().toString().equals("")||username.getText().toString().equals("")||password.getText().toString().equals("")||profile.getText().toString().equals("")||email.getText().toString().equals(""))
                {
                    txtWarning.setText("some field is empty");
                    return;
                }
                else
                {
                    if(!(password.getText().toString().equals(retypePassword.getText().toString())))
                    {
                        txtWarning.setText("password does not match");
                        return;
                    }
                }

                String json = POST(URL_ADD_MEMBER,name.getText().toString(),surname.getText().toString(),username.getText().toString(),password.getText().toString(),profile.getText().toString(),email.getText().toString());
                if(json.equals(""))
                {
                    txtWarning.setText("INTERNET_DISCONNECTED");
                    return;
                }
                Gson gson = new Gson();
                Profile profile_ = gson.fromJson(json,Profile.class);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("checkLogin",true);
                editor.putInt("idMember",profile_.getIdmember());
                editor.putString("pProfile",profile_.getProfile());
                editor.putString("pUsername",profile_.getUsername());
                editor.putString("pName",profile_.getName());
                editor.putString("pSurname",profile_.getSurname());
                editor.putString("pEmail",profile_.getEmail());
                editor.commit();
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                //Toast.makeText(RegisterFragment.this.getContext(),json,Toast.LENGTH_LONG);
            }
        });

        return rootView;
    }
    public void bindView(View view)
    {
        name = (EditText)view.findViewById(R.id.mname);
        surname = (EditText)view. findViewById(R.id.msurname);
        username = (EditText)view. findViewById(R.id.musername);
        password = (EditText)view. findViewById(R.id.mpasswd);
        retypePassword = (EditText)view. findViewById(R.id.retypePasswd);
        profile = (EditText)view. findViewById(R.id.mprofile);
        email = (EditText)view. findViewById(R.id.memail);
        txtWarning = (TextView)view. findViewById(R.id.txtWarning);
        registerButton = (Button)view.findViewById(R.id.registerButton);
    }

    public static String POST(String url, String name,String surname,String username,String password,String profile,String email){
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
            jsonObject.accumulate("name", name);
            jsonObject.accumulate("surname", surname);
            jsonObject.accumulate("username", username);
            jsonObject.accumulate("passwd", password);
            jsonObject.accumulate("profile", profile);
            jsonObject.accumulate("email", email);


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