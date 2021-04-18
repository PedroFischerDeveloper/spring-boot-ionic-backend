package com.example.demo;

import com.example.demo.domain.Category;
import com.example.demo.domain.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@EnableAutoConfiguration
public class DemoApplication implements CommandLineRunner  {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args)
	{
		SpringApplication.run(DemoApplication.class, args);
	}

	/**
	 * CommandLineRunner
	 * executa o código que estiver dentro de run
	 * aqui estamos instanciando os objetos do modelo concentual
	 * */
	@Override
	public void run(String... args) {
		Category c1 = new Category(null, "Informática");
		Category c2 = new Category(null, "Escritório");

		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);

		c1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		c2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategorys().addAll(Arrays.asList(c1));
		p2.getCategorys().addAll(Arrays.asList(c1, c2));
		p3.getCategorys().addAll(Arrays.asList(c1));

		

		categoryRepository.saveAll(Arrays.asList(c1, c2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));

	}
}
