package eventhive.com.eventhive;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import eventhive.com.eventhive.Tablayout.SlidingTabLayout;


public class EventHomeScreen extends AppCompatActivity {


    //ActionBar actionBar;
    Toolbar toolbar;
    private ViewPager vwpager;
    private SlidingTabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_home_screen);
        //actionBar = android.support.v7.app.ActionBar.getActionBar();
        //assert actionBar != null;
        vwpager = (ViewPager) findViewById(R.id.pager);
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        vwpager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        tabs.setCustomTabView(R.layout.custom_tab_layout, R.id.tabtext);
        tabs.setDistributeEvenly(true);
        tabs.setBackgroundColor(getResources().getColor(R.color.primaryColor));
       // tabs.setSelectedIndicatorColors(R.color.whitecolor);
    tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
              return getResources().getColor(R.color.accentColor);
           }
        });
        tabs.setViewPager(vwpager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class PagerAdapter extends FragmentPagerAdapter {

        String[] tabstrings = getResources().getStringArray(R.array.tabstring);

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment frag = null;
            if (position == 0) {
                frag = new AcceptedEvents();
            }
            if (position == 1) {
                frag = new CreatedEvents();
            }
            if (position == 2) {
                frag = new PendingEvent();
            }

            return frag;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabstrings[position];
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}