package module.zhihudailymcv.newsdetail.Model;

import android.util.Log;

import com.popo.zhihudailymvc.ApiService;
import com.popo.zhihudailymvc.Bean.NewsItem;
import com.popo.zhihudailymvc.RetrofitFactory;

import io.reactivex.Observable;
import module.zhihudailymcv.MVPContract;
import module.zhihudailymcv.newsdetail.Presenter.NewsPresenter;

/**
 * Created by lgp on 2018/3/30.
 */

public class NewsModel implements MVPContract.Model {
    private ApiService mApiService;
    private NewsPresenter mNewsPresenter;
    @Override
    public void initiate(MVPContract.Presenter presenter) {
        mApiService= RetrofitFactory.getAPIService();
        mNewsPresenter=(NewsPresenter)presenter;
    }

    public Observable<NewsItem> getNews(int id){
        Observable<NewsItem> itemObservable=mApiService.getNews(id+"");
        Log.d("ddd","sss");
        return itemObservable;
    }
}
