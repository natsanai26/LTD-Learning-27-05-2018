package com.example.windows10.ltd_learning;

/**
 * Created by Windows10 on 12/6/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.windows10.ltd_learning.mFragment.ProfileFragment;
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

public class Register extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        final EditText name = (EditText) findViewById(R.id.mname);
        final EditText surname = (EditText) findViewById(R.id.msurname);
        final EditText username = (EditText) findViewById(R.id.musername);
        final EditText password = (EditText) findViewById(R.id.mpasswd);
        final EditText retypePassword = (EditText) findViewById(R.id.retypePasswd);
        final EditText profile_ = (EditText) findViewById(R.id.mprofile);
        final EditText email = (EditText) findViewById(R.id.memail);
        final TextView txtWarning = (TextView) findViewById(R.id.txtWarning);

        Button registerButton = (Button)findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals("")||surname.getText().toString().equals("")||username.getText().toString().equals("")||password.getText().toString().equals("")||profile_.getText().toString().equals("")||email.getText().toString().equals(""))
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
                String url = "http://158.108.207.7:8090/elearning/member/add";
                String json = POST(url,name.getText().toString(),surname.getText().toString(),username.getText().toString(),password.getText().toString(),profile_.getText().toString(),email.getText().toString());
                Gson gson = new Gson();
                Profile profile = gson.fromJson(json,Profile.class);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", profile.getUsername());
                editor.putString("password", profile.getPasswd());
                editor.putString("name", profile.getName());
                editor.putString("surname", profile.getSurname());
                editor.putString("profile", profile.getProfile());
                editor.putString("email", profile.getEmail());
                editor.commit();
                Intent intent = new Intent(Register.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
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