package danieldjgomes.larica.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "danieldjgomes.larica")
@EnableFeignClients({"danieldjgomes.larica.*"})
public class LaricaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaricaApplication.class, args);

	}

}
