package com.popo.zhihudailymvc.Bean;


/**
 * Created by popo on 2018/3/10.
 */

public class News {
    private String imageUrl;
    private String content;
    private String title;
    private String css;
    private String share_url;
    private int id;

    public News(String imageUrl, String title,int id) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.id=id;
    }

    public News(String imageUrl, String content, String title, String css, String share_url) {
        this.imageUrl = imageUrl;
        this.content = content;
        this.title = title;
        this.css = css;
        this.share_url = share_url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getCss() {
        return css;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCss(String css) {
        this.css = css;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public int getId() {
        return id;
    }
}