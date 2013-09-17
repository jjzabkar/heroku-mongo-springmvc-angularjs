package com.jjz.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import com.jjz.domain.Foo;

/**
 * {@link Repository} that persists domain objects that are handed to business {@code Service}s.
 * 
 * @author JJ Zabkar
 */
@Repository
public interface FooRepository extends CrudRepository<Foo, UUID>, MongoRepository<Foo, UUID> {

}
