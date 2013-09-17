package com.jjz.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/**
 * Domain object managed by a data {@code Repository}.
 * 
 * @author JJ Zabkar
 */
@Document
public class Foo {

	@Id
	private UUID uuid;

	private String name;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
