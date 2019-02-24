package com.jianyiclub.jianzhi.entity;

import java.util.List;

/**
 * Created by wl624 on 2017/10/17.
 */

public class MyNews {
    /**
     * date : 20171017
     * stories : [{"images":["https://pic3.zhimg.com/v2-d09a9f9df8c9ea7d1e5dd520c0999fe6.jpg"],"type":0,"id":9652477,"ga_prefix":"101719","title":"新人进娱乐圈签的合约，其实就是一张「卖身契」"},{"images":["https://pic4.zhimg.com/v2-762a189d1ecfedee12a39f19a178eceb.jpg"],"type":0,"id":9652580,"ga_prefix":"101718","title":"接受不了专业付费咨询，却对不负责的免费回答赞不绝口是为什么？"},{"images":["https://pic4.zhimg.com/v2-5197e86b304bc25019ae4187fe2ace37.jpg"],"type":0,"id":9652363,"ga_prefix":"101717","title":"今年双十一前，几家快递公司选择此时涨价"},{"images":["https://pic1.zhimg.com/v2-3a20fb05cbfabb30e1731a3da82798ec.jpg"],"type":0,"id":9652342,"ga_prefix":"101716","title":"为什么减肥失败？不怪你，是减肥时大脑老想着耍点小脾气"},{"images":["https://pic3.zhimg.com/v2-befc9bf0941f653f1a46db7721cb552e.jpg"],"type":0,"id":9652474,"ga_prefix":"101715","title":"引力波提前两年震惊世界，这两台中国望远镜帮了大忙"},{"images":["https://pic1.zhimg.com/v2-62bdf87dc42501abbef8508de050eb4c.jpg"],"type":0,"id":9652379,"ga_prefix":"101714","title":"继美团小米之后陷进了校招坑，说「就业歧视」苏宁冤不冤？"},{"images":["https://pic4.zhimg.com/v2-71887025c6d00a77f88c45ebb88589bf.jpg"],"type":0,"id":9652549,"ga_prefix":"101712","title":"WPA2 被破解，黑客可以通过 Wi-Fi  窃听任何连网设备，我们该怎么做？"},{"images":["https://pic2.zhimg.com/v2-fa1e344037e7cc05463ea3b9ef9de9a9.jpg"],"type":0,"id":9651470,"ga_prefix":"101712","title":"大误 · 换了个新影子后"},{"images":["https://pic3.zhimg.com/v2-5718db335cf036539b3c66109886ff32.jpg"],"type":0,"id":9652284,"ga_prefix":"101711","title":"家里的床真的要比酒店的床干净吗？"},{"images":["https://pic1.zhimg.com/v2-ed4ac52f52b7490baff877e5982eec44.jpg"],"type":0,"id":9651834,"ga_prefix":"101710","title":"他们用 10 年跟拍 5 个孩子，反差巨大，值得所有父母深思"},{"images":["https://pic2.zhimg.com/v2-4d8d94c78af87c4613373ea1a9e27ef9.jpg"],"type":0,"id":9652370,"ga_prefix":"101709","title":"这轮消费升级中，说说我看到的几个有创业前景的细分领域"},{"images":["https://pic1.zhimg.com/v2-af9b131fafe95f7d7e6beacc1f375280.jpg"],"type":0,"id":9651922,"ga_prefix":"101708","title":"《天才枪手》火了泰国广告，泰国人望向电视机，尴尬地笑了"},{"images":["https://pic4.zhimg.com/v2-e03b68473dcff666ebffd5959698a8a7.jpg"],"type":0,"id":9651786,"ga_prefix":"101707","title":"为什么国家不叫「国」，而是叫「国家」？"},{"images":["https://pic1.zhimg.com/v2-4d6cf2349a97f9a4dbad6f5b7e926f54.jpg"],"type":0,"id":9652382,"ga_prefix":"101707","title":"LIGO 观测到双中子星合并，面对浩瀚宇宙，人类终于耳聪目明了"},{"images":["https://pic1.zhimg.com/v2-2e96c571207e548aa802634dcdb29e34.jpg"],"type":0,"id":9652259,"ga_prefix":"101707","title":"财务出身的人，有哪些普通人没有的思维习惯？"},{"images":["https://pic1.zhimg.com/v2-1da7ad8ca17ca74d4c334b0fab9508b8.jpg"],"type":0,"id":9652281,"ga_prefix":"101706","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic3.zhimg.com/v2-5e0d3e230ee2dcae9e8aedf039a91176.jpg","type":0,"id":9652363,"ga_prefix":"101717","title":"今年双十一前，几家快递公司选择此时涨价"},{"image":"https://pic4.zhimg.com/v2-33e8ff2613152cf9042be1fd5084ea53.jpg","type":0,"id":9651834,"ga_prefix":"101710","title":"他们用 10 年跟拍 5 个孩子，反差巨大，值得所有父母深思"},{"image":"https://pic3.zhimg.com/v2-14549978faa19e1c9fd312831c161d0e.jpg","type":0,"id":9652379,"ga_prefix":"101714","title":"继美团小米之后陷进了校招坑，说「就业歧视」苏宁冤不冤？"},{"image":"https://pic3.zhimg.com/v2-4aae9ffcd7287fdfca7c87e580dff80a.jpg","type":0,"id":9652549,"ga_prefix":"101712","title":"WPA2 被破解，黑客可以通过 Wi-Fi  窃听任何连网设备，我们该怎么做？"},{"image":"https://pic1.zhimg.com/v2-5e87affad27041606b60d6d0d628ec1c.jpg","type":0,"id":9652342,"ga_prefix":"101716","title":"为什么减肥失败？不怪你，是减肥时大脑老想着耍点小脾气"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic3.zhimg.com/v2-d09a9f9df8c9ea7d1e5dd520c0999fe6.jpg"]
         * type : 0
         * id : 9652477
         * ga_prefix : 101719
         * title : 新人进娱乐圈签的合约，其实就是一张「卖身契」
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private String[] images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String[] getImages() {
            return images;
        }

        public void setImages(String[] images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic3.zhimg.com/v2-5e0d3e230ee2dcae9e8aedf039a91176.jpg
         * type : 0
         * id : 9652363
         * ga_prefix : 101717
         * title : 今年双十一前，几家快递公司选择此时涨价
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
    public String toString(){
        return getDate();
    }
}
