package test_data;

import pojos.DummyDataPojo;

import java.util.HashMap;
import java.util.Map;

public class DummyTestData {
    public Map<String, Object> dummyDatesSetUp(String employee_name, Integer employee_salary,Integer employee_age,String profile_image){

        Map<String, Object> dummyDatesMap = new HashMap<>();
        dummyDatesMap.put("employee_name", employee_name);
        dummyDatesMap.put("employee_salary", employee_salary);
        dummyDatesMap.put("employee_age", employee_age);
        dummyDatesMap.put("profile_image", profile_image);


        return dummyDatesMap;

    }

    public Map<String,Object> expectedDataSetUp(String status, Map<String,Object> data, String message){
        Map<String, Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("status",status);
        expectedDataMap.put("data",data);
        expectedDataMap.put("message",message);

        return  expectedDataMap;
    }

    public String expectedDataInString(String status,String employee_name,int employee_salary,int employee_age,String profile_image,String message){
        String expectedData=  "{\n" +
                "    \"status\": \""+status+"\",\n" +
                "    \"data\": {\n" +
                "        \"employee_name\": \""+employee_name+"\",\n" +
                "        \"employee_salary\": "+employee_salary+",\n" +
                "        \"employee_age\": "+employee_age+",\n" +
                "        \"profile_image\": \""+profile_image+"\"\n" +
                "    },\n" +
                "    \"message\": \""+message+"\"\n" +
                "}";

        return expectedData;

    }
}
