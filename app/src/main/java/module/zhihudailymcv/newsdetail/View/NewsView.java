package module.zhihudailymcv.newsdetail.View;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import com.popo.zhihudailymvc.MainActivity;
import com.popo.zhihudailymvc.R;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import module.zhihudailymcv.MVPContract;
import module.zhihudailymcv.newsdetail.Presenter.NewsPresenter;


public class NewsView extends Fragment implements MVPContract.View{

    public NewsPresenter mNewsPresenter;
    public Activity mActivity;
    public AppCompatActivity appCompatActivity;
    public Toolbar mToolbar;
    public CollapsingToolbarLayout collapsingToolbarLayout;
    public ImageView newsImg;
    public WebView newsContentTextView;
    private int news_id;
    public FloatingActionButton shareButton;

    @Override
    public void initiate(MVPContract.Presenter presenter) {
        mNewsPresenter=(NewsPresenter) presenter;
    }

    public NewsView() {
        // Required empty public constructor
    }

    public static NewsView newInstance(String param1, String param2) {
        NewsView fragment = new NewsView();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_view, container, false);
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                mActivity=(Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        appCompatActivity=(AppCompatActivity)mActivity;
        Bundle bundle=this.getArguments();
        news_id=bundle.getInt("news_id");
        mToolbar=(Toolbar)view.findViewById(R.id.toolbar);
        appCompatActivity.setSupportActionBar(mToolbar);
        ActionBar actionBar=appCompatActivity.getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout=(CollapsingToolbarLayout)view.findViewById(R.id.collapsingtoolbar);
        newsImg=(ImageView)view.findViewById(R.id.news_dgi_image);
        newsContentTextView=(WebView)view.findViewById(R.id.news_dgi_content);
        mNewsPresenter.onGetNews(news_id);
        shareButton=(FloatingActionButton)view.findViewById(R.id.share);
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent=new Intent(getActivity(), MainActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
        }
        return true;
    }
}
