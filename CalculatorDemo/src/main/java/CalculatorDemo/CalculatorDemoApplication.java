package CalculatorDemo;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import CalculatorDemo.dao.api.UserEntryRepository;
import CalculatorDemo.dao.entities.ComputationEntry;
import CalculatorDemo.dao.entities.UserEntry;

@SpringBootApplication
public class CalculatorDemoApplication implements CommandLineRunner {

	@Autowired
	private UserEntryRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(CalculatorDemoApplication.class, args);
	}
	
	
	@Override
	@Transactional
	public void run(String... strings) throws Exception {
		UserEntry userEntry = new UserEntry("Tarek", "hello");
		Set<ComputationEntry> computations = new HashSet<>();
		computations.add(new ComputationEntry("1 + 1", "2", userEntry));
		userEntry.setComputations(computations);
		repo.save(userEntry);
	}
	
}
