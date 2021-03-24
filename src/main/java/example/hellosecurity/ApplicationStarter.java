package example.hellosecurity;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@SpringBootApplication
@MapperScan(value = "example.hellosecurity.mybatis.mapper")
@NacosPropertySource(dataId = "hello-security-data-id", autoRefreshed = true)
public class ApplicationStarter {

	@NacosValue(value = "${message:default message}", autoRefreshed = true)
	String message;

	@NacosInjected
	private NamingService namingService;

	/**
	 * 向nacos注册服务
	 * @throws NacosException
	 */
	@PostConstruct
	private void init() throws NacosException {
		namingService.registerInstance("hello-security", "dev","101.231.195.147",8101);
	}

	/**
	 * 参考这个项目，很详细 https://www.cnblogs.com/dw3306/p/12751373.html
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApplicationStarter.class, args);
	}

}
