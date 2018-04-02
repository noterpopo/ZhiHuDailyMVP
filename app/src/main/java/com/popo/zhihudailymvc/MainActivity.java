package com.popo.zhihudailymvc;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import module.zhihudailymcv.newslist.Model.NewsListModelImpl;
import module.zhihudailymcv.newslist.Presenter.NewsListPresenter;
import module.zhihudailymcv.newslist.View.NewsListViewImpl;

public class MainActivity extends AppCompatActivity {
    private NewsListPresenter mNewsListPresenter=new NewsListPresenter();
    private NewsListViewImpl mNewsListView=new NewsListViewImpl();
    private NewsListModelImpl mNewsListModel=new NewsListModelImpl();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mNewsListPresenter=new NewsListPresenter();
        mNewsListView=new NewsListViewImpl();
        mNewsListModel=new NewsListModelImpl();
        super.onCreate(savedInstanceState);
        this.getWindow().getDecorView().setBackground(null);
        setContentView(R.layout.activity_main);

        mNewsListView.initiate(mNewsListPresenter);
        mNewsListPresenter.initiate(mNewsListModel,mNewsListView);
        mNewsListModel.initiate(mNewsListPresenter);

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fm_newslist,mNewsListView);
        fragmentTransaction.commit();

    }
}
