package CalculatorDemo.dao.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import CalculatorDemo.dao.entities.ComputationEntry;

@Repository
public interface ComputationRepository extends JpaRepository<ComputationEntry, Long> {
}
