package module.zhihudailymcv.newslist.Model;

import android.support.annotation.NonNull;

import com.popo.zhihudailymvc.ApiService;
import com.popo.zhihudailymvc.Bean.DateNews;
import com.popo.zhihudailymvc.Bean.News;
import com.popo.zhihudailymvc.RetrofitFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import module.zhihudailymcv.MVPContract;
import module.zhihudailymcv.newslist.Presenter.NewsListPresenter;

/**
 * Created by lgp on 2018/3/27.
 */

public class NewsListModelImpl implements MVPContract.Model{
    private ApiService mApiService;
    private NewsListPresenter mNewsListPresenter;
    public List<News> newsList;

    @Override
    public void initiate(MVPContract.Presenter presenter) {
        newsList=new ArrayList<>();
        mApiService= RetrofitFactory.getAPIService();
        mNewsListPresenter=(NewsListPresenter)presenter;
    }

    public News getNews(int pos)
    {
        return newsList.get(pos);
    }
    public int getListNum()
    {
        return newsList.size();
    }
    public Observable<DateNews> getDateNews(Date date){

        return mApiService.getDateNews(getDateUrl(date));
    }

    @NonNull
    private String getDateUrl(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try{
            date  =  new Date(date.getTime()+24*3600*1000);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return simpleDateFormat.format(date).toString();
    }

}
