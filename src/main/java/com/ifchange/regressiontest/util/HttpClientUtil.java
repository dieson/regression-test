package com.ifchange.regressiontest.util;

import org.apache.http.*;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;


/**
 * Http协议共通类，提供Http的Post/Get 等基础操作
 */
public class HttpClientUtil {

    private static final int TIMEOUT = 10000;
    private static final String ENCODING = "UTF-8";
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * 发送get请求
     *
     * @param url
     * @param timeOut
     * @param headers
     * @return
     */
    public static String getWithHeader(String url, int timeOut, Header... headers) throws IOException {
        LOGGER.info("get url: " + url);
        Request request = Request.Get(url);
        if (timeOut > 0) {
            request.connectTimeout(timeOut).socketTimeout(timeOut);
        }
        request.addHeader("Content-Type", "application/json");
        if (headers != null && headers.length > 0) {
            for (Header header : headers) {
                request.addHeader(header);
            }
        }
        HttpResponse returnResponse = request.execute().returnResponse();
        int statusCode = returnResponse.getStatusLine().getStatusCode();
        if (statusCode == HttpStatus.SC_OK) {
            String result = StringUtil.unicodeToString(EntityUtils.toString(returnResponse.getEntity(), StandardCharsets.UTF_8));
            LOGGER.debug("get return: " + result);
            return result;
        } else {
            LOGGER.error("client.executeMethod failed: " + statusCode);
        }
        return null;
    }

    /**
     * 发送Http Get请求，并指定超时时间
     *
     * @param url
     * @param timeOut
     * @return
     */
    public static String getTimeOut(String url, int timeOut) throws IOException {
        return getWithHeader(url, timeOut);
    }

    /**
     * 发送get请求
     *
     * @param url
     * @return
     */
    public static String get(String url) throws IOException {
        return getTimeOut(url, TIMEOUT);
    }

    /**
     * Get请求，失败再次发起请求
     *
     * @param url
     * @param tryCount 重复次数
     * @return
     */
    public static String getTryCount(String url, int tryCount) throws IOException {
        String result = "";
        int timeOut = 0;
        for (int i = 1; i <= tryCount; i++) {
            timeOut += TIMEOUT;
            result = getTimeOut(url, timeOut);
            LOGGER.debug(url + ", 第" + i + "次调用结果为:" + result);
            if (!StringUtil.isEmpty(result)) {
                return result;
            }
        }
        return result;
    }

    public static String getTimeOutAndTryCount(String url, int timeOut, int tryCount) throws IOException {
        String result = "";
        for (int i = 1; i <= tryCount; i++) {
            result = getTimeOut(url, timeOut);
            LOGGER.debug(url + ", 第" + i + "次调用结果为:" + result);
            if (!StringUtil.isEmpty(result)) {
                return result;
            }
        }
        return result;
    }

    public static String postWithHeader(String url, String data, int timeOut, Header... headers) throws IOException {
        if (StringUtil.isEmpty(url) || StringUtil.isEmpty(data)) {
            return null;
        }
        Request request = Request.Post(url).bodyString(data, ContentType.APPLICATION_JSON).addHeader(new BasicHeader("Accept", ContentType.APPLICATION_JSON.toString()));
        if (timeOut > 0) {
            request.connectTimeout(timeOut).socketTimeout(timeOut);
        }
        request.addHeader("Content-Type", "application/json");
        if (headers != null && headers.length > 0) {
            for (Header header : headers) {
                request.addHeader(header);
            }
        }
        HttpResponse returnResponse = request.execute().returnResponse();
        int statusCode = returnResponse.getStatusLine().getStatusCode();
        if (statusCode == HttpStatus.SC_OK) {
            LOGGER.info("url:" + url + " post successfully.");
            String result = StringUtil.unicodeToString(EntityUtils.toString(returnResponse.getEntity(), StandardCharsets.UTF_8));
            LOGGER.debug("url:" + url + " post return: " + result);
            return result;
        } else {
            LOGGER.error("url:" + url + " post failed: " + statusCode);
        }
        return null;
    }

