package lossp.serviceImp;

import lossp.service.AccountService;
import lossp.valueObject.ChatMessageRequestVO;
import lossp.valueObject.RegisterInfoResponse;
import lossp.valueObject.ServerResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static lossp.constant.Constant.ACCOUNT_PREFIX;

@Component
public class AccountServiceImp implements AccountService {
    Logger logger = LoggerFactory.getLogger(AccountServiceImp.class);

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

    }

    @Override
    public ServerResponseVO loadServerByUserId(Long userId) {
        // TODO 仅仅作为测试数据，之后需要从Mysql | redis中获取，通过userID映射获取到的服务器信息
        ServerResponseVO serverResponseVO = new ServerResponseVO();
        serverResponseVO.setIp("127.0.0.1");
        serverResponseVO.setHttpPort(3004);
        serverResponseVO.setServerPort(3030);

        return serverResponseVO;
    }
}
