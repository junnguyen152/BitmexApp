package skysoft.com.bitmexapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import skysoft.com.bitmexapp.Fragment.ChartFragment;
import skysoft.com.bitmexapp.Fragment.MarketsFragment;
import skysoft.com.bitmexapp.Fragment.RecentTradesFragment;
import skysoft.com.bitmexapp.Fragment.TradesFragment;
import skysoft.com.bitmexapp.R;

public class MainActivity extends AppCompatActivity {

    final MarketsFragment marketsFragment = new MarketsFragment();
    final TradesFragment tradesFragment = new TradesFragment();
    final ChartFragment chartFragment = new ChartFragment();
    final FragmentManager fm = getSupportFragmentManager();
    final RecentTradesFragment recentTradesFragment = new RecentTradesFragment();
    Fragment active = marketsFragment;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            switch (item.getItemId()) {
                case R.id.navigation_markets:
                    fm.beginTransaction().setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).hide(active).show(marketsFragment).commit();
                    active = marketsFragment;
                    return true;
                case R.id.navigation_order:
                    fm.beginTransaction().setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).hide(active).show(tradesFragment).commit();
                    active = tradesFragment;
                    return true;
                case R.id.navigation_recenttrades:
                    fm.beginTransaction().setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).hide(active).show(recentTradesFragment).commit();
                    active = recentTradesFragment;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_main);

        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);


        fm.beginTransaction().add(R.id.relative_fragment, marketsFragment).commit();
        fm.beginTransaction().add(R.id.relative_fragment, tradesFragment).hide(tradesFragment).commit();
        fm.beginTransaction().add(R.id.relative_fragment, recentTradesFragment).hide(recentTradesFragment).commit();
        //fm.beginTransaction().add(R.id.relative_fragment, chartFragment).hide(chartFragment).commit();

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void finish() {
        super.finish();
        onLeaveThisActivity();
    }

    protected void onLeaveThisActivity(){
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        onStartNewActivity();
    }

    @Override
    public void startActivity(Intent intent, Bundle options) {
        super.startActivity(intent, options);
        onStartNewActivity();
    }

    protected void onStartNewActivity() {
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }
}
