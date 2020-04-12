package lossp.serviceImp;

import lossp.service.AccountService;
import lossp.valueObject.GroupMessage;
import lossp.valueObject.RegisterInfoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static lossp.constant.Constant.ACCOUNT_PREFIX;

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
    public void offLine(long userId) throws Exception {

    }

    @Override
    public void messagePush(String url, long userId, GroupMessage groupMessage) throws Exception {

    }
}
