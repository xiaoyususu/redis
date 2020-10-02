package post;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName TestPostClient
 * @Description TODO
 * @Author boy
 * @Date 2019/11/9 2:39 PM
 */

//1.写一个利用HttpClient发送post请求的类
public class RequestClientInterface {
    public static String send(String url, Map<String, String> map, String encoding) throws ParseException, IOException {
        String body = "";

        //创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        //设置参数到请求对象中，这里使用new StringEntity()更方便，因为可传入的数据类型更多
        httpPost.setEntity(new StringEntity(Map2Json(map), encoding));

        System.out.println("请求地址：" + url);
        System.out.println("请求参数：" + Map2Json(map));

        //设置header信息
        //指定报文头和相关数据格式
        httpPost.setHeader("Content-type", "application/json");
        // httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpPost);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, encoding);
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        return body;
    }

    //这里使用Google的Gson把map类型转为json字符串比较简单
    public static String Map2Json(Map<String, String> map) {
        JSON json = (JSON) JSON.toJSON(map);

        return json.toJSONString();
    }
//说明：此demo用到的相关jar包可在本人上传的TestPost.jar压缩文件中下载使用。

    public static void main(String[] args) throws Exception{
        String url = "http://localhost:5000/hello-service/pay";
        Map<String,String> map = new HashMap<>();
        map.put("token","1111");
        String encoding = "UTF-8";
        send(url,map,encoding);
    }
}