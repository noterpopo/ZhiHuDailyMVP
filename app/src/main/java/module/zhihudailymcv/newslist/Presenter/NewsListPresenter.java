package module.zhihudailymcv.newslist.Presenter;

import android.util.Log;
import android.widget.Toast;

import com.popo.zhihudailymvc.Bean.DateNews;
import com.popo.zhihudailymvc.Bean.News;

import java.util.Date;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import module.zhihudailymcv.MVPContract;
import module.zhihudailymcv.newslist.Model.NewsListModelImpl;
import module.zhihudailymcv.newslist.View.NewsListViewImpl;

/**
 * Created by lgp on 2018/3/29.
 */

public class NewsListPresenter implements MVPContract.Presenter{
    private NewsListViewImpl mNewsListView;
    private NewsListModelImpl mNewsListModel;

    @Override
    public void initiate(MVPContract.Model model, MVPContract.View view) {
        mNewsListModel=(NewsListModelImpl)model;
        mNewsListView=(NewsListViewImpl)view;
    }

    public void onRefresh(Date date){
        getDateNews(date);

    }
    public News onGetNews(int pos){
        return mNewsListModel.getNews(pos);
    }
    public int onGetListNum()
    {
        return mNewsListModel.getListNum();
    }
    private void getDateNews(Date date){
        mNewsListModel.getDateNews(date).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DateNews>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("retrofit","Start connect");
                    }

                    @Override
                    public void onNext(DateNews value) {
                        Toast.makeText(mNewsListView.getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
                        mNewsListView.mSwipeRefreshLayout.setRefreshing(false);
                        mNewsListModel.newsList.clear();
                        for(int i=0;i<value.getStories().size();++i){
                            mNewsListModel.newsList.add(new News(value.getStories().get(i).getImages().get(0),value.getStories().get(i).getTitle(),value.getStories().get(i).getId()));
                        }
                        mNewsListView.adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
