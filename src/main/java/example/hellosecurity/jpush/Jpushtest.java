package example.hellosecurity.jpush;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.ServiceHelper;
import cn.jiguang.common.connection.NettyHttpClient;
import cn.jiguang.common.resp.ResponseWrapper;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.alibaba.fastjson.JSON;
import io.netty.handler.codec.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class Jpushtest {
    protected static final Logger logger = LoggerFactory.getLogger(Jpushtest.class);
    protected static final String APP_KEY = "3a3fe901239a5924e65fc8f1";
    protected static final String MASTER_SECRET = "ebc9f5fff330f8188fae1df7";

//    public static void main(String[] args) throws Exception{
//        //初始化客户端
//        ClientConfig clientConfig = ClientConfig.getInstance();
////        NettyHttpClient client = new NettyHttpClient(ServiceHelper.getBasicAuthorization(APP_KEY, MASTER_SECRET), null, clientConfig);
//        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, clientConfig);
//
//        //初始化uri
//        URI uri = new URI((String)clientConfig.get(ClientConfig.PUSH_HOST_NAME) + clientConfig.get(ClientConfig.PUSH_PATH));
//
//        //初始化有效负载
//        PushPayload payload = PushPayload.newBuilder()
//                .setPlatform(Platform.ios())//所有平台（指Android，iOS 等）
//                .setAudience(Audience.all())//所有观众（指移动设备）
////                .setAudience(Audience.registrationId("13165ffa4edd6bea878"))//注册ID
//                .setNotification(Notification.alert("content"))
//                .build();
//
//        //发起调用
////        client.sendRequest(HttpMethod.POST, payload.toString(), uri, new NettyHttpClient.BaseCallback() {
////            @Override
////            public void onSucceed(ResponseWrapper responseWrapper) {
////                log.info("Got result: " + responseWrapper.responseContent);
////                client.close();
////            }
////        });
//
//        PushResult result = jpushClient.sendPush(payload);
//        logger.info(JSON.toJSON(result).toString());
//    }
}
