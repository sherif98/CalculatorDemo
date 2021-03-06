package CalculatorDemo;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import CalculatorDemo.dao.api.ComputationRepository;
import CalculatorDemo.dao.api.UserEntryRepository;
import CalculatorDemo.dao.entities.ComputationEntry;
import CalculatorDemo.dao.entities.UserEntry;

@SpringBootApplication
public class CalculatorDemoApplication implements CommandLineRunner {

	@Autowired
	private UserEntryRepository repo;
	@Autowired
	private ComputationRepository comrepo;

	public static void main(String[] args) {
		SpringApplication.run(CalculatorDemoApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... strings) throws Exception {
		UserEntry userEntry = new UserEntry("Tarek", "hello");
		List<ComputationEntry> computations = new ArrayList<>();
		computations.add(new ComputationEntry("1 + 1", "2", userEntry));
		userEntry.setComputations(computations);
		UserEntry retrived = repo.save(userEntry);
		ComputationEntry computationEntry = retrived.getComputations().get(0);
		ComputationEntry one = comrepo.getOne(computationEntry.getId());

		System.out.println(one);
	}

}
