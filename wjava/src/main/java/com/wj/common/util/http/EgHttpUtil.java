package com.wj.common.util.http;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author : wangjia
 * @version : http client v2.0
 * @time : 2016/1/4 14:50
 */
@SuppressWarnings("WeakerAccess")
public class EgHttpUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(EgHttpUtil.class);
    private static final String PRE_SCHEME_HTTP = "http://";
    private static final String PRE_SCHEME_HTTPS = "https://";
    private static final String CONTENT_TYPE_APP_JSON = "application/json";
    private static final Charset DEFAULT_ENCODE = Charset.forName("UTF-8");

    private static final int DEFAULT_RETRY_TIMES = 3;
    private static final RequestConfig DEFAULT_CONFIG;
    private static final HttpParams timeParams = new BasicHttpParams();
    private static final int SOCKET_TIME_OUT = 5000;
    private static final int CONN_TIME_OUT = 5000;

    static {
        DEFAULT_CONFIG = RequestConfig.custom().setSocketTimeout(SOCKET_TIME_OUT).setConnectTimeout(CONN_TIME_OUT).build();
        // 连接url的连接等待时间：
        timeParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, CONN_TIME_OUT);
        // 连接到url后，获取response的返回等待时间
        timeParams.setParameter(CoreConnectionPNames.SO_TIMEOUT, SOCKET_TIME_OUT);
    }

    public static String get(String url, Map<String, Object> params) {
        try {
            return request(url, params, DEFAULT_ENCODE, DEFAULT_RETRY_TIMES, RequestMethod.GET);

        } catch (Exception e) {
            LOGGER.error("error", e);
            return null;
        }
    }

    public static String post(String url, Map<String, Object> params) {
        try {
            return request(url, params, DEFAULT_ENCODE, DEFAULT_RETRY_TIMES, RequestMethod.POST);
        } catch (Exception e) {
            LOGGER.info("error", e);
            return null;
        }
    }

    @SuppressWarnings("Duplicates")
    public static String request(String url, Map<String, Object> params, Charset encode, int reTryTimes, RequestMethod requestMethod) throws Exception {
        long startTime = System.currentTimeMillis();
        checkUrl(url);
        CloseableHttpClient httpClient = getHttpClient(url);
        HttpUriRequest request = getHttpUriRequest(url, params, requestMethod);
        for (int i = 0; i < reTryTimes; i++) {
            try {
                CloseableHttpResponse httpResponse = httpClient.execute(request);
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                String response = EntityUtils.toString(httpResponse.getEntity(), encode);
                if (HttpStatus.OK.value() == statusCode) {
                    httpLog(startTime, params, url, response);
                    httpClient.close();
                    return response;
                } else {
                    httpLog(startTime, params, url, response);
                }
            } catch (Exception e) {
                LOGGER.error("http error", e);
            }
            i++;
        }
        httpClient.close();
        return null;
    }

    private static HttpUriRequest getHttpUriRequest(String url, Map<String, Object> params, RequestMethod requestMethod) throws IOException {
        List<NameValuePair> nameValuePairs = geneParams(params);
        HttpUriRequest request;
        switch (requestMethod) {
            case GET:
                request = geneHttpGet(url, nameValuePairs);
                break;
            case POST:
                request = geneHttpPost(url, nameValuePairs);
                break;
            default:
                request = geneHttpGet(url, nameValuePairs);
        }
        return request;
    }

    private static void httpLog(long startTime, Map<String, Object> params, String url, String response) {
        Long timeCost = System.currentTimeMillis() - startTime;
        LOGGER.info("params:{}", getUrlParamsByMap(params));
        LOGGER.info("url:{},response:{},cost:{}ms", url, response, timeCost);
    }

    private static String getUrlParamsByMap(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            sb.append(entry.getKey()).append("=").append(entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = StringUtils.substringBeforeLast(s, "&");
        }
        return s;
    }

    private static CloseableHttpClient getHttpClient(String url) {
        if (isHttps(url)) {
            try {
                return new SSLClient(timeParams);
            } catch (Exception e) {
                LOGGER.error("ssl error", e);
            }
        }
        return HttpClients.createDefault();
    }

    private static boolean isHttps(String url) {
        return url.startsWith(PRE_SCHEME_HTTPS);
    }

    private static void checkUrl(String url) {
        if (!url.startsWith(PRE_SCHEME_HTTP) && !url.startsWith(PRE_SCHEME_HTTPS)) {
            LOGGER.error("wrong scheme:{}", url);
            throw new IllegalStateException("wrong scheme");
        }
    }

    private static HttpGet geneHttpGet(String url, List<NameValuePair> nameValuePairs) throws IOException {
        String paramsFormatedForGet = EntityUtils.toString(new UrlEncodedFormEntity(nameValuePairs, Consts.UTF_8));
        HttpGet httpGet;
        if (StringUtils.isBlank(paramsFormatedForGet)) {
            httpGet = new HttpGet(url.trim());
        } else {
            httpGet = new HttpGet(url.trim() + "?" + paramsFormatedForGet);
        }
        httpGet.setConfig(DEFAULT_CONFIG);
        return httpGet;
    }

    private static HttpPost geneHttpPost(String url, List<NameValuePair> nameValuePairs) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, Consts.UTF_8));
        httpPost.setConfig(RequestConfig.DEFAULT);
        return httpPost;
    }

    private static List<NameValuePair> geneParams(Map<String, Object> params) {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
        if (params != null && !params.isEmpty()) {
            Set<Map.Entry<String, Object>> set = params.entrySet();
            for (Map.Entry<String, Object> entry : set) {
                if (entry.getValue() != null) {
                    nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
                }
            }
        }
        return nameValuePairs;
    }

    @SuppressWarnings("Duplicates")
    public static String doJsonPost(String url, String json, int reTryTimes) throws Exception {
        long startTime = System.currentTimeMillis();
        CloseableHttpClient httpClient = getHttpClient(url);
        HttpPost jsonPost = geneHttpJsonPost(url, json);
        for (int i = 0; i < reTryTimes; i++) {
            try {
                CloseableHttpResponse httpResponse = httpClient.execute(jsonPost);
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                String response = EntityUtils.toString(httpResponse.getEntity(), DEFAULT_ENCODE);
                if (HttpStatus.OK.value() == statusCode) {
                    httpLog(startTime, json, url, response);
                    httpClient.close();
                    return response;
                } else {
                    httpLog(startTime, json, url, response);
                }
            } catch (Exception e) {
                LOGGER.error("http error", e);
            }
            i++;
        }
        httpClient.close();
        return null;
    }

    private static void httpLog(long startTime, String params, String url, String response) {
        Long timeCost = System.currentTimeMillis() - startTime;
        LOGGER.info("params:{}", params);
        LOGGER.info("url:{},response:{},cost:{}ms", url, response, timeCost);
    }

    private static HttpPost geneHttpJsonPost(String url, String json) throws UnsupportedEncodingException {
        HttpPost post = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(json);
        stringEntity.setContentEncoding("UTF-8");
        stringEntity.setContentType(CONTENT_TYPE_APP_JSON);
        post.setEntity(stringEntity);
        return post;
    }
}