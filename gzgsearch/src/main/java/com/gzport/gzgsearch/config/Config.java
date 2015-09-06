package com.gzport.gzgsearch.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/4.
 */
public class Config {
    public static final String HOST = "http://10.0.2.2:7001/";
    public static final String KEY = "2cebada8-aaec-11e2-b76a-20c9d084d52d";

    public static final String[] comStrArray = {"集团", "黄埔", "新港", "新沙",
            "石油化工",
            "粮食码头",
            "西基",
            "NCT",
            "船务公司",
            "港发",
            "小虎码头",
            "建滔",
            "GOCT",
            "河南公司",
            "南沙码头",
            "GCT"};

    public static final String[] activityArray = {"集团", "黄埔", "新港", "新沙", "石油化工", "西基", "粮食码头"};

    public static Map activityComMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put(activityArray[0], 5);
        map.put(activityArray[1], 2);
        map.put(activityArray[2], 3);
        map.put(activityArray[3], 1);
        map.put(activityArray[4], 6);
        map.put(activityArray[5], 4);
        map.put(activityArray[6], 16);
        return map;

    }

    public static Map getComMap() {
        Map<String, String> map = new HashMap<>();
        map.put(comStrArray[0], "5");
        map.put(comStrArray[1], "2");
        map.put(comStrArray[2], "3");
        map.put(comStrArray[3], "1");
        map.put(comStrArray[4], "6");
        map.put(comStrArray[5], "16");
        map.put(comStrArray[6], "4");
        map.put(comStrArray[7], "9");
        map.put(comStrArray[8], "11");
        map.put(comStrArray[9], "12");
        map.put(comStrArray[10], "13");
        map.put(comStrArray[11], "14");
        map.put(comStrArray[12], "15");
        map.put(comStrArray[13], "17");
        map.put(comStrArray[14], "18");
        map.put(comStrArray[15], "19");
        return map;
    }

}
