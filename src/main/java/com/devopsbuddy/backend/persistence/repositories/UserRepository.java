package com.devopsbuddy.backend.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devopsbuddy.backend.persistence.domain.backend.User;

/**
 * Repository for the entity {@link User}
 * @author SlimenTN
 * For the {@link CrudRepository} we pass two classes:
 * The first one is the entity which we want to create a repository
 * The second is the type of the primary key of this entity (in our case the type of id of the entity User is Long)
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
