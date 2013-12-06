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
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class OtherProfileActivity extends ActionBarActivity {

    private String fname;
    private String lname;
    private String phone;
    private String facebook;
    private String twitter;
    private String lift;
    private String cardio;
    private String bio;
    private String username;
    private String otheruser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);

        username = getIntent().getExtras().getString("usrname").toString();
        otheruser = getIntent().getExtras().getString("otherusr").toString();

        System.out.print("HAHAHAHA" + otheruser);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        
        


        String url ="http://www.urfitness.org/mobile_getdata.php?lookfor=first&where=user&is="+otheruser;
        try
        {
            //firstname
            fname=sendGet(url);
            TextView fnameE = (TextView) findViewById(R.id.firstName);
            fnameE.setText(fname, TextView.BufferType.NORMAL);

            //lastname
            url = "http://www.urfitness.org/mobile_getdata.php?lookfor=last&where=user&is="+otheruser;
            lname = sendGet(url);
            TextView lnameE = (TextView) findViewById(R.id.lastName);
            lnameE.setText(lname, TextView.BufferType.NORMAL);

            //phonenum
            url = "http://www.urfitness.org/mobile_getdata.php?lookfor=phone_number&where=user&is="+otheruser;
            phone = sendGet(url);
            TextView phoneE = (TextView) findViewById(R.id.userPhone);
            phoneE.setText(phone, TextView.BufferType.NORMAL);

            //facebook
            url = "http://www.urfitness.org/mobile_getdata.php?lookfor=facebook_url&where=user&is="+otheruser;
            facebook = sendGet(url);
            TextView facebookE = (TextView) findViewById(R.id.userFacebook);
            facebookE.setText(facebook, TextView.BufferType.NORMAL);

            //twitter
            url = "http://www.urfitness.org/mobile_getdata.php?lookfor=twitter_url&where=user&is="+otheruser;
            twitter = sendGet(url);
            TextView twitterE = (TextView) findViewById(R.id.userTwitter);
            twitterE.setText(twitter, TextView.BufferType.NORMAL);

            //bio
            url = "http://www.urfitness.org/mobile_getdata.php?lookfor=bio&where=user&is="+otheruser;
            bio = sendGet(url);
            System.out.println(bio);
            TextView bioE = (TextView) findViewById(R.id.user_bio);
            bioE.setText(bio, TextView.BufferType.NORMAL);

            //weightlifting
            url = "http://www.urfitness.org/mobile_getdata.php?lookfor=weight_lifting&where=user&is="+otheruser;
            lift = sendGet(url);
            TextView weightE = (TextView) findViewById(R.id.weight_experience);
            if(lift.equals("0"))
            {
                lift = "Beginner";
            }
            else if(lift.equals("1"))
            {
                lift = "Intermediate";
            }
            else
                lift = "Advanced";
            weightE.setText(lift, TextView.BufferType.NORMAL);

            //cardio
            url = "http://www.urfitness.org/mobile_getdata.php?lookfor=cardio&where=user&is="+otheruser;
            cardio = sendGet(url);
            TextView cardioE = (TextView) findViewById(R.id.card_experience);
            if(cardio.equals("0"))
            {
                cardio = "Beginner";
            }
            else if(cardio.equals("1"))
            {
                cardio = "Intermediate";
            }
            else
                cardio = "Advanced";
            cardioE.setText(cardio, TextView.BufferType.NORMAL);



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
        getMenuInflater().inflate(R.menu.other_profile, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_other_profile, container, false);
            return rootView;
        }
    }

    public void submit_prof(View v){
        Intent intent = new Intent(getApplicationContext(), BuddylistActivity.class);
        intent.putExtra("usrname",username);
        startActivity(intent);
    }

}
