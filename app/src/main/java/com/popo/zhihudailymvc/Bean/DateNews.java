package com.popo.zhihudailymvc.Bean;

/**
 * Created by lgp on 2018/3/29.
 */

import java.util.List;

public class DateNews {
    public List<sto> stories;
    public static class sto{
        public int id;
        public String title;
        public List<String> images;

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public List<String> getImages() {
            return images;
        }
    }

    public List<sto> getStories() {
        return stories;
    }

}