    /**
     * 发送 http post 请求并指定超时时间
     *
     * @param url
     * @param data json数据
     * @return http返回结果
     */
    public static String postTimeOut(String url, String data, int timeOut) throws IOException {
        return postWithHeader(url, data, timeOut);
    }

    /**
     * post请求，失败再次发起请求
     *
     * @param url
     * @param data
     * @param tryCount 重复次数
     * @return
     */
    public static String postTryCount(String url, String data, int tryCount) throws IOException {
        String result = "";
        int timeOut = 0;
        for (int i = 1; i <= tryCount; i++) {
            timeOut += TIMEOUT;
            result = postWithHeader(url, data, timeOut);
            LOGGER.debug(url + ", 第" + i + "次调用结果为:" + result);
            if (!StringUtil.isEmpty(result)) {
                return result;
            }
        }
        return result;
    }

    public static String postTimeOutAndTryCount(String url, String data, int timeOut, int tryCount) throws IOException {
        String result = "";
        for (int i = 1; i <= tryCount; i++) {
            result = postWithHeader(url, data, timeOut);
            LOGGER.debug(url + ", 第" + i + "次调用结果为:" + result);
            if (!StringUtil.isEmpty(result)) {
                return result;
            }
        }
        return result;
    }

    /**
     * URL转码方法
     *
     * @param parameter
     * @return
     */
    public static String encodeUrlUTF8(String parameter) {
        if (StringUtil.isEmpty(parameter))
            return "";
        try {
            parameter = URLEncoder.encode(parameter, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parameter;
    }

    /**
     * url解码方法
     *
     * @param parameter
     * @return
     */
    public static String decodeUrlUTF8(String parameter) {
        if (StringUtil.isEmpty(parameter))
            return "";
        try {
            parameter = URLDecoder.decode(parameter, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return parameter;
    }

    /**
     * 发送请求,参数值以body形式发送
     *
     * @param url      请求的服务器地址
     * @param bodyData 需发送的Request Body数据,如: 我们要以流发送的数据...
     * @return 返回服务器的响应
     * @throws IOException
     */
    public static String send(String url, String bodyData) throws IOException {
        return send(url, null, null, bodyData);
    }

    /**
     * 发送请求[设置请求头],参数值以body形式发送
     *
     * @param url            请求服务器地址
     * @param requestHeaders 请求头部值 key-value 如(头部有2个键值):
     *                       key=Content-Type value=application/json
     *                       key=Authorization value=bay dasfdfasddasf
     *                       为null默认为: "application/octet-stream"
     * @param bodyData       需发送的Request Body数据
     * @return URL所代表远程资源的响应结果
     * @throws IOException
     */
    public static String send(String url, Map<String, String> requestHeaders, String bodyData) throws IOException {
        return send(url, null, requestHeaders, bodyData);
    }

    /**
     * 发送请求[设置请求方式GET POST PUT DELETE|设置请求头],参数值以body形式发送
     *
     * @param url            请求服务器地址
     * @param requestMethod  请求方式: GET POST PUT DELETE HEAD OPTIONSTRACE
     * @param requestHeaders 请求头部值 key-value 如(头部有2个键值):
     *                       key=Content-Type value=application/json
     *                       key=Authorization value=bay dasfdfasddasf
     *                       为null默认为: "application/octet-stream"
     * @param bodyData       需发送的Request Body数据
     * @return URL所代表远程资源的响应结果
     * @throws IOException
     */
    public static String send(String url, String requestMethod, Map<String, String> requestHeaders, String bodyData) throws IOException {
        LOGGER.debug("[发送请求]请求地址:" + url);
        if (null == url || "".equals(url))
            throw new NullPointerException("请求的地址不能为空!");
        bodyData = (null == bodyData || "".equals(bodyData)) ? "0" : bodyData;
        HttpURLConnection httpURLConnection;
        // 建立链接
        URL gatewayUrl = new URL(url);
        httpURLConnection = (HttpURLConnection) gatewayUrl.openConnection();

        // 设置连接属性
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setUseCaches(false);
        requestMethod = StringUtil.isEmpty(requestMethod) ? "POST" : requestMethod;
        httpURLConnection.setRequestMethod(requestMethod);
        // 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致
        byte[] requestStringBytes = bodyData.getBytes(ENCODING);

        // 设置请求属性
        httpURLConnection.setRequestProperty("Content-length", "" + requestStringBytes.length);
        if (null == requestHeaders || requestHeaders.size() < 1)
            httpURLConnection.setRequestProperty("Content-Type", "application/octet-stream");
        else {
            Set<String> entries = requestHeaders.keySet();
            for (String key : entries) {
                String value = requestHeaders.get(key);
                httpURLConnection.setRequestProperty(key, value);
            }
        }
        // 建立输出流，并写入数据
        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(requestStringBytes);
        outputStream.close();
        // 获取所有响应头字段
        Map<String, List<String>> map = httpURLConnection.getHeaderFields();
        // 遍历所有的响应头字段
        StringBuilder sb = new StringBuilder();
        for (String key : map.keySet()) {
            sb.append(key + "=>" + map.get(key) + "\r\t");
        }
        LOGGER.debug("[发送请求]响应头:" + sb);

        // 获得响应状态
        int responseCode = httpURLConnection.getResponseCode();
        StringBuilder responseBuffer = new StringBuilder();
        if (HttpURLConnection.HTTP_OK == responseCode) {
            String readLine;
            BufferedReader responseReader;
            // 处理响应流，必须与服务器响应流输出的编码一致
            responseReader = new BufferedReader(new InputStreamReader(
                    httpURLConnection.getInputStream(), ENCODING));
            while ((readLine = responseReader.readLine()) != null) {
                responseBuffer.append(readLine).append("\n");
            }
            responseReader.close();
        } else
            responseBuffer.append(responseCode);
        LOGGER.debug("[发送请求]响应状态码:" + responseCode + " 响应内容:" + responseBuffer);
        return responseBuffer.toString();
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数(如果有中文参数必须转码且转一次仍有乱码,可转2次))，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
			/*// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}*/
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            LOGGER.error("发送GET请求出错! 请求地址:" + url + " 请求参数:" + param, e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception ex) {
                LOGGER.error("发送GET请求出错! 请求地址:" + url + " 请求参数:" + param, ex);
            }
        }
        return result.toString();
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数(中文参数可以不转码)，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (Exception e) {
            LOGGER.error("发送POST请求出错! 请求地址:" + url + " 请求参数:" + param, e);
        } finally {// 使用finally块来关闭输出流、输入流
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                LOGGER.error("发送POST请求出错! 请求地址:" + url + " 请求参数:" + param, ex);
            }
        }
        return result.toString();
    }

    /**
     * 验证用户是否已经登陆
     *
     * @param xMobile
     * @param xToken
     * @return boolean
     */
    public static boolean isAuthedByAdmin(String xMobile, String xToken, String url) {
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new SecureRandom());
            URL myURL = new URL(url);

            // 创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
            HttpsURLConnection httpsConn = (HttpsURLConnection) myURL.openConnection();
            httpsConn.setConnectTimeout(TIMEOUT);
            httpsConn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            httpsConn.setSSLSocketFactory(sc.getSocketFactory());
            httpsConn.setRequestProperty("X-User-Mobile", xMobile);
            httpsConn.setRequestProperty("X-User-Token", xToken);
            // 取得该连接返回的code,200为通过,401为过期或未通过
            if (httpsConn.getResponseCode() == 200) {
                return true;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage() + " 请求:" + url);
        }
        return false;
    }

    private static class TrustAnyTrustManager implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
            return;
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
            return;
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
}