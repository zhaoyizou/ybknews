package com.visionet.core.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Sets;
import com.visionet.core.util.security.Md5Utils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

public class HttpClient {
    private static final Logger log = LoggerFactory.getLogger(HttpClient.class);
    public static final String SQYC_CHANNEL = "sqyc_001";
    public static final String DZCX_CHANNEL = "dzcx";
    public static final String SQYC_KEY = "sqycKey";
    public static final String SQYC_KEY_VALUE = ResourceUtil.getValueBykey("base", "sqyc.key");
    public static final String DZCX_KEY = "dzcxKey";
    public static final String DZCX_KEY_VALUE = "dzcx5806ce1c44c623373c055720cb6fd5a6";
    /**
     * 增加第三方加密
     *
     * @param url
     * @param params
     * @return
     */
    public static String postEncrypt(String url, Map<String, String> params) throws Exception {
        if (params == null || params.isEmpty()) {
            return "";
        }
        // TODO: 2016/11/22 14:37  生成签名
        getSign(params);
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String body;
        log.info("create httppost:" + url);
        HttpPost post = postForm(url, params);

        body = invoke(httpclient, post);

        httpclient.getConnectionManager().shutdown();

        return body;
    }

    /**
     * 增加第三方加密
     *
     * @param url
     * @param params
     * @return
     */
    public static String postEncryptAddOrder(String url, Map<String, String> params) throws Exception {
        if (params == null || params.isEmpty()) {
            return "";
        }
        // TODO: 2016/11/22 14:37  生成签名
        getSignAddOrder(params);
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String body;
        log.info("create httppost:" + url);
        HttpPost post = postForm(url, params);

        body = invoke(httpclient, post);

        httpclient.getConnectionManager().shutdown();

        return body;
    }
    

    /**
     * 计算签名
     *
     * @param params
     * @throws UnsupportedEncodingException
     */
    private static void getSign(Map<String, String> params) throws UnsupportedEncodingException {
        Set<String> keySet = params.keySet();
        TreeSet<String> setSort = Sets.newTreeSet(keySet);
        StringBuilder sb = new StringBuilder();
        for (String key : setSort) {
            String value = params.get(key);
            if (StringUtils.isBlank(value)) {
                continue;
            }
            params.put(key, URLEncoder.encode(value, HTTP.UTF_8));
            sb.append(key + "=" + URLEncoder.encode(value, HTTP.UTF_8) + "&");
        }
        sb.append(SQYC_KEY + "=" + SQYC_KEY_VALUE);
        //sb.append(DZCX_KEY + "=" + DZCX_KEY_VALUE);
        log.info("签名字符串："+sb.toString());
        String sign = Md5Utils.encode(sb.toString());
        log.info("sign："+sign);
        params.put("sign", sign);
    }

    /**
     * 计算签名
     *
     * @param params
     * @throws UnsupportedEncodingException
     */
    private static void getSignAddOrder(Map<String, String> params) throws UnsupportedEncodingException {
        Set<String> keySet = params.keySet();
        TreeSet<String> setSort = Sets.newTreeSet(keySet);
        StringBuilder sb = new StringBuilder();
        for (String key : setSort) {
            String value = params.get(key);
            if (StringUtils.isBlank(value)) {
                continue;
            }
            if ("groupIds".equals(key)) {
                params.put(key, value);
                sb.append(key + "=" + value + "&");
            }else if ("reason".equals(key)){
                params.put(key, value);
                sb.append(key + "=" + value + "&");
            }else{
                params.put(key, URLEncoder.encode(value, HTTP.UTF_8));
                sb.append(key + "=" + URLEncoder.encode(value, HTTP.UTF_8) + "&");
            }
        }
        sb.append(SQYC_KEY + "=" + SQYC_KEY_VALUE);
        //sb.append(DZCX_KEY + "=" + DZCX_KEY_VALUE);
        System.out.println("签名字符串："+sb.toString());
        String sign = Md5Utils.encode(sb.toString());
        System.out.println("sign："+sign);
        params.put("sign", sign);
    }

    public static String post(String url, Map<String, String> params) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String body = null;

        log.info("create httppost:" + url);
        HttpPost post = postForm(url, params);

        body = invoke(httpclient, post);

        httpclient.getConnectionManager().shutdown();

