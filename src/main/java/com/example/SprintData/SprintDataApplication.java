package com.example.SprintData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class SprintDataApplication implements CommandLineRunner {

	@Autowired
	private ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(SprintDataApplication.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner insertTestData(ProductRepository productRepository) {
		return args -> {
			if (productRepository.count() == 0) { // Only insert if table is empty
				productRepository.saveAll(List.of(
						new Product("Laptop"),
						new Product("Smartphone"),
						new Product("Headphones"),
						new Product("Monitor"),
						new Product("Keyboard"),
						new Product("Mouse"),
						new Product("Tablet"),
						new Product("Smartwatch"),
						new Product("Printer"),
						new Product("Camera")
				));
			}
		};
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);
		int page = 0;
		String command;

		do {
			List<Product> products = productService.getProducts(page);

			if (products.isEmpty()) {
				System.out.println("No more products.");
			} else {
				System.out.println("\nPage " + (page + 1));
				products.forEach(product -> System.out.println(product.getId() + ". " + product.getName()));
			}

			System.out.println("\nCommands: [n] Next Page | [p] Previous Page | [q] Quit");
			command = scanner.nextLine();

			if ("n".equalsIgnoreCase(command)) {
				page++;
			} else if ("p".equalsIgnoreCase(command) && page > 0) {
				page--;
			}

		} while (!"q".equalsIgnoreCase(command));

		System.out.println("Programa uždaroma. Program is closing!");
		scanner.close();
	}
}
