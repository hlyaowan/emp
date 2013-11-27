package com.quartz.monitor.common;

public class ThreadConstant {
    /***
     * 每天16小时，5.76 wS
            频道 600W 22.36W  = 3.88次/s
            分栏 500W 18.46W  =3.2次/s
            分栏内容 350W 13W =2.26次/s
            内容详情 200W 7.6W =1.32次/s
            章节列表 200W 7.6W =1.32次/s
            排行榜单 300W 11.2W =2次/s
            用户识别 2600W 96.6W =16.77次/s
            总计 4750W 135.9W 
     */
    public final static int CHANNELS_THREAD = 4;
    public final static int CATALOGLIST_THREAD = 3;
    public final static int CATALOGCONTENT_THREAD = 2;
    public final static int CONTENTINFO_THREAD = 1;
    public final static int CHAPTERLIST_THREAD = 1;
    public final static int AUTHORINFO_THREAD = 1;
    public final static int RANK_THREAD = 1;
    public final static int RANK_TYPE_THREAD = 1;
    public final static int RECOMMEND_THREAD = 2;
    public final static int RECOMMENDTYPE_THREAD = 1;
    public final static int HOTRECOMMEND_THREAD = 1;
    public final static int IDENTIFYCNET_THREAD = 16;
    
    public final static int CHANNELS_MAX_LIMITED = 223600;
    public final static int CATALOGLIST_MAX_LIMITED = 184600;
    public final static int CATALOGCONTENT_MAX_LIMITED = 130000;
    public final static int CONTENTINFO_MAX_LIMITED = 57000;
    public final static int CHAPTERLIST_MAX_LIMITED =57000;
    public final static int AUTHORINFO_MAX_LIMITED = 57000;
    public final static int RANK_MAX_LIMITED = 22400;
    public final static int RANK_TYPE_MAX_LIMITED = 22400;
    public final static int RECOMMEND_MAX_LIMITED = 22400;
    public final static int RECOMMENDTYPE_MAX_LIMITED= 22400;
    public final static int HOTRECOMMEND_MAX_LIMITED= 22400;
    public final static int IDENTIFYCNET_MAX_LIMITED = 167700;
    
    public final static int SLEEPTIME =4000;
}
