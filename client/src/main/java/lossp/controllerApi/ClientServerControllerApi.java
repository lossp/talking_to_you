package lossp.controllerApi;

import lossp.valueObject.*;

public interface ClientServerControllerApi {
    public BaseResponse<NULLBody> sendMessage(StringRequestVO stringRequestVO) throws Exception;

    public BaseResponse<NULLBody> login(LoginRequestVO loginRequestVO) throws Exception;

    public BaseResponse<NULLBody> sendGroupMessage(SendMessageRequestVO sendMessageRequestVO);

    public BaseResponse<NULLBody> sendP2PMessage(P2PMessageRequestVO p2PMessageRequestVO);
}
