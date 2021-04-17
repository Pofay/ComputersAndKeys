package com.pofay.computersandkeys.repositories;

import com.pofay.computersandkeys.entities.AuthorizedKey;

import org.springframework.data.repository.CrudRepository;

public interface AuthorizedKeysRepository extends CrudRepository<AuthorizedKey, String> {

}
