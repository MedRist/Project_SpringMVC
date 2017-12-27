package me.project.perso;

import me.project.perso.dao.ProductRepository;
import me.project.perso.entities.Product;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersoApplication {

	public static void main(String[] args) {
		org.springframework.context.ApplicationContext ctx = SpringApplication.run(PersoApplication.class, args);

	}
}
