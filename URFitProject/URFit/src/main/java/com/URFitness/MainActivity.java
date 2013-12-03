package com.URFitness;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.os.Build;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

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
    }
    public void jumpToPage(View v){
        String result = null;

        EditText mEditu = (EditText) findViewById(R.id.userLogin);
        System.out.println("1");
        EditText mEditp = (EditText) findViewById(R.id.editText2);
        System.out.println("2");

        String uname= mEditu.getText().toString();
        String upass=mEditp.getText().toString();
        System.out.println("3");
        HttpClient client = new DefaultHttpClient();
        System.out.println("4");

         String tem="http://urfitness.org/mobile_login.php?username="+uname+"&password="+upass;
        HttpGet request = new HttpGet(tem);
        try
        {
            HttpResponse response=client.execute(request);
            System.out.println("5");
            HttpEntity entity=response.getEntity();
            System.out.println("6");
            InputStream is=entity.getContent();
            System.out.println("7");
            InputStreamReader isr = new InputStreamReader(is);
            System.out.println("8");
            BufferedReader reader = new BufferedReader(isr);
            System.out.println("9");
            result=reader.readLine();
            System.out.println("10");
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        if(result.equals("1")==true)
        {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);

        }
        else
        {


        }



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
