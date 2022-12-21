package HttpClinent;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

public class DoPOST {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建post请求
        HttpPost httpPost = new HttpPost("http://www.oschina.net/");
        //伪装浏览器请求
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        CloseableHttpResponse response = null;
        try {
            //执行发送请求
            response = httpClient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(content);
                //内容写入文件
                FileUtils.writeStringToFile(new File("E:\\devtest\\oschina.html"), content, "UTF-8");
                System.out.println("内容长度：" + content.length());
            }
        } finally {
            if (response != null) {
                response.close();
            }
            httpClient.close();
        }
    }
}
