package com.bugcoder.sc.student;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    LinearLayout profile;
    LinearLayout ll_daily_schedule;

    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initData();
        initUI();
        Schedule s = new Schedule("Android", "Zhang Di", "08:00 - 10:00", "YF205", "12 Jul 2019");
        addSchedule(s);
//        Schedule s1 = new Schedule("asdfghjkl","Zhang Di","08:00 - 10:00","YF205","12 Jul 2019");
//        addSchedule(s1);
    }

    void initData() {
        count = 0;
    }

    void initUI() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        profile = header.findViewById(R.id.profile);
        profile.setOnClickListener(this);

        ll_daily_schedule = findViewById(R.id.ll_daily_schedule);
    }

    void addSchedule(final Schedule schedule) {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/calibri.ttf");
        //ll_daily_schedule.removeView(ll_daily_schedule);

        android.support.v7.widget.CardView cardView = new android.support.v7.widget.CardView(this);
        LinearLayout.LayoutParams lp_cardView = new LinearLayout.LayoutParams(800, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_cardView.setMargins(0, 10, 0, 0);
        cardView.setLayoutParams(lp_cardView);
        cardView.setRadius(8);
        cardView.setElevation(16);
        cardView.setUseCompatPadding(true);
        cardView.setContentPadding(16, 16, 16, 16);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getApplicationContext(), CourseLiving.class);
                intent.putExtra("schedule", schedule);
                startActivity(intent);
            }
        });
        ll_daily_schedule.addView(cardView);

        LinearLayout ll_card = new LinearLayout(this);
        ll_card.setOrientation(LinearLayout.VERTICAL);
        cardView.addView(ll_card);

        TextView tv_course_name = new TextView(this);
        LinearLayout.LayoutParams lp_tv1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_tv1.setMargins(0, 0, 0, 12);
        tv_course_name.setLayoutParams(lp_tv1);
        tv_course_name.setTypeface(typeface);
        tv_course_name.setText(schedule.courseName);
        tv_course_name.setTextColor(Color.parseColor("#c45248"));
        tv_course_name.setTextSize(18);
        ll_card.addView(tv_course_name);

        TextView tv_teacher_name = new TextView(this);
        LinearLayout.LayoutParams lp_tv2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_tv2.setMargins(0, 0, 0, 16);
        tv_teacher_name.setLayoutParams(lp_tv2);
        tv_teacher_name.setTypeface(typeface);
        tv_teacher_name.setTextColor(Color.parseColor("#000000"));
        tv_teacher_name.setText(schedule.teacherName);
        //tv_teacher_name.setTextSize(14);
        ll_card.addView(tv_teacher_name);

        View v = new View(this);
        LinearLayout.LayoutParams lp_v = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 3);
        lp_v.setMargins(0, 6, 0, 20);
        v.setLayoutParams(lp_v);
        v.setBackgroundColor(Color.parseColor("#80D1D1D1"));
        ll_card.addView(v);

        LinearLayout ll_content = new LinearLayout(this);
        LinearLayout.LayoutParams lp_ll_content = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_ll_content.weight = 100;
        ll_content.setLayoutParams(lp_ll_content);
        ll_content.setOrientation(LinearLayout.HORIZONTAL);
        ll_card.addView(ll_content);

        LinearLayout ll_content_1 = new LinearLayout(this);
        LinearLayout.LayoutParams lp_ll_content_1 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_ll_content_1.weight = 50;
        ll_content_1.setLayoutParams(lp_ll_content);
        ll_content_1.setOrientation(LinearLayout.VERTICAL);
        ll_content.addView(ll_content_1);

        LinearLayout ll_content_2 = new LinearLayout(this);
        LinearLayout.LayoutParams lp_ll_content_2 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_ll_content_2.weight = 50;
        ll_content_2.setLayoutParams(lp_ll_content);
        ll_content_2.setOrientation(LinearLayout.VERTICAL);
        ll_content.addView(ll_content_2);

        TextView tv_time = new TextView(this);
        LinearLayout.LayoutParams lp_tv3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_tv3.setMargins(0, 0, 0, 16);
        //lp_tv3.gravity = Gravity.CENTER;
        tv_time.setLayoutParams(lp_tv3);
        tv_time.setTypeface(typeface);
        tv_time.setText(schedule.time);
        tv_time.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_access_time_red_24dp), null, null, null);
        tv_time.setCompoundDrawablePadding(8);
        //tv_teacher_name.setTextSize(14);
        ll_content_1.addView(tv_time);

        TextView tv_date = new TextView(this);
        LinearLayout.LayoutParams lp_tv4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_tv4.setMargins(0, 0, 0, 6);
        //lp_tv4.gravity = Gravity.CENTER;
        tv_date.setLayoutParams(lp_tv4);
        tv_date.setTypeface(typeface);
        tv_date.setText(schedule.date);
        tv_date.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_event_available_red_16dp), null, null, null);
        tv_date.setCompoundDrawablePadding(8);
        //tv_teacher_name.setTextSize(14);
        ll_content_1.addView(tv_date);

        TextView tv_room = new TextView(this);
        LinearLayout.LayoutParams lp_tv5 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_tv5.setMargins(0, 0, 0, 16);
        //lp_tv5.gravity = Gravity.CENTER;
        tv_room.setLayoutParams(lp_tv5);
        tv_room.setTypeface(typeface);
        tv_room.setText(schedule.room);
        tv_room.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_location_on_red_24dp), null, null, null);
        tv_room.setCompoundDrawablePadding(8);
        //tv_teacher_name.setTextSize(14);
        ll_content_2.addView(tv_room);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_notification) {
            Intent intent = new Intent(getApplicationContext(), Events.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_courses) {
            Intent intent = new Intent(getApplicationContext(), Courses.class);
            startActivity(intent);
        } else if (id == R.id.nav_events) {
            Intent intent = new Intent(getApplicationContext(), Events.class);
            startActivity(intent);
        } else if (id == R.id.nav_announcements) {
            Intent intent = new Intent(getApplicationContext(), Announcements.class);
            startActivity(intent);
        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(getApplicationContext(), Settings.class);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            Intent intent = new Intent(getApplicationContext(), LoginScreen.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.profile) {
            Intent intent = new Intent(getApplicationContext(), MyProfile.class);
            startActivity(intent);
        }
    }
}
