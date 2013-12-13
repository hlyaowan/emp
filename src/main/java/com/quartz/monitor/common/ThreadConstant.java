package com.quartz.monitor.common;

public class ThreadConstant {
    /***
     * 每天16小时，5.76 wS
            频道 600W 25.8W  = 3.88次/s
            分栏 500W 21.3W  =3.2次/s
            分栏内容 350W 15W =2.26次/s
            内容详情 200W 8.7W =1.32次/s
            章节列表 200W 8.7W =1.32次/s
            排行榜单 300W 12.9W =2次/s
            用户识别 2600W 111.45W =16.77次/s
            总计 4750W 202.5.9W 
     */
    public final static int CHANNELS_THREAD = 6;
    public final static int CATALOGLIST_THREAD = 7;
    public final static int CATALOGCONTENT_THREAD = 5;
    public final static int CONTENTINFO_THREAD = 4;
    public final static int CHAPTERLIST_THREAD = 2;
    public final static int AUTHORINFO_THREAD = 2;
    public final static int RANK_THREAD = 2;
    public final static int RANK_TYPE_THREAD = 2;
    public final static int RECOMMEND_THREAD = 2;
    public final static int RECOMMENDTYPE_THREAD =2;
    public final static int HOTRECOMMEND_THREAD = 2;
    public final static int IDENTIFYCNET_THREAD = 16;
    
    public final static int CHANNELS_MAX_LIMITED = 273600;
    public final static int CATALOGLIST_MAX_LIMITED = 254600;
    public final static int CATALOGCONTENT_MAX_LIMITED = 200000;
    public final static int CONTENTINFO_MAX_LIMITED = 120000;
    public final static int CHAPTERLIST_MAX_LIMITED =120000;
    public final static int AUTHORINFO_MAX_LIMITED = 90000;
    public final static int RANK_MAX_LIMITED = 80000;
    public final static int RANK_TYPE_MAX_LIMITED = 80000;
    public final static int RECOMMEND_MAX_LIMITED = 80000;
    public final static int RECOMMENDTYPE_MAX_LIMITED= 80000;
    public final static int HOTRECOMMEND_MAX_LIMITED= 80000;
    public final static int IDENTIFYCNET_MAX_LIMITED = 1114500;
    
    public final static int SLEEPTIME =4000;
}
