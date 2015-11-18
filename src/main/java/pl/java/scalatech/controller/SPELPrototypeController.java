package pl.java.scalatech.controller;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.java.scalatech.components.City;
import pl.java.scalatech.components.PropMessages;

@Controller
public class SPELPrototypeController {

	@Autowired
	private PropMessages propMessages;

	@Value("#{ systemProperties }")
	private Properties properties;

	@Value("#{ T(java.lang.Math).random() * 100.0 }")
	private double randomNumber;


	@Value("#{propMessages.getValueByKey('piotr')}")
	private String key;

	@Value("#{propMessages.getProperKey()}")
	private String key1;

	@Value("#{'Slawek Borowiec test ' +  propMessages.getValueByKey('piotr')}")
	private String key2;

	@Value("#{cities[2]}")
	private City c1;

	@Value("#{cities[T(java.lang.Math).random() * cities.size()]}")
	private City c2;
	
	@Autowired
	private String country;

	@RequestMapping(value = "/spelPrototypeTest")
	public @ResponseBody
	String show() {
		StringBuilder sb = new StringBuilder();
		sb.append("------------------PROTOTYPE -------------------");
		sb.append("\n <br/>");
		/*
		 * for(Entry<Object, Object> o : properties.entrySet()){
		 * sb.append(o.getKey()).append(" :  ").append(o.getValue());
		 * sb.append("\n <br/>");
		 * }
		 */
		sb.append("\n <br/>");
		sb.append(randomNumber + "  " + key + " " + key1 + " " + key2);
		sb.append("\n <br/>");
		sb.append(country);
		sb.append("\n <br/>");

		sb.append("city C1  : ").append(c1);

		sb.append("\n <br/>");

		sb.append("city C2  : ").append(c2);

		sb.append("\n <br/>");

		/*
		 * sb.append("city C3  : ").append(c3);
		 * sb.append("\n <br/>");
		 * sb.append("city C4  : ").append(c5);
		 * sb.append("\n <br/>");
		 * sb.append("city C4  : ").append(c5);
		 */

		return sb.toString();

	}
}
