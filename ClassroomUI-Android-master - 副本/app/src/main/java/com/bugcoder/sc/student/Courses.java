package com.bugcoder.sc.student;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Vector;

import okhttp3.Call;
import okhttp3.Response;

public class Courses extends AppCompatActivity {
    private LinearLayout ll_course_list;
    Vector v = new Vector();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        init();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        HttpUtil.sendRequestWithOkhttp("http://192.168.3.56:8081/stu/course/s1", new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("shibai");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message msg = myHandler.obtainMessage();
                msg.what = 1;
                msg.obj = response.body().string();
                myHandler.sendMessage(msg);
                System.out.println("123");
                //parseJsonWithJsonObject(response);
            }
        });
    }

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    try {
                        System.out.println("456");
                        System.out.println((String) msg.obj);
                        parseJsonWithJsonObject((String) msg.obj);
                        for (int i = 0; i < v.size(); i++) {
                            System.out.println("start: " + i);
                            addCourse((Course) v.elementAt(i));
                            System.out.println(i);
                        }
                    } catch (IOException e) {

                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void init() {
        ll_course_list = findViewById(R.id.course_list);
    }

    public void addCourse(Course course) {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/calibri.ttf");

        android.support.v7.widget.CardView cardView = new android.support.v7.widget.CardView(this);
        LinearLayout.LayoutParams lp_cardView = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lp_cardView.setMargins(0, 0, 0, 0);
        cardView.setLayoutParams(lp_cardView);
        cardView.setRadius(8);
        cardView.setUseCompatPadding(true);
        cardView.setContentPadding(8, 8, 8, 8);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getApplicationContext(), MyProfile.class);
                startActivity(intent);
            }
        });
        ll_course_list.addView(cardView);

        LinearLayout ll_h1 = new LinearLayout(this);
        LinearLayout.LayoutParams lp_ll_h = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll_h1.setLayoutParams(lp_ll_h);
        ll_h1.setOrientation(LinearLayout.VERTICAL);
        cardView.addView(ll_h1);

        TextView tv_major = new TextView(this);
        LinearLayout.LayoutParams lp_tv1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_tv1.setMargins(18, 0, 0, 6);
        tv_major.setLayoutParams(lp_tv1);
        tv_major.setTypeface(typeface);
        tv_major.setText(course.major);
        tv_major.setTextColor(Color.parseColor("#000000"));
        tv_major.setTextSize(16);
        ll_h1.addView(tv_major);

        TextView tv_course_name = new TextView(this);
        LinearLayout.LayoutParams lp_tv2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_tv2.setMargins(18, 0, 0, 8);
        tv_course_name.setLayoutParams(lp_tv2);
        tv_course_name.setTypeface(typeface);
        tv_course_name.setText(course.courseName);
        tv_course_name.setTextSize(14);
        ll_h1.addView(tv_course_name);

        if (course.taskNum != 0) {
            TextView tv_task = new TextView(this);
            LinearLayout.LayoutParams lp_tv4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp_tv4.setMargins(18, 0, 0, 4);
            tv_task.setLayoutParams(lp_tv4);
            tv_task.setTypeface(typeface);
            tv_task.setText(course.taskNum + " Task");
            tv_task.setTextColor(Color.parseColor("#1F2C77"));
            tv_task.setTextSize(14);
            ll_h1.addView(tv_task);
        }

        TextView tv_teacher_name = new TextView(this);
        LinearLayout.LayoutParams lp_tv3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_tv3.setMargins(0, 0, 18, 4);
        tv_teacher_name.setLayoutParams(lp_tv3);
        tv_teacher_name.setTypeface(typeface);
        tv_teacher_name.setText(course.teacherName);
        tv_teacher_name.setTextAlignment(TextView.TEXT_ALIGNMENT_VIEW_END);
        tv_teacher_name.setTextSize(14);
        ll_h1.addView(tv_teacher_name);

        ProgressBar pb = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        LinearLayout.LayoutParams lp_pb = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_pb.setMargins(18, 0, 18, 0);
        pb.setLayoutParams(lp_pb);
        pb.setProgress(course.myProgress);
        ll_h1.addView(pb);
    }

    //private void parseJsonWithJsonObject(Response response) throws IOException {
    private void parseJsonWithJsonObject(String responseData) throws IOException {

        System.out.println("789");
        //String responseData=response.body().string();
        System.out.println(responseData);

        try {
            JSONObject jsonObject = new JSONObject(responseData);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            System.out.println("array:" + jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String major = obj.getString("major");
                String courseName = obj.getString("courseName");
                String teacherName = obj.getString("teacherName");
                int taskNum = obj.getInt("taskNum");
                int myProgress = obj.getInt("myProgress");
                System.out.println("json: " + v.size());
                v.addElement(new Course(major, courseName, teacherName, taskNum, myProgress));
                System.out.println("jsonover: " + v.size());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
