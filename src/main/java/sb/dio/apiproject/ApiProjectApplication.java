package sb.dio.apiproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ApiProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiProjectApplication.class, args);
	}

}
