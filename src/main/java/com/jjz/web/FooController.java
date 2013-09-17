package com.jjz.web;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@ResponseBody
	public Foo getFooByUuid(@PathVariable("uuid") UUID uuid) {
		return svc.getFoo(uuid);
	}

	@RequestMapping(value = "/foo", method = GET)
	@ResponseBody
	public Foo getNewOne() {
		UUID one = UUID.randomUUID();
		Foo foo = getFooByUuid(one);
		foo.setName("Hello from FooController at " + new Date());
		svc.save(foo);
		return foo;
	}

	@RequestMapping(value = "/foo/all", method = GET)
	@ResponseBody
	public List<Foo> getFoos() {
		return svc.getFoos();
	}

	@RequestMapping(value = "/foo/{uuid}", method = PUT)
	@ResponseBody
	public Foo put(@PathVariable("uuid") UUID uuid, @RequestBody Foo foo) {
		return svc.update(uuid, foo);
	}

	@RequestMapping(value = "/foo", method = POST)
	@ResponseBody
	public Foo create(@RequestBody Foo foo) {
		return svc.save(foo);
	}

	@RequestMapping(value = "/foo/{uuid}", method = DELETE)
	@ResponseBody
	public void delete(@PathVariable("uuid") UUID uuid) {
		svc.delete(uuid);
	}

}
