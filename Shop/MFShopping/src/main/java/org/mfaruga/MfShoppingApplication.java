package org.mfaruga;

import java.util.Arrays;
import org.mfaruga.entity.EProductTag;
import org.mfaruga.entity.Product;
import org.mfaruga.entity.builders.ProductBuilder;
import org.mfaruga.repository.ProductReporitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MfShoppingApplication implements CommandLineRunner {

	@Autowired
	ProductReporitory productRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(MfShoppingApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		if (productRepo.count() == 0) {
			Product chleb = new ProductBuilder().setName("Chleb").setDescription("Duzy chleb").addProductTag(EProductTag.FOOD).build();
			Product woda = new ProductBuilder().setName("Woda Cisowianka").setDescription("Woda 1.5l marka cisowianka").addProductTag(EProductTag.FOOD).build();
			Product zeszyt = new ProductBuilder().setName("Zeszyt").setDescription("Zeszyt do robienia notatek").addProductTag(EProductTag.WORK).build();
			
			Product[] products = new Product[] {chleb, woda, zeszyt};			
			productRepo.save(Arrays.asList(products));
		}
	
		
	}
}
