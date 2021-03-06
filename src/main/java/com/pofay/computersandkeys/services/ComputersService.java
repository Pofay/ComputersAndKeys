package com.pofay.computersandkeys.services;

import java.util.Optional;
import java.util.stream.StreamSupport;
import com.pofay.computersandkeys.entities.Computer;
import com.pofay.computersandkeys.repositories.ComputersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComputersService {

    ComputersRepository repository;

    @Autowired
    public ComputersService(ComputersRepository repository) {
        this.repository = repository;
    }

    public Optional<Computer> findMatchingComputerByMakerAndModel(String maker, String model) {
        return repository.findById(model)
                .flatMap(c -> c.getMaker().equals(maker.toUpperCase()) ? Optional.of(c) : Optional.empty());
    }

    public boolean hasAssociatedModels(String maker) {
        long numberOfComputers = StreamSupport.stream(repository.findAll().spliterator(), false)
                .filter(c -> c.getMaker().equals(maker.toUpperCase())).count();
        return numberOfComputers > 0;
    }

}
