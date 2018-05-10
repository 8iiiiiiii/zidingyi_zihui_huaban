package com.example.com.rk0510_demo;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.jane.mxlistview.view.XListView;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private XListView xlv;
    private int url = 10;
    private  int type = 1;
    private Mybase base;
    private List<UserBean.NewslistBean> list = new ArrayList<>();
    private String path = "http://api.tianapi.com/world/?key=0678218dc644ad08e9108a02bdf21d19&num="+url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xlv = findViewById(R.id.xlv);
        initXlv();
        getdata();
    }

    private void initXlv() {
        xlv.setPullLoadEnable(true);
        xlv.setPullRefreshEnable(true);
        xlv.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                url = 10;
                type = 1;
                path = "http://api.tianapi.com/world/?key=0678218dc644ad08e9108a02bdf21d19&num="+url;
                getdata();
            }

            @Override
            public void onLoadMore() {
                url ++;
                type = 2;
                path = "http://api.tianapi.com/world/?key=0678218dc644ad08e9108a02bdf21d19&num="+url;
                getdata();
            }
        });
    }

    //调用
    private void getdata() {
    new MyTask().execute(path);
    }
    //获取数据
    class MyTask extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(5000);
                urlConnection.setConnectTimeout(5000);
                if(urlConnection.getResponseCode()==200){
                    InputStream inputStream = urlConnection.getInputStream();
                    String s = StreamToString(inputStream);
                    return s;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            List<UserBean.NewslistBean> news = gson.fromJson(s, UserBean.class).getNewslist();
            if (type==1){
                xlv.stopRefresh();
                list.clear();
            }else{
                xlv.stopLoadMore();
            }
            list.addAll(news);
            setAdpter();

        }
    }

    //设置适配器
    private void setAdpter() {
    if(base==null){
        xlv.setAdapter(new Mybase());
    }else{
        base.notifyDataSetChanged();
    }
    }


    private String StreamToString(InputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        String str;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            try {
            while ((str=reader.readLine())!=null){
               sb.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    class Mybase extends BaseAdapter{

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//使用内存缓存
                .cacheOnDisk(true)//使用磁盘缓存
                .showImageOnFail(R.mipmap.ic_launcher)//下载失败时显示的图片
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片色彩模式
                .imageScaleType(ImageScaleType.EXACTLY)//设置图片的缩放模式
                .build();

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHodler hodler;
            if(view==null){
                view = View.inflate(MainActivity.this,R.layout.mybase,null);
                hodler = new ViewHodler();
                hodler.image = view.findViewById(R.id.image);
                hodler.title = view.findViewById(R.id.title);
                view.setTag(hodler);
            }else{
                hodler = (ViewHodler) view.getTag();
            }
            ImageLoader.getInstance().displayImage(list.get(i).getPicUrl(),hodler.image,options);
            hodler.title.setText(list.get(i).getTitle());
            return view;
        }
        class ViewHodler{
            ImageView image;
            TextView title;
        }
    }
}
