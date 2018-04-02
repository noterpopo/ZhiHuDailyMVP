package module.zhihudailymcv.newsdetail.Presenter;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.popo.zhihudailymvc.Bean.NewsItem;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import module.zhihudailymcv.MVPContract;
import module.zhihudailymcv.newsdetail.Model.NewsModel;
import module.zhihudailymcv.newsdetail.View.NewsView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lgp on 2018/3/30.
 */

public class NewsPresenter implements MVPContract.Presenter {
    private NewsModel mNewsModel;
    private NewsView mNewsView;
    @Override
    public void initiate(MVPContract.Model model, MVPContract.View view) {
        mNewsModel=(NewsModel)model;
        mNewsView=(NewsView)view;
    }

    public void onGetNews(int id){
        mNewsModel.getNews(id).subscribeOn(Schedulers.io())
                .map(new Function<NewsItem, NewsItem>() {
                    @Override
                    public NewsItem apply(NewsItem newsItem)  {
                        StringBuilder newsCss=new StringBuilder();
                        try {
                            for(int i=0;i<newsItem.getCss().size();++i){
                                OkHttpClient okHttpClient=new OkHttpClient();
                                Request request=new Request.Builder()
                                        .url(newsItem.getCss().get(i))
                                        .build();
                                Response response=okHttpClient.newCall(request).execute();
                                newsCss.append(response.body().string()+"\n");
                            }
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                        newsItem.setCssed(newsCss.toString());
                        return newsItem;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsItem>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("retrofit","Start news");
                    }

                    @Override
                    public void onNext(final NewsItem value) {
                       mNewsView.collapsingToolbarLayout.setTitle(value.getTitle());
                        Glide.with(mNewsView).load(value.getImage()).into(mNewsView.newsImg);
                        String newsContent=value.getBody().replaceAll("\\n","").replaceAll("\\r","").replaceAll("\\\\","").replaceAll("img-place-holder","");

                        String newsCssContent=value.getCssed().replaceAll("\\n","").replaceAll("\\r","").replaceAll("\\\\","");
                        String head = "<head>" +
                                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                                "<style>"+newsCssContent+"</style>" +
                                "</head>";
                        String htmlContent="<html>" + head + "<body>" + newsContent + "</body></html>";
                        mNewsView.newsContentTextView.loadData(htmlContent,"text/html; charset=UTF-8", null);
                        mNewsView.shareButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent shareIntent=
                                        ShareCompat.IntentBuilder.from(mNewsView.getActivity())
                                                .setType("text/plain")
                                                .setText(value.getShare_url())
                                                .getIntent();
                                if (shareIntent.resolveActivity(mNewsView.getActivity().getPackageManager()) != null){
                                    mNewsView.getActivity().startActivity(shareIntent);
                                }
                            }
                        });
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
