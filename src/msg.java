import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class msg {

    String mobiles=null;
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static String sendtext(String url) throws IOException  {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String responsecode = readAll(rd);
            return responsecode;
        } finally {
            is.close();
        }
    }

    public static void main(String[] args) throws IOException, JSONException {
        JSONObject json = readJsonFromUrl("https://sheets.googleapis.com/v4/spreadsheets/1H-J-rcSCGjZGvp9eNfSJnAjjir7GPwxhaj7eT9c-VjI/values/I2:I37/?key=AIzaSyAvAP8jlwyGbUvEoLcd0Mbrs0FN1wJBZhw");
        System.out.println(json.toString());
        String text = "this is team Codefoster";
       // sendtext("http://api.msg91.com/api/sendhttp.php?sender=CDFSTR&route=4&mobiles=9479803953&authkey=196196AvL5IOJ9Jz55a738e66&country=0&message="+text);
        //System.out.println(json1.toString());


        //Using Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Response response = objectMapper.readValue(json.toString(), Response.class);
            printParsedObject(response);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void printParsedObject(Response response) throws IOException {
        String no = response.getValues().get(1).toString();
        StringBuilder stringBuilder1 = new StringBuilder(no);
        stringBuilder1.deleteCharAt(0).deleteCharAt(10);
        String mobiles =stringBuilder1.toString().concat(",");
        //String mobiles =stringBuilder1.toString();

        //printPageInfo(page.getPageInfo());
    for (int i=2;i<36;i++) {
        String num = response.getValues().get(i).toString();
        StringBuilder stringBuilder = new StringBuilder(num);
        stringBuilder.deleteCharAt(0).deleteCharAt(10);
        //System.out.println(stringBuilder.toString());
        mobiles= mobiles.concat(stringBuilder.toString()).concat(",");


    }
    System.out.println(mobiles);
        String text = "This is a Reminder Message for the Version Controlling Session planned at Conference Room(DoIT).Date and time :03/02/2018 11:00 AM. Team Codefoster";
        sendtext("http://api.msg91.com/api/sendhttp.php?sender=CDFSTR&route=4&mobiles="+mobiles+"9479803953&authkey=196196AvL5IOJ9Jz55a738e66&country=0&message="+text);
        //printPosts(page.getPosts());
    }
}
