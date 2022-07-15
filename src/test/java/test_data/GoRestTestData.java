package test_data;

import java.util.HashMap;
import java.util.Map;

public class GoRestTestData {
    public Map<String,String>DataKeyMap(String name, String email,String gender,String status){
        Map<String,String>dataKeyMap = new HashMap<>();//inner map
        dataKeyMap.put("name",name);
        dataKeyMap.put("email",email);
        dataKeyMap.put("gender",gender);
        dataKeyMap.put("status",status);

        return dataKeyMap;
    }
    public Map<String,Object>expectedDataMap(Object meta,Map<String,String> data){
        Map<String,Object>expectedData = new HashMap<>();//dış map
        expectedData.put("meta", meta);
        expectedData.put("data", data);

        return expectedData;
    }
}
