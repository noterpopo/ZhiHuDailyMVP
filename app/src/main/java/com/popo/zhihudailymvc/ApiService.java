package com.popo.zhihudailymvc;

import com.popo.zhihudailymvc.Bean.DateNews;
import com.popo.zhihudailymvc.Bean.NewsItem;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lgp on 2018/3/30.
 */

public interface ApiService {
    String BASEURL="https://news-at.zhihu.com/api/4/";

    @GET("news/before/{date}")
    Observable<DateNews> getDateNews(@Path("date") String date);

    @GET("news/{id}")
    Observable<NewsItem> getNews(@Path("id") String id);
}
