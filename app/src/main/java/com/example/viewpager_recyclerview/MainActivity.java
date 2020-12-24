    package com.example.viewpager_recyclerview;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.fragment.app.Fragment;
    import androidx.fragment.app.FragmentManager;
    import androidx.fragment.app.FragmentTransaction;
    import androidx.viewpager.widget.ViewPager;

    import android.os.Bundle;
    import android.widget.FrameLayout;

    import com.google.android.material.tabs.TabLayout;


    public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            tabLayout=(TabLayout)findViewById(R.id.tabLayout);
            viewPager=(ViewPager)findViewById(R.id.viewPager);
            viewPager.setOffscreenPageLimit(2);
            adapter=new ViewPagerAdapter(getSupportFragmentManager());
            adapter.AddFragment(new HomeFragment(),"Home");
            adapter.AddFragment(new DashboardFragment(),"Dashboard");
            adapter.AddFragment(new NotificationFragment(),"Notifications");
            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);

            tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_home_24);
            tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_dashboard_24);
            tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_notifications_24);

            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

        }
    }