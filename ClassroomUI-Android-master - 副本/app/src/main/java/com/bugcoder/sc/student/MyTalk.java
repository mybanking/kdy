package com.bugcoder.sc.student;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyTalk extends AppCompatActivity {

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            System.out.println("获得handle事件");
            System.out.println("Handle处理："+ msg.obj);

            if(msg.what==2){
//
//                for(){
//
//                }
            }
            super.handleMessage(msg);

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Talk talk = (Talk) getIntent().getSerializableExtra("talk_data");
        final TextView talk_name=findViewById(R.id.talk_name);
       final  TextView talk_question=findViewById(R.id.talk_question);
       final  TextView talk_date=findViewById(R.id.talk_date);
        talk_name.setText(talk.getStuName());
        talk_question.setText(talk.getQuestion());
        talk_date.setText(talk.getDate());

        //       //加载发送数据
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("stuId","1");
//            jsonObject.put("courseId","1");
//            jsonObject.put("signal","loadQuestion");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        OkHttpClient okHttpClient = new OkHttpClient();
//        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), String.valueOf(jsonObject));
//        Request request = new Request.Builder()
//                .url("http://www.baidu.com")//请求的url
//                .post(requestBody)
//                .build();
//
//        okhttp3.Call call = okHttpClient.newCall(request);
//        System.out.println("请求成功");
//        //返回
//        call.enqueue(new okhttp3.Callback(){
//            @Override
//            public void onFailure(Call call, IOException e) {
//                System.out.println("数据获取失败:"+e.toString());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Message msg = myHandler.obtainMessage();
//                msg.what = 1;
//                msg.obj = response.body().string();
//                myHandler.sendMessage(msg);
//                System.out.println("Handle发送："+ msg.obj);
//            }
//        });


        final LinearLayout layout = findViewById(R.id.center1);

        //循环加载
//        layout.addView(createCardView("1","1","1"));
        //进行讨论
        Button talk_send=findViewById(R.id.talk_send);
//        ScrollView scrollView=findViewById(R.id.scrollView);
//        scrollView.fullScroll(ScrollView.FOCUS_DOWN);
        talk_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et =findViewById(R.id.send_message);
                String q= et.getText().toString();//获取文本
                Date date = new Date(System.currentTimeMillis());  //系统当前时间
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                String d= dateFormat.format(date);//获取时间
                layout.addView(createCardView("孔德焱",q,d));
                et.setText("");

                //加载发送数据
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("stuId","17301066");
                    jsonObject.put("courseId","17301066");
                    jsonObject.put("stuName","孔德焱");
                    jsonObject.put("date",talk_date);
                    jsonObject.put("TstuId","17301066");
                    jsonObject.put("TstuName","孔德焱");
                    jsonObject.put("assess",q);
                    jsonObject.put("Tdate",d);
                    jsonObject.put("signal","TsavaQuestion");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), String.valueOf(jsonObject));
                Request request = new Request.Builder()
                        .url("http://www.baidu.com")//请求的url
                        .post(requestBody)
                        .build();

                okhttp3.Call call = okHttpClient.newCall(request);
                System.out.println("请求成功");
                //返回
                call.enqueue(new okhttp3.Callback(){
                    @Override
                    public void onFailure(Call call, IOException e) {
                        System.out.println("数据获取失败:"+e.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Message msg = myHandler.obtainMessage();
                        msg.what = 2;
                        msg.obj = response.body().string();
                        myHandler.sendMessage(msg);
                        System.out.println("Handle发送："+ msg.obj);
                    }
                });
            }
        });
    }

    public CardView createCardView(String name, String question, String data){
        //布局
        LinearLayout.LayoutParams lp_cardView = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams lp_texteView = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT ,LinearLayout.LayoutParams.WRAP_CONTENT);

        //视图属性
        TextView textView=new TextView(this);

        textView.setText(name);
        lp_texteView.setMargins(0,0,0,8);
        textView.setLayoutParams(lp_texteView);
        textView.setBackground(getResources().getDrawable(R.drawable.blue_rounded_solid));
        textView.setPadding(12,8,12,8);
        textView.setTextColor(getResources().getColor(R.color.white));
        textView.setTextSize(14);
//视图属性
        TextView textView1=new TextView(this);

        textView1.setText(question);
        lp_texteView.setMargins(0,0,0,4);
        textView1.setLayoutParams(lp_texteView);
        textView1.setPadding(12,8,12,8);
        textView1.setTextColor(Color.BLACK);
        textView1.setTextSize(16);
//视图属性
        TextView textView2=new TextView(this);

        textView2.setText(data);
        textView2.setLayoutParams(lp_texteView);
        textView2.setTextSize(16);

        LinearLayout linearLayout=new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(lp_cardView);
        linearLayout.addView(textView);
        linearLayout.addView(textView1);
        linearLayout.addView(textView2);


//        android.support.v7.widget.CardView cardView = new android.support.v7.widget.CardView(this);
        final CardView cardView=new CardView(this);
        lp_cardView.setMargins(2,2,2,2);
        cardView.setLayoutParams(lp_cardView);
        cardView.setRadius(4);
        cardView.setElevation(4);
        cardView.setUseCompatPadding(true);
        cardView.setContentPadding(16,16,16,16);
        cardView.addView(linearLayout);
        return cardView;
    }
}