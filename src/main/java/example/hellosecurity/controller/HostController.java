package example.hellosecurity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class HostController {

    String UNKNOWN = "unknown";

    @RequestMapping(path = "/host", method = RequestMethod.GET)
    String host() {
        //从上下文中取出HttpServletRequest
        HttpServletRequest  request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //定义url可能对应的Header中的key数组，下标越小优先级越大
        String[] strs = {
                "X-Requested-For",
                "X-Forwarded-For",
                "Proxy-Client-IP",
                "WL-Proxy-Client-IP",
                "HTTP_CLIENT_IP",
                "HTTP_X_FORWARDED_FOR"};
        //遍历key数组
        String clientIp = null;
        for(String str : strs){
            if(StringUtils.hasText(request.getHeader(str)) && !request.getHeader(str).equals("unknown")){
                clientIp = request.getHeader(str);
                break;
            }
        }
        //返回
        return clientIp == null ? request.getRemoteAddr() : clientIp;

    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    String main() {
        return "main page";
    }
}
