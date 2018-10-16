package com.capgemini.eurekaservice2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;


@EnableEurekaClient
@SpringBootApplication
public class Eurekaservice2Application {

	public static void main(String[] args) {
		SpringApplication.run(Eurekaservice2Application.class, args);
	}
}

@RestController
class EurekaController {
	RestTemplate templet = new RestTemplate();
	@Autowired
	private EurekaClient eurekaClient;
	
	@GetMapping("/")
	public String getStringMessage()
	{
		Application app=eurekaClient.getApplication("config-service");
		InstanceInfo instance=app.getInstances().get(0);
		String url="http://"+instance.getIPAddr()+":"+instance.getPort();
		return templet.getForObject(url+"/message", String.class);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/*String url="http://in-mum-ldvm011.corp.capgemini.com:8089";
	
	@GetMapping("/")
	public String getStringMessage() {
		String s = templet.getForObject(url+"/message", String.class);
		return s;
	}
	*/
	
	
}
