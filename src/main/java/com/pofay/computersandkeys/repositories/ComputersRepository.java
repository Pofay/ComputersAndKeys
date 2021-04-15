package com.pofay.computersandkeys.repositories;

import com.pofay.computersandkeys.entities.Computer;
import org.springframework.data.repository.CrudRepository;

public interface ComputersRepository extends CrudRepository<Computer, String> {
}
