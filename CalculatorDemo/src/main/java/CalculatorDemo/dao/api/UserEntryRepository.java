package CalculatorDemo.dao.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import CalculatorDemo.dao.entities.UserEntry;

@Repository
public interface UserEntryRepository extends JpaRepository<UserEntry, Long> {

	public List<UserEntry> findByName(String name);
}
