package vroong.laas.dispatch.infrastructure.common.config;

import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "vroong.laas.dispatch.infrastructure.storage.db")
@EnableJpaRepositories(basePackages = "vroong.laas.dispatch.infrastructure.storage.db")
class JpaConfig {

}
