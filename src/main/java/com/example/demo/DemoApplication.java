package com.example.demo;

import com.example.demo.generic.GenericSimpleStack;
import com.example.demo.generic.SimpleStack;
import com.example.demo.generic.SimpleStackString;
import com.example.demo.getraenke.Bier;
import com.example.demo.getraenke.Getraenk;
import com.example.demo.getraenke.Weisswein;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		GenericSimpleStack<String> genericStack = new GenericSimpleStack<String>(10);

		SimpleStack simpleStack = new SimpleStack(10);
		simpleStack.push(42);
		Object o = simpleStack.pop();
		if(o instanceof String) {
			String s = (String) o;
		} else if (o instanceof Integer) {
			Integer i = (Integer) o;
		}



		genericStack.push("Hello");
		String s = (String) genericStack.pop();
		System.out.println(s);
		genericStack.push("World");
		s = (String) genericStack.pop();
		System.out.println(s);

		GenericSimpleStack<Double> genericStack2 = new GenericSimpleStack<Double>(10);
		genericStack2.push(1.2);
		Double x = (Double) genericStack2.pop();
		System.out.println(x);



	}

	public int calculate(List<String> args) throws CustomException {
		try {
			if(Integer.parseInt(args.get(0)) < 9 && Integer.parseInt(args.get(1)) > 4) {
				return 1;
			} else {
				return -1;
			}
		} catch (Exception e) {
			throw new CustomException("Something went wrong");
		}
	}

	public static class CustomException extends Exception {
		CustomException(String message) {
			super(message);
		}
	}




}
