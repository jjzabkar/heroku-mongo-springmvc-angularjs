package com.jjz.web;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import com.jjz.domain.Foo;
import com.jjz.service.FooService;

/**
 * {@link Controller} that declares {@link RequestMapping} endpoints to allow a client to interact with a business {@code Service}.
 * 
 * @author JJ Zabkar
 */
@Controller
public class FooController {

	@Inject
	FooService svc;

	@RequestMapping(value = "/foo/{uuid}", method = GET)
	@ResponseStatus(OK)
	@ResponseBody
	public Foo getFooByUuid(@PathVariable("uuid") UUID uuid) {
		return svc.getFoo(uuid);
	}

	@RequestMapping(value = "/foo/new", method = POST)
	@ResponseStatus(CREATED)
	@ResponseBody
	public Foo create() {
		UUID one = UUID.randomUUID();
		Foo foo = getFooByUuid(one);
		foo.setName("Hello from FooController at " + new Date());
		svc.save(foo);
		return foo;
	}

	@RequestMapping(value = "/foo/all", method = GET)
	@ResponseStatus(OK)
	@ResponseBody
	public List<Foo> getFoos() {
		return svc.getFoos();
	}

	@RequestMapping(value = "/foo/{uuid}", method = PUT)
	@ResponseStatus(ACCEPTED)
	// or @ResponseStatus(value = NOT_IMPLEMENTED, reason = "'update' not implemented yet")
	@ResponseBody
	public Foo put(@PathVariable("uuid") UUID uuid, @RequestBody Foo foo) {
		return svc.update(uuid, foo);
	}

	@RequestMapping(value = "/foo", method = POST)
	@ResponseStatus(CREATED)
	@ResponseBody
	public Foo create(@RequestBody Foo foo) {
		return svc.save(foo);
	}

	@RequestMapping(value = "/foo/{uuid}", method = DELETE)
	@ResponseStatus(NO_CONTENT)
	public void delete(@PathVariable("uuid") UUID uuid) {
		svc.delete(uuid);
	}

}
