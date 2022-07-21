package utils;


import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    private static ObjectMapper mapper;//bunu codehouse dan çekecegiz buna dikkat

    static {
        mapper = new ObjectMapper();//statıc blok ilk once çalışır
    }

        //1. Method: Json Datasını Java Objesine çevirir.(De-Serialization)

    public static <T> T convertJsonToJavaObject(String json, Class<T> cls){//Generic Method//jsonı javaya cevir
        //object kullanmıyoruz bana ne gelirse ona göre çalışsın kendi hali ile bana dönsün istiyorum o yuzden Class<t>
        //KULLANIYORUZ ne girersem onu verir
        T javaResult = null;

        try {
            javaResult = mapper.readValue(json,cls);//degeri okur ve onun data tipinde bir value ver
            //ya okuyamazsam diyor bende run tıme exceptıon atıyorum
        } catch (IOException e) {
            e.printStackTrace();
        }

        return javaResult;
    }

        //2. Method: Java Objesini Json Dataya  çevirir.(Serialization)

    public static String convertJavaObjectToJson(Object obj){

        String jsonResult = null;

        try {
            jsonResult = mapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonResult;
    }


}