        return body;
    }

    public static String post(String url, String json) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String body = null;
        HttpPost post;
        post = postJSON(url, json);
        body = invoke(httpclient, post);

        httpclient.getConnectionManager().shutdown();

        return body;
    }
    
    public static String post(String url, String json,HashMap<String, String> head) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String body = null;
        HttpPost post;
        post = postJSON(url, json,head);
        body = invoke(httpclient, post);

        httpclient.getConnectionManager().shutdown();

        return body;
    }

    public static String get(String url) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String body = null;
        // log.info("create httppost:" + url);
        HttpGet get = new HttpGet(url);
        try {
            body = URLDecoder.decode(invoke(httpclient, get), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        httpclient.getConnectionManager().shutdown();

        return body;
    }

    private static String invoke(DefaultHttpClient httpclient, HttpUriRequest httpost) {
        HttpResponse response = sendRequest(httpclient, httpost);
        String body = paseResponse(response);
        return body;
    }

    private static String paseResponse(HttpResponse response) {
        // log.info("get response from http server.." + response );
        HttpEntity entity = response.getEntity();
        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        try {
            body = EntityUtils.toString(entity, charset);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return body;
    }

    private static HttpResponse sendRequest(DefaultHttpClient httpclient, HttpUriRequest httpost) {
//		log.info("execute post...");
        HttpResponse response = null;

        try {
            response = httpclient.execute(httpost);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private static HttpPost postForm(String url, Map<String, String> params) {

        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key)));
        }

        try {
            // log.info("set utf-8 form entity to httppost");
            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return httpost;
    }

    private static HttpPost postJSON(String url, String json) {

        HttpPost httpost = new HttpPost(url);
        try {
//			log.info("set utf-8 form entity to httppost");
            httpost.addHeader("Content-type", "application/json");
            httpost.addHeader("Accept","application/json");
            StringEntity entity = new StringEntity(json.toString(), "utf-8");// 解决中文乱码问题
            httpost.setEntity(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return httpost;
    }
    private static HttpPost postJSON(String url, String json,final HashMap<String, String> head) {

        HttpPost httpost = new HttpPost(url);
        try {
//			log.info("set utf-8 form entity to httppost");
            /*httpost.addHeader("Content-type", "application/json");
            httpost.addHeader("Accept","application/json");*/
        	for (String key : head.keySet()) {
				httpost.addHeader(key, head.get(key));
			}
        	//默认请求头
        	if(!head.containsKey("Content-type")){
        		httpost.addHeader("Content-type", "application/json");
        	}
        	if(!head.containsKey("Accept"))
        	{
        		  httpost.addHeader("Accept","application/json");
        	}
            StringEntity entity = new StringEntity(json.toString(), "utf-8");// 解决中文乱码问题
            httpost.setEntity(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return httpost;
    }

    public static void main(String[] args) throws Exception {
        //String creatTable_url = "http://api.map.baidu.com/geodata/v3/geotable/create";
        //Map<String, String> params = new HashMap<String, String>();
        //params.put("name", "czloc");
        //params.put("geotype", "1");
        //params.put("is_published", "1");
        //params.put("ak", "TKBDIoYGzUGh1nCyoGBGHQ4O");
        //String xml = HttpClient.post(creatTable_url, params);
        //System.out.println(xml);
        //URLDecoder.decode(xml);
        //System.out.println(xml);
        System.out.println(URLDecoder.decode("34%2C35%2C41","utf-8"));

//        Map<String, String> params = new HashMap<String, String>();
//        params.put("partnerOrderId", "112121");
//        params.put("partnerOrderNo", "jkjk");
//        params.put("businessType", "1");
//        params.put("orderType", "6");
//        params.put("carType", "1");
//        params.put("startPlace","北京东城区银河SOHO");
//        params.put("startGps", "116.432958,39.922368");
//        params.put("endPlace", "北京市朝阳区望京");
//        params.put("expectedKm", "19.9");
//        params.put("endGps", "116.466554,39.995147");
//        params.put("expectedPrice", "30");
//        params.put("customerPhone", "13800008888");
//        params.put("customerName", "s");
//        params.put("callDate", "2016-12-01 15:42:30");
//        params.put("bookDate", "2016-12-01 15:22:30");
//        params.put("orderSource", "4");
//        params.put("city", "北京");
//        params.put("cityId", "35");
//        params.put("channel", "sqyc_001");
//
//
//        //getSign(params);
//        //System.out.println(params.get("sign"));
//        //String xml = HttpClient.postEncrypt("http://111.206.162.235:18087/touch/partner/getGroupsPrice", params);
//        //String xml = HttpClient.postEncrypt("http://test-ck.letzgo.com.cn:12015/dzcx_partner/cooperation/order/newOrder", params);
//        String xml = HttpClient.postEncrypt("http://127.0.0.1:8080/dzcx_partner/cooperation/order/newOrder", params);
//        System.out.println("===="+xml);
    }

    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return
     * @author 李晓健
     * @date 2014年7月12日 下午12:17:36
     */
    public static String https(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        BufferedReader bufferedReader = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        HttpsURLConnection httpUrlConn = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);
            if (requestMethod.equalsIgnoreCase("GET")) {
                httpUrlConn.connect();
            }
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            httpUrlConn.connect();
            inputStream = httpUrlConn.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // jsonObject = JSONObject.parseObject(buffer.toString());

        } catch (ConnectException ce) {
            // log.error("Weixin server connection timed out.");
        } catch (Exception e) {
            // log.error("https request error:{}", e);
        } finally {
            // 释放资源
            try {
                if (null != bufferedReader) {
                    bufferedReader.close();
                }
                if (null != inputStreamReader) {
                    inputStreamReader.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                    inputStream = null;
                }
                if (null != httpUrlConn) {
                    httpUrlConn.disconnect();
                }
            } catch (Exception e2) {
            }
        }
        return buffer.toString();
    }
}