package com.URFitness;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class BuddylistActivity extends Activity {

    ListView list;
    String[] web = {"Alex Wilson", "Charlie Kelman", "Edward Barthélemy", "Daniel Weiner", "Tessa Eagle", "Michael Holupka", "Jamie Jones", "Dan Doogle", "John Robb", "Kevin Rodman", "Kurt Dinelle"};
    Integer[] imageId = {
            R.drawable.guy,
            R.drawable.guy,
            R.drawable.guy,
            R.drawable.guy,
            R.drawable.guy,
            R.drawable.guy,
            R.drawable.guy,
            R.drawable.guy,
            R.drawable.guy,
            R.drawable.guy,
            R.drawable.guy
    };
    private String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buddylist);

        username = getIntent().getExtras().getString("usrname").toString();
        CustomList adapter = new
                CustomList(BuddylistActivity.this, web, imageId);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Toast.makeText(BuddylistActivity.this, "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), OtherProfileActivity.class);
                intent.putExtra("usrname",username);
                intent.putExtra("otherusr", "ckelman@u.rochester.edu");
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.buddylist, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_buddylist, container, false);
            return rootView;
        }
    }

}
