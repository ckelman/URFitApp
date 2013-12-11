package com.URFitness;

import android.app.Activity;
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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FindBuddyActivity extends Activity {

    private String username = "";
    private String category= "";
    private String out = "";
    ListView list;
    String[] web;
    Integer[] imageId;


    String[] ids;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findbuddy);

        username = getIntent().getExtras().getString("usrname").toString();
        category = getIntent().getExtras().getString("catgry").toString();

        try
        {
            if(category.equals("weights"))
            {
                out = sendGet("http://urfitness.org/mobile_getLiftingMatchIds.php?user="+username);
            }
            else if(category.equals("buddies"))
            {
                out = sendGet("http://www.urfitness.org/mobile_getdata.php?lookfor=buddy&where=user&is="+username);
            }
            else if(category.equals("cardio"))
            {
                out = sendGet("http://urfitness.org/mobile_getCardioMatchIds.php?user="+username);
            }
            else if(category.equals("tennis"))
            {
                out = sendGet("http://urfitness.org/mobile_getSportsIds.php?sport=tennis&user="+username);
            }
            else if(category.equals("squash"))
            {
                out = sendGet("http://urfitness.org/mobile_getSportsIds.php?sport=squash&user="+username);
            }
            else if(category.equals("football"))
            {
                out = sendGet("http://urfitness.org/mobile_getSportsIds.php?sport=football&user="+username);
            }
            else if(category.equals("soccer"))
            {
                out = sendGet("http://urfitness.org/mobile_getSportsIds.php?sport=soccer&user="+username);
            }
            else if(category.equals("badminton"))
            {
                out = sendGet("http://urfitness.org/mobile_getSportsIds.php?sport=badminton&user="+username);
            }
            else if(category.equals("basketball"))
            {
                out = sendGet("http://urfitness.org/mobile_getSportsIds.php?sport=basketball&user="+username);
            }
            else
            {
                out = sendGet("http://urfitness.org/mobile_getCardioMatchIds.php?user="+username);
            }
            ids = out.split(",");
            web = new String[ids.length];
            imageId = new Integer[ids.length];
            for(int i =0;i<imageId.length;i++)
            {
                imageId[i] = R.drawable.guy;
            }
            for(int i=0;i<web.length;i++)
            {
                web[i] = sendGet("http://www.urfitness.org/mobile_getdata.php?lookfor=first&where=id&is="+ids[i])+" "+ sendGet("http://www.urfitness.org/mobile_getdata.php?lookfor=last&where=id&is="+ids[i]);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        CustomList adapter = new
                CustomList(FindBuddyActivity.this, web, imageId);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Toast.makeText(FindBuddyActivity.this, "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), OtherProfileActivity.class);
                intent.putExtra("usrname",username);
                try
                {
                    intent.putExtra("otherusr",sendGet("http://www.urfitness.org/mobile_getdata.php?lookfor=user&where=id&is="+ids[+position]));
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                startActivity(intent);

            }
        });

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
        getMenuInflater().inflate(R.menu.find_buddy, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_findbuddy, container, false);
            return rootView;
        }
    }

}
