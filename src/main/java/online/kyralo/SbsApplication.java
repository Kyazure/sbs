package online.kyralo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author yeqi
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "online.kyralo.*.dao")
public class SbsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbsApplication.class, args);
	}

}
