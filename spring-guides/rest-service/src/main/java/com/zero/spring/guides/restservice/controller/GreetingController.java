package com.zero.spring.guides.restservice.controller;

import com.zero.spring.guides.restservice.entity.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * https://spring.io/guides/gs/rest-service/
 *
 * This controller is concise and simple, but there is plenty going on under the hood. We break it down step by step.
 * 这个controller很简单，但是背后有很多工作要做，我们将一步一步的拆解它。
 */
@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();


	/**
	 * The @GetMapping annotation ensures that HTTP GET requests to /greeting are mapped to the greeting() method.
	 * @GetMapping 注解保证这个http get请求到映射到greeting()方法。
	 * There are companion annotations for other HTTP verbs (e.g. @PostMapping for POST). There is also a @RequestMapping annotation that they all derive from, and can serve as a synonym (e.g. @RequestMapping(method=GET)).
	 * 还可以传递其他的http请求，（例：@PostMapping post请求）。还有一个@RequestMapping注解，@GetMapping、@PostMapping都是从这个注解派生出去的，并且可以使用@RequestMapping达到同样的效果（例：@RequestMapping(method=GET) = @GetMapping）
	 *
	 * @RequestParam binds the value of the query string parameter name into the name parameter of the greeting() method. If the name parameter is absent in the request, the defaultValue of World is used.
	 * @RequestParam注解将查询参数中的name绑定到greeting()方法的name上中。如果参数name为空，就会使用默认值defaultValue的值，也就是`World`。
	 *
	 * AtomicLong 是用来保证线程安全的，有兴趣可以了解下java.util.concurrent.*;
	 */
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}