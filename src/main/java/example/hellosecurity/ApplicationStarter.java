package example.hellosecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class ApplicationStarter {
	/**
	 * 参考这个项目，很详细 https://www.cnblogs.com/dw3306/p/12751373.html
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApplicationStarter.class, args);
	}

}
