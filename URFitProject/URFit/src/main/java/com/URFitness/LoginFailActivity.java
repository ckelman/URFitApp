package com.URFitness;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.EditText;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginFailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_fail);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    public void jumpToPage(View v) throws IOException{
        EditText mEditu = (EditText) findViewById(R.id.userLogin);
        EditText mEditp = (EditText) findViewById(R.id.editText2);

        String uname= mEditu.getText().toString();
        String upass=mEditp.getText().toString();


        String url = "http://urfitness.org/mobile_login.php?username="+uname+"&password="+upass;


        try
        {
            String ans  = sendGet(url);

            if(ans.equals("1"))
            {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("usrname",uname );
                startActivity(intent);


            }
            else
            {
                Intent intent = new Intent(getApplicationContext(), LoginFailActivity.class);
                startActivity(intent);

            }
            System.out.println(ans);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }

    public String sendGet(String url) throws Exception {


        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);


        HttpResponse response = client.execute(request);

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " +
                response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        System.out.println(result.toString());
        return result.toString();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_fail, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_login_fail, container, false);
            return rootView;
        }
    }

}
