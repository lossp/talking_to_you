package lossp.serviceImp;

import com.alibaba.fastjson.JSONObject;
import lossp.service.AccountService;
import lossp.valueObject.ChatMessageRequestVO;
import lossp.valueObject.RegisterInfoResponse;
import lossp.valueObject.ServerResponseVO;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static lossp.constant.Constant.ACCOUNT_PREFIX;

@Component
public class AccountServiceImp implements AccountService {
    Logger logger = LoggerFactory.getLogger(AccountServiceImp.class);

    private MediaType mediaType = MediaType.parse("application/json");

    @Autowired
    private OkHttpClient okHttpClient;

    @Override
    public RegisterInfoResponse register(RegisterInfoResponse info) {
        String key = ACCOUNT_PREFIX + info.getUserId();
        // 从中需要从数据库中查询信息，但是基于项目规模，目前只考虑通过redis来处理
        // TODO 处理个人信息

        return null;
    }

    @Override
    public void offLine(Long userId) throws Exception {

    }

    @Override
    public void messagePush(String url, Long userId, ChatMessageRequestVO groupMessage) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", groupMessage.getMessage());
        jsonObject.put("userId", groupMessage.getUserId());
        jsonObject.put("receivedUserId", groupMessage.getReceivedUserId());
        jsonObject.put("username", groupMessage.getUsername());
        RequestBody requestBody = RequestBody.create(mediaType, jsonObject.toString());

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        try {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        } finally {
            response.body().close();
        }
    }

    @Override
    public ServerResponseVO loadServerByUserId(Long userId) {
        // TODO 仅仅作为测试数据，之后需要从Mysql | redis中获取，通过userID映射获取到的服务器信息
        ServerResponseVO serverResponseVO = new ServerResponseVO();
        serverResponseVO.setIp("127.0.0.1");
        serverResponseVO.setHttpPort(3000);
        serverResponseVO.setServerPort(3030);

        return serverResponseVO;
    }
}
