package kosmachevskiy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineShopApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(OnlineShopApplication.class);

	public static void main(String[] args) {
		LOGGER.debug("Application Starting ... ");
		SpringApplication.run(OnlineShopApplication.class, args);
	}
}
