package lossp.serviceImp;

import com.alibaba.fastjson.JSONObject;
import lossp.service.RouteRequest;
import lossp.valueObject.GroupMessageRequestVO;
import lossp.valueObject.LoginRequestVO;
import lossp.valueObject.P2PMessageRequestVO;
import lossp.valueObject.ServerInfoResVO;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

public class RouteRequestImp implements RouteRequest {
    Logger logger = LoggerFactory.getLogger(RouteRequestImp.class);

    @Autowired
    private OkHttpClient httpClient;

    private MediaType mediaType = MediaType.parse("application/json");

    @Value("${server.route.request.url}")
    private String p2pRouteRequestUrl;

    @Override
    public void sendP2PMessage(P2PMessageRequestVO p2PMessageRequestVO) throws Exception{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", p2PMessageRequestVO.getMessage());
        jsonObject.put("userId", p2PMessageRequestVO.getUserId());
        jsonObject.put("receivedUserId", p2PMessageRequestVO.getReceivedUserId());

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
    public ServerInfoResVO.ServerInfo getServer(LoginRequestVO loginRequestVO) throws Exception {
        return null;
    }

    @Override
    public void offLine() {

    }
}
