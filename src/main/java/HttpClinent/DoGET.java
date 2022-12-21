package HttpClinent;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

public class DoGET {
    public static void main(String[] args) throws IOException {
        //创建Httpclient请求
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建http GET请求
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        CloseableHttpResponse response = null;
        try {
            //执行请求
            response = httpClient.execute(httpGet);
            //判断返回状态码是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                //请求具体能容
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(content);
                //内容写入文件
                FileUtils.writeStringToFile(new File("E:\\devtest\\baidu.html"), content, "UTF-8");
                System.out.println("工作内容长度：" + content.length());
            }
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (response != null) {
                response.close();
            }
            httpClient.close();
        }
    }

}
