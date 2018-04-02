package com.popo.zhihudailymvc.Bean;

import java.util.List;

/**
 * Created by popo on 2018/3/10.
 */

public class NewsItem {
    public String body;
    public String title;
    public String image;
    public List<String> css;
    public String share_url;
    public String cssed;

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public List<String> getCss() {
        return css;
    }

    public String getShare_url() {
        return share_url;
    }

    public String getCssed() {
        return cssed;
    }

    public void setCssed(String cssed) {
        this.cssed = cssed;
    }
}