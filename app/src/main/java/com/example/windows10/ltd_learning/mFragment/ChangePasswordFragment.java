package com.example.windows10.ltd_learning.mFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows10.ltd_learning.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by natsanai on 2/12/2018.
 */

public class ChangePasswordFragment extends Fragment {
    private EditText currentPasswd;
    private EditText newPasswd;
    private EditText newPasswdAgain;
    private Button okButton;
    private TextView textWarning;

    private final String MyPREFERENCES = "MyPrefs" ;
    private SharedPreferences sharedPreferences;
    private final String URL_CHANGE_PASSWORD = "http://158.108.207.7:8090/elearning/member/update";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.change_password,container,false);
        bindView(view);
        sharedPreferences = getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentPasswd.getText().toString().equals("")||newPasswd.getText().toString().equals("")||newPasswdAgain.getText().toString().equals(""))
                {
                    textWarning.setText("Some field is empty!");
                    return;
                }
                if (!newPasswd.getText().toString().equals(newPasswdAgain.getText().toString()))
                {
                    textWarning.setText("password does not match");
                    return;
                }
                String data = String.format("{\n" +
                                "        \"idmember\": %d,\n" +
                                "        \"passwd\": \"%s\",\n" +
                                "        \"oldPasswd\": \"%s\"\n" +
                                "    \n" +
                                "    }",
                        sharedPreferences.getInt("idMember",-1),
                        newPasswd.getText().toString(),
                        currentPasswd.getText().toString());

                try {

                    JSONObject jsonObject = new JSONObject(sendPut(data,URL_CHANGE_PASSWORD));

                    if(jsonObject.getBoolean("status"))
                    {
                        Toast.makeText(getContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                        getFragmentManager().popBackStackImmediate();
                    }
                    else
                    {
                        textWarning.setText(jsonObject.getString("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        });
        return view;
    }
    public void bindView(View view)
    {
        currentPasswd = view.findViewById(R.id.current_passwd);
        newPasswd = view.findViewById(R.id.new_passwd);
        newPasswdAgain = view.findViewById(R.id.new_passwd_again);
        okButton = view.findViewById(R.id.okButton);
        textWarning = view.findViewById(R.id.txtWarning);
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
            if (response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 204|| response.getStatusLine().getStatusCode() == 501) {

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
}
