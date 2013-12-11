package com.URFitness;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class ProfileActivity extends ActionBarActivity {
    private String fname;
    private String lname;
    private String phone;
    private String facebook;
    private String twitter;
    private String lift;
    private String cardio;
    private String bio;
    private String username;
    private String bench_weight;

    EditText fnameE;
    EditText lnameE;
    EditText phoneE;
    EditText facebookE;
    EditText twitterE;
    EditText bioE;
    EditText bench_weightE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

       username = getIntent().getExtras().getString("usrname").toString();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        String url ="http://www.urfitness.org/mobile_getdata.php?lookfor=first&where=user&is="+username;
        try
        {
                //firstname
              fname=sendGet(url);
            fnameE = (EditText) findViewById(R.id.firstName);
            fnameE.setText(fname, TextView.BufferType.EDITABLE);

                //lastname
              url = "http://www.urfitness.org/mobile_getdata.php?lookfor=last&where=user&is="+username;
              lname = sendGet(url);
            lnameE = (EditText) findViewById(R.id.lastName);
            lnameE.setText(lname, TextView.BufferType.EDITABLE);

            //phonenum
            url = "http://www.urfitness.org/mobile_getdata.php?lookfor=phone_number&where=user&is="+username;
            phone = sendGet(url);
            phoneE = (EditText) findViewById(R.id.userPhone);
            phoneE.setText(phone, TextView.BufferType.EDITABLE);

            //facebook
            url = "http://www.urfitness.org/mobile_getdata.php?lookfor=facebook_url&where=user&is="+username;
            facebook = sendGet(url);
            facebookE = (EditText) findViewById(R.id.userFacebook);
            facebookE.setText(facebook, TextView.BufferType.EDITABLE);

            //twitter
            url = "http://www.urfitness.org/mobile_getdata.php?lookfor=twitter_url&where=user&is="+username;
            twitter = sendGet(url);
            twitterE = (EditText) findViewById(R.id.userTwitter);
            twitterE.setText(twitter, TextView.BufferType.EDITABLE);

            //bech weight
            url = "http://www.urfitness.org/mobile_getdata.php?lookfor=bench&where=user&is="+username;
            bench_weight = sendGet(url);
            System.out.println(bench_weight);
            bench_weightE = (EditText)findViewById(R.id.bench_weight);
            bench_weightE.setText(bench_weight, TextView.BufferType.EDITABLE);

            //bio
            url = "http://www.urfitness.org/mobile_getdata.php?lookfor=bio&where=user&is="+username;
            bio = sendGet(url);
            System.out.println(bio);
            bioE = (EditText) findViewById(R.id.user_bio);
            bioE.setText(bio, TextView.BufferType.EDITABLE);

            //weightlifting
            url = "http://www.urfitness.org/mobile_getdata.php?lookfor=weight_lifting&where=user&is="+username;
            lift = sendGet(url);
            Spinner liftS = (Spinner)findViewById(R.id.weight_experience);
            liftS.setPromptId(Integer.parseInt(lift));

            //cardio
            url = "http://www.urfitness.org/mobile_getdata.php?lookfor=cardio&where=user&is="+username;
            cardio = sendGet(url);
            Spinner cardS = (Spinner)findViewById(R.id.card_experience);
            cardS.setPromptId(Integer.parseInt(cardio));






        }
        catch(Exception e)
        {
            System.out.println(e);
        }


        if( Build.VERSION.SDK_INT >= 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);
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
        getMenuInflater().inflate(R.menu.profile, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
            return rootView;
        }
    }

    public void submit_prof(View v){
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.putExtra("usrname",username);

        fname = fnameE.getText().toString();
        lname = lnameE.getText().toString();
        phone=phoneE.getText().toString();
        facebook = facebookE.getText().toString();
        twitter = twitterE.getText().toString();
        bio = bioE.getText().toString();





        try
        {

            fname =java.net.URLEncoder.encode(fname, "ISO-8859-1");
            lname = java.net.URLEncoder.encode(lname, "ISO-8859-1");
            phone = java.net.URLEncoder.encode(phone, "ISO-8859-1");
            facebook = java.net.URLEncoder.encode(facebook, "ISO-8859-1");
            twitter = java.net.URLEncoder.encode(twitter, "ISO-8859-1");
            String biostring = java.net.URLEncoder.encode(bio.toString(), "ISO-8859-1");
            bench_weight = java.net.URLEncoder.encode(bench_weight, "ISO-8859-1");


            sendGet("http://urfitness.org/mobile_setValue.php?col=first&val="+fname+"&user="+username);
            sendGet("http://urfitness.org/mobile_setValue.php?col=last&val="+lname+"&user="+username);
            sendGet("http://urfitness.org/mobile_setValue.php?col=phone_number&val="+phone+"&user="+username);
            sendGet("http://urfitness.org/mobile_setValue.php?col=facebook_url&val="+facebook+"&user="+username);
            sendGet("http://urfitness.org/mobile_setValue.php?col=twitter_url&val="+twitter+"&user="+username);
            sendGet("http://urfitness.org/mobile_setValue.php?col=twitter_url&val="+bench_weight+"&user="+username);




            sendGet("http://urfitness.org/mobile_setValue.php?col=bio&val=" + biostring + "&user=" + username);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        startActivity(intent);
    }


    // show The Image




 class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}


}


