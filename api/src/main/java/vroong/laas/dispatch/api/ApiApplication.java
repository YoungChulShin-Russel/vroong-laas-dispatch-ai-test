package vroong.laas.dispatch.api;

import java.math.BigDecimal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
    scanBasePackages = {
        "vroong.laas.dispatch.api",
        "vroong.laas.dispatch.core",
        "vroong.laas.dispatch.infrastructure"
    }
)
public class ApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(ApiApplication.class, args);
  }

}
