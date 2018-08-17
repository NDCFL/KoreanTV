package com.dt.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.common.Message;
import com.dt.service.VedioSectionService;
import com.dt.service.VedioService;
import com.dt.vo.VedioSectionVo;
import com.dt.vo.VedioVo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Random;

@Controller
@RequestMapping("test")
public class TestController {

    @Resource
    private VedioService vedioService;
    @Resource
    private VedioSectionService vedioSectionService;

    @RequestMapping("index")
    public String index() {

        return "swagger/dist/index";
    }

    @RequestMapping("test")
    @ResponseBody
    public Message test() {
        return Message.success("成功!");
    }

    @RequestMapping("getInfo")
    @ResponseBody
    public Message getInfo(String nickName) {
        return Message.success(nickName);
    }

    @RequestMapping("updates")
    @ResponseBody
    public void update() {
        String url = "http://111.230.234.200:88/query.aspx?page={page}";
        String sectionId = "http://111.230.234.200:88/detail.aspx?seasonId={page}";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        for (int i = 1; i <= 226; i++) {
            try {
                HttpPost httpPost = new HttpPost(url.replace("{page}", i + ""));
                JSONObject jsonObject = new JSONObject();
                ByteArrayEntity entity = null;
                entity = new ByteArrayEntity(jsonObject.toString().getBytes("UTF-8"));
                entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                httpPost.setEntity(entity);
                String result = httpclient.execute(httpPost, responseHandler);
                JSONObject accessorJSON = JSON.parseObject(result.substring(0, result.indexOf("<")));
                JSONObject jUser = accessorJSON.getJSONObject("data");
                JSONArray u = jUser.getJSONArray("results");
                for (int j = 0; j < u.size(); j++) {
                    JSONObject object = u.getJSONObject(j);
                    VedioVo vedioVo = new VedioVo();
                    vedioVo.setTitle(object.get("title").toString());//标题
                    vedioVo.setDescription(object.get("brief").toString());//描述
                    vedioVo.setFaceImg(object.get("cover").toString());//剧缩略图
                    vedioVo.setJishu(Integer.parseInt(object.get("upInfo").toString()));
                    try {
                        vedioVo.setRate(object.get("score").toString());//评分
                    } catch (Exception e) {
                        vedioVo.setRate("5");//评分
                    }
                    vedioVo.setVedioModuleId(Long.parseLong(new Random().nextInt(3) + ""));//模块编号
                    vedioVo.setVedioTypeId(object.get("cat").toString());
                    vedioVo.setArea(object.get("area").toString());
                    vedioVo.setYear(object.get("year").toString());
                    vedioVo.setIsVip(0);
                    if (object.get("finish").toString().equals("false")) {
                        vedioVo.setIsLast(1);
                    } else {
                        vedioVo.setIsLast(0);
                    }
                    vedioVo.setIsActive(0);
                    vedioVo.setCreateTime(new Date());
                    vedioService.save(vedioVo);
                    HttpPost httpPost1 = new HttpPost(sectionId.replace("{page}", object.get("id") + ""));
                    JSONObject jsonObject1 = new JSONObject();
                    ByteArrayEntity entity1 = null;
                    entity1 = new ByteArrayEntity(jsonObject.toString().getBytes("UTF-8"));
                    entity1.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                    httpPost.setEntity(entity1);
                    String result1 = httpclient.execute(httpPost1, responseHandler);
                    JSONObject accessorJSON1 = JSON.parseObject(result1);
                    JSONObject jUser1 = accessorJSON1.getJSONObject("data");
                    JSONObject jUser2 = jUser1.getJSONObject("season");
                    JSONArray u1 = jUser2.getJSONArray("playUrlList");
                    for (int p = 0; p < u1.size(); p++) {
                        JSONObject object1 = u1.getJSONObject(p);
                        VedioSectionVo vedioSectionVo = new VedioSectionVo();
                        vedioSectionVo.setVedioId(vedioVo.getId());
                        vedioSectionVo.setEpisode(Integer.parseInt(object1.get("episode").toString()));
                        vedioSectionVo.setTitle("第" + object1.get("episode") + "集");
                        vedioSectionVo.setUrl("http://139.199.200.20/meiju.aspx?episodeSid=" + object1.get("episodeSid") + "&seasonId=" +Long.parseLong(object.get("id")+""));
                        vedioSectionVo.setLookTimes(100);
                        vedioSectionVo.setCreateTime(new Date());
                        vedioSectionVo.setIsActive((byte) 0);
                        vedioSectionService.save(vedioSectionVo);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

        public String handleResponse(final HttpResponse response) throws IOException {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        }

    };

    public static void main(String arg[]) {
        String url = "http://111.230.234.200:88/query.aspx?page={page}";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        for (int i = 1; i <= 226; i++) {
            try {
                HttpPost httpPost = new HttpPost(url.replace("{page}", i + ""));
                JSONObject jsonObject = new JSONObject();
                ByteArrayEntity entity = null;
                entity = new ByteArrayEntity(jsonObject.toString().getBytes("UTF-8"));
                entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                httpPost.setEntity(entity);
                String result = httpclient.execute(httpPost, responseHandler);
                JSONObject accessorJSON = JSON.parseObject(result.substring(0, result.indexOf("<")));
                JSONObject jUser = accessorJSON.getJSONObject("data");
                JSONArray u = jUser.getJSONArray("results");
                for (int j = 0; j < u.size(); j++) {
                    JSONObject object = u.getJSONObject(j);
                    System.out.println(object.get("upInfo"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
