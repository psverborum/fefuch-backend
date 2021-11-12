package com.verborum.fefuchbackend.Repository;

import org.springframework.data.repository.CrudRepository;
import com.verborum.fefuchbackend.Entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
