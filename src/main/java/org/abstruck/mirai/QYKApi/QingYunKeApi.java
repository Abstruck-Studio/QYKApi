package org.abstruck.mirai.QYKApi;

import com.google.gson.Gson;
import java.util.HashMap;

public class QingYunKeApi {
    //使用来自青云客的api
    public static final String QINGYUNKE_API ="http://api.qingyunke.com/api.php";
    public static String getDialogue(String request){
        HashMap<String,String> param = new HashMap<>();
        param.put("key","free");
        param.put("appid","0");
        param.put("msg",request);
        String json = HttpUtil.doGet(QINGYUNKE_API,param);
        System.out.println(json);
        QYKData data = new Gson().fromJson(json,QYKData.class);
        if (data.result!=0) return "API出错";
        return data.content;
    }

    static class QYKData {
        int result;
        String content;
    }
}
