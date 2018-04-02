package module.zhihudailymcv.newslist;

/**
 * Created by lgp on 2018/3/29.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.popo.zhihudailymvc.Bean.News;
import com.popo.zhihudailymvc.R;

import module.zhihudailymcv.newsdetail.NewsActivity;
import module.zhihudailymcv.newslist.Presenter.NewsListPresenter;

/**
 * Created by popo on 2018/3/10.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context context;
    private NewsListPresenter mNewsListPresenter;

    public NewsAdapter(NewsListPresenter newsListPresenter) {
        mNewsListPresenter = newsListPresenter;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView news_image;
        TextView news_title;

        public ViewHolder(View view){
            super(view);
            cardView=(CardView)view;
            news_image=(ImageView)view.findViewById(R.id.news_image);
            news_title=(TextView)view.findViewById(R.id.news_title);
        }

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(context==null){
            context=parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.news_item,parent,false);
        final ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=viewHolder.getAdapterPosition();
                News news=mNewsListPresenter.onGetNews(position);
                Intent intent=new Intent(context,NewsActivity.class);
                intent.putExtra("news_id",news.getId());
                //intent.putExtra("news_content",news.getContent());
               // intent.putExtra("news_image_url",news.getImageUrl());
               // intent.putExtra("news_css",news.getCss());
               // intent.putExtra("news_share",news.getShare_url());
               context.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        News news=mNewsListPresenter.onGetNews(position);
        holder.news_title.setText(news.getTitle());
        Glide.with(context).load(news.getImageUrl()).into(holder.news_image);
    }

    @Override
    public int getItemCount() {
        return mNewsListPresenter.onGetListNum();
    }
}
