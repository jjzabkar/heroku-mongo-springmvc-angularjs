package com.jjz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import com.jjz.domain.Foo;
import com.jjz.repository.FooRepository;

/**
 * {@link Service} that contains business logic and allows a {@code Controller} to interact with a DAO {@code Repository}.
 * 
 * @author JJ Zabkar
 */
@Service
public class FooService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Inject
	FooRepository repo;

	public Foo getFoo(UUID uuid) {

		LOGGER.debug("getFoo({})", uuid);

		Foo foo = repo.findOne(uuid);
		if (foo == null) {
			foo = new Foo();
			foo.setUuid(uuid);
			LOGGER.debug("saving uuid={}", uuid);
			repo.save(foo);
		}
		return foo;
	}

	public List<Foo> getFoos() {
		return repo.findAll();
	}

	public void delete(UUID uuid) {
		Foo found = repo.findOne(uuid);
		if (found != null) {
			repo.delete(found);
		} else {
			throw new IllegalArgumentException("uuid " + uuid.toString() + " not found");
		}
	}

	public Foo save(Foo foo) {
		return repo.save(foo);
	}

	public Foo update(UUID uuid, Foo foo) {
		Foo found = repo.findOne(uuid);
		if (found != null) {
			// found.setName(foo.getName());
			return repo.save(foo); // works because uuid is the same; mongo overwrites
		} else {
			throw new IllegalArgumentException("uuid " + uuid.toString() + " not found");
		}
	}

}
