package example.hellosecurity.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
public class HostController {

    @NacosInjected
    private NamingService namingService;

    @NacosInjected
    private ConfigService configService;

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
    String mainPage(){
        return "main page!";
    }

    /**
     * 获取nacos配置信息
     * @param dataId
     * @param group
     * @return
     * @throws NacosException
     */
    @RequestMapping(path = "/nacos/confs", method = RequestMethod.GET)
    String confs(@RequestParam(value = "dataId", required = false, defaultValue = "hello-security-data-id") String dataId,
                 @RequestParam(value = "group", required = false, defaultValue = "dev") String group) throws NacosException {
        return configService.getConfig(dataId, group,5000);
    }

    /**
     * 获取nacos服务信息
     * @param serviceName
     * @return
     * @throws NacosException
     */
    @RequestMapping(value = "/nacos/servers", method = RequestMethod.GET)
    List<Instance> servers(@RequestParam(value = "serviceName", required = false, defaultValue = "hello-security") String serviceName,
                           @RequestParam(value = "groupName", required = false, defaultValue = "dev") String groupName) throws NacosException {
        return namingService.getAllInstances(serviceName, groupName);
    }
}
