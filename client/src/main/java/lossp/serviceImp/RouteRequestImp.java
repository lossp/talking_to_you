package lossp.serviceImp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lossp.service.RouteRequest;
import lossp.valueObject.*;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RouteRequestImp implements RouteRequest {
    Logger logger = LoggerFactory.getLogger(RouteRequestImp.class);

    @Autowired
    private OkHttpClient httpClient;

    private MediaType mediaType = MediaType.parse("application/json");

    @Value("${server.route.p2p.request.url}")
    private String p2pRouteRequestUrl;

    @Value("${server.route.login.request.url}")
    private String userLoginRouteUrl;

    @Override
    public void sendP2PMessage(P2PMessageRequestVO p2PMessageRequestVO) throws Exception{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", p2PMessageRequestVO.getMessage());
        jsonObject.put("userId", p2PMessageRequestVO.getUserId());
        jsonObject.put("receivedUserId", p2PMessageRequestVO.getReceivedUserId());
        logger.info("the json Object = [{}]", jsonObject);
        RequestBody requestBody = RequestBody.create(mediaType, jsonObject.toJSONString());
        Request request = new Request.Builder().url(p2pRouteRequestUrl).post(requestBody).build();

        Response response = httpClient.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code = " + response.code());
        }

        ResponseBody body = response.body();
        try {
            String jsonBody = body.string();
            logger.info("the json body :" + jsonBody );
        } finally {
            body.close();
        }
    }

    @Override
    public void sendGroupMessage(GroupMessageRequestVO groupMessageRequestVO) {

    }

    @Override
    public ServerResponseVO getServer(LoginRequestVO loginRequestVO) throws Exception {
        logger.info("get server at the moment, please wait a moment... ... ...");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", loginRequestVO.getUsername());
        jsonObject.put("userId", loginRequestVO.getUserId());
        RequestBody requestBody = RequestBody.create(mediaType, jsonObject.toString());

        Request request = new Request.Builder().url(userLoginRouteUrl).post(requestBody).build();
        Response response = httpClient.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code = " + response.code());
        }
        ResponseBody body = response.body();
        ServerResponseVO serverResponse;
        try {
            String json = body.string();
            serverResponse = JSON.parseObject(json, ServerResponseVO.class);
        } finally {
            body.close();
        }

        return serverResponse;
    }

    @Override
    public void offLine() {

    }
}
