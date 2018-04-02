package module.zhihudailymcv.newsdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.popo.zhihudailymvc.R;

import module.zhihudailymcv.newsdetail.Model.NewsModel;
import module.zhihudailymcv.newsdetail.Presenter.NewsPresenter;
import module.zhihudailymcv.newsdetail.View.NewsView;

public class NewsActivity extends AppCompatActivity {
    private NewsView mNewsView=new NewsView();
    private NewsModel mNewsModel=new NewsModel();
    private NewsPresenter mNewsPresenter=new NewsPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Intent intent=getIntent();
        int news_id= intent.getIntExtra("news_id",0);

        mNewsView.initiate(mNewsPresenter);
        mNewsPresenter.initiate(mNewsModel,mNewsView);
        mNewsModel.initiate(mNewsPresenter);

        Bundle bundle=new Bundle();
        bundle.putInt("news_id",news_id);
        mNewsView.setArguments(bundle);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fm_news,mNewsView);
        fragmentTransaction.commit();
    }

}
