package example.hellosecurity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ApplicationStarterTest {

	@Test
	void contextLoads() {
//		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

//		String idForEncode = "bcrypt";
//		Map encoders = new HashMap<>();
//		encoders.put(idForEncode, new BCryptPasswordEncoder());
//		encoders.put("noop", NoOpPasswordEncoder.getInstance());
//		encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
//		encoders.put("scrypt", new SCryptPasswordEncoder());
//		encoders.put("sha256", new StandardPasswordEncoder());
//
//		PasswordEncoder passwordEncoder =
//				new DelegatingPasswordEncoder(idForEncode, encoders);


		UserDetails user = User.withDefaultPasswordEncoder()
				.username("jgs")
				.password("3123")
				.roles("user")
				.build();
		System.out.println(user.getPassword());
	}
}
