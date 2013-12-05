package com.URFitness;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.os.Build;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        if( Build.VERSION.SDK_INT >= 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);
        }
    }
    public void jumpToPage(View v) throws IOException{

        //httppost= new HttpPost("\"http://urfitness.org/mobile_login.php?username=\"+uname+\"&password=\"+upass"); // make sure the url is correct.

        EditText mEditu = (EditText) findViewById(R.id.userLogin);
        EditText mEditp = (EditText) findViewById(R.id.editText2);

        String uname= mEditu.getText().toString();
        String upass=mEditp.getText().toString();

        System.out.println(uname + " |||||||||||||||||||" + upass);

       // httpclient=new DefaultHttpClient();
        //httppost= new HttpPost("http://urfitness.org/mobile_login.php?username=daniel.weiner@rochester.edu&password=poop");



/**
        String result = "";
        InputStream is = null;
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("username","awils18@u.rochester.edu"));
        nameValuePairs.add(new BasicNameValuePair("password","alexwilson"));

        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://urfitness.org/mobile_login.php?username=daniel.weiner@rochester.edu&password=poop");
            System.out.println("WELL THIS WORKS");
            //httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        }catch(Exception e){
            Log.e("log_tag", "Error in http connection "+e.toString());
        }

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();

            result=sb.toString();
        }catch(Exception e){
            Log.e("log_tag", "Error converting result "+e.toString());
        }

//parse json data
        try{
            JSONArray jArray = new JSONArray(result);
            for(int i=0;i<jArray.length();i++){
                JSONObject json_data = jArray.getJSONObject(i);
                Log.i("log_tag","id: "+json_data.getInt("id")+
                        ", name: "+json_data.getString("name")+
                        ", sex: "+json_data.getInt("sex")+
                        ", birthyear: "+json_data.getInt("birthyear")
                );
            }
        }
    catch(JSONException e){
        Log.e("log_tag", "Error parsing data "+e.toString());
    }**/
    //System.out.println(inputStreamToString(getInputStreamFromUrl("http://urfitness.org/mobile_login.php?username=daniel.weiner@rochester.edu&password=poop")));

       int ans  = connect("http://urfitness.org/mobile_login.php?username=daniel.weiner@rochester.edu&password=poop");
        //System.out.println("*************"+ans);
        if (ans == -1)
        {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        }
        else if (ans == 0)
        {
            Intent intent = new Intent(getApplicationContext(), WeightOptionsActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(getApplicationContext(), SportsOptionsActivity.class);
            startActivity(intent);
        }
    }

    public static int connect(String url)
    {

        //System.out.println("1");
        HttpClient httpclient = new DefaultHttpClient();
        //System.out.println("2");
        // Prepare a request object
        HttpGet httpget = new HttpGet(url);
        //System.out.println("3");

        // Execute the request
        HttpResponse response;
        try {
            response = httpclient.execute(httpget);
            //System.out.println("4");
            // Examine the response status
            Log.i("Praeda",response.getStatusLine().toString());
            //System.out.println("5");


            // Get hold of the response entity
            HttpEntity entity = response.getEntity();
           // System.out.println("6");
            // If the response does not enclose an entity, there is no need
            // to worry about connection release


            if (entity != null) {

                // A Simple JSON Response Read
                InputStream instream = entity.getContent();
                String result= convertStreamToString(instream);
                // now you have the string representation of the HTML request
                System.out.print("HERE WE ARE" + result);
                instream.close();

                if(result.equals("1"))
                    return 1;
                else
                    return -1;
            }


        } catch (Exception e) { System.out.print("THERE IS A CONNECTION ERROR\n"+e);}

        return 0;

    }

    private static String convertStreamToString(InputStream is) {
    /*
     * To convert the InputStream to String we use the BufferedReader.readLine()
     * mdfeethod. We iterate until the BufferedReader return null which means
     * there's no more data to read. Each line will appended to a StringBuilder
     * and returned as String.
     */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }





    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
