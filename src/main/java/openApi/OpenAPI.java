package openApi;

import java.io.IOException;
import java.net.URLEncoder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class OpenAPI {


    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();
        StringBuilder urlBuilder = new StringBuilder(
          "http://openapi.jeonju.go.kr/rest/wifizone/getWifiList"); /*URL*/
        urlBuilder.append("?").append(URLEncoder.encode("serviceKey", "UTF-8")).append("=")
                  .append(
                    "%2BKWJ7Ig2kpzGyjXyXNi4paYsEhrlcUaSB2gEW0slUsweLN%2FygODs7k5ZqA6NLHp3bwhAfHV7KcbZr1k9zSo6ng%3D%3D"
                  ); /*Service Key*/
//        urlBuilder.append("&").append(URLEncoder.encode("authApiKey", "UTF-8")).append("=")
//                  .append(
//                    "%2BKWJ7Ig2kpzGyjXyXNi4paYsEhrlcUaSB2gEW0slUsweLN%2FygODs7k5ZqA6NLHp3bwhAfHV7KcbZr1k9zSo6ng%3D%3D"
//                  ); /*인증키*/
        urlBuilder.append("&").append(URLEncoder.encode("pageNo", "UTF-8")).append("=")
                  .append(URLEncoder.encode("1", "UTF-8")); /*시작페이지*/
        urlBuilder.append("&").append(URLEncoder.encode("numOfRows", "UTF-8")).append("=")
                  .append(URLEncoder.encode("50", "UTF-8")); /*페이지크기*/

        System.out.println("urlBuilder = " + urlBuilder);

        Request request = new Request.Builder().url(urlBuilder.toString()).build();

        try {
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (body != null) {
                    String jsonData = body.string();
                    System.out.println("JSON Data: " + jsonData);
                }
            } else {
                System.out.println("Error: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
