package myboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = Starter.class)
@EntityScan(basePackageClasses = Starter.class)
@EnableTransactionManagement
public class Starter  {

	public static void main(String[] args) {
		SpringApplication.run(Starter.class, args);
	}

}
