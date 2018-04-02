package module.zhihudailymcv.newslist.View;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.popo.zhihudailymvc.R;

import java.util.Calendar;
import java.util.Date;

import module.zhihudailymcv.MVPContract;
import module.zhihudailymcv.newslist.NewsAdapter;
import module.zhihudailymcv.newslist.Presenter.NewsListPresenter;

public class NewsListViewImpl extends Fragment implements MVPContract.View{
    public NewsListPresenter mNewsListPresenter;
    public AppCompatActivity appCompatActivity;
    public Activity mActivity;
    public Toolbar mToolbar;
    public RecyclerView mRecyclerView;
    public SwipeRefreshLayout mSwipeRefreshLayout;
    public FloatingActionButton mFloatingActionButton;
    public NewsAdapter adapter;
    public Date mDate=null;

    @Override
    public void initiate(MVPContract.Presenter presenter) {
        mNewsListPresenter=(NewsListPresenter) presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                mActivity=(Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        appCompatActivity=(AppCompatActivity)mActivity;
        mToolbar=(Toolbar)view.findViewById(R.id.mtoolbar);
        appCompatActivity.setSupportActionBar(mToolbar);
        mSwipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(mDate==null){
                    mDate=new Date(System.currentTimeMillis());
                }
                mNewsListPresenter.onRefresh(mDate);
            }
        });
        mFloatingActionButton=(FloatingActionButton)view.findViewById(R.id.refresh);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDate=new Date(System.currentTimeMillis());
                mNewsListPresenter.onRefresh(mDate);
            }
        });
        mRecyclerView=(RecyclerView)view.findViewById(R.id.content_rec);
        GridLayoutManager layoutManager=new GridLayoutManager(context,1);
        mRecyclerView.setLayoutManager(layoutManager);
        Date date=new Date(System.currentTimeMillis());
        mNewsListPresenter.onRefresh(date);
        adapter=new NewsAdapter(mNewsListPresenter);
        mRecyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.data_choose:
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        mDate=new Date(i-1900,i1,i2);
                        mSwipeRefreshLayout.setRefreshing(true);
                        mNewsListPresenter.onRefresh(mDate);
                    }
                },year,month,day);
                datePickerDialog.show();
                break;
            case R.id.setting:
                break;
            case R.id.about:
                break;
            default:
                break;
        }
        return true;
    }
}
