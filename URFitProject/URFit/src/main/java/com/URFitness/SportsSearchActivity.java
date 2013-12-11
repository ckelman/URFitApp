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

public class SportsSearchActivity extends ActionBarActivity {


    private String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_search);

        username = getIntent().getExtras().getString("usrname").toString();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sports_search, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_sports_search, container, false);
            return rootView;
        }
    }

    public void jumpToFindFootball(View v){
        Intent intent = new Intent(getApplicationContext(), FindBuddyActivity.class);
        intent.putExtra("usrname",username);
        intent.putExtra("catgry","football");
        startActivity(intent);
    }
    public void jumpToFindTennis(View v){
        Intent intent = new Intent(getApplicationContext(), FindBuddyActivity.class);
        intent.putExtra("usrname",username);
        intent.putExtra("catgry","tennis");
        startActivity(intent);
    }
    public void jumpToFindSquash(View v){
        Intent intent = new Intent(getApplicationContext(), FindBuddyActivity.class);
        intent.putExtra("usrname",username);
        intent.putExtra("catgry","squash");
        startActivity(intent);
    }
    public void jumpToFindBasketball(View v){
        Intent intent = new Intent(getApplicationContext(), FindBuddyActivity.class);
        intent.putExtra("usrname",username);
        intent.putExtra("catgry","basketball");
        startActivity(intent);
    }
    public void jumpToFindRaquetball(View v){
        Intent intent = new Intent(getApplicationContext(), FindBuddyActivity.class);
        intent.putExtra("usrname",username);
        intent.putExtra("catgry","raquetball");
        startActivity(intent);
    }
    public void jumpToFindSoccer(View v){
        Intent intent = new Intent(getApplicationContext(), FindBuddyActivity.class);
        intent.putExtra("usrname",username);
        intent.putExtra("catgry","soccer");
        startActivity(intent);
    }
    public void jumpToFindBadminton(View v){
        Intent intent = new Intent(getApplicationContext(), FindBuddyActivity.class);
        intent.putExtra("usrname",username);
        intent.putExtra("catgry","badminton");
        startActivity(intent);
    }

}
