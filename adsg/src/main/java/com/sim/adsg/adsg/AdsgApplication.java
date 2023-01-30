package com.sim.adsg.adsg;

import com.sim.adsg.adsg.exceptions.BaseException;
import com.sim.adsg.adsg.pojo.bpm.common.BpmResponse;
import com.sim.adsg.adsg.pojo.bpm.common.TokenInfo;
import com.sim.adsg.adsg.services.bpm.BPMConsumers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.prefs.BackingStoreException;

@SpringBootApplication
@RestController
public class AdsgApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdsgApplication.class, args);
	}


	@GetMapping("/")
	public String welcomePage(){
		return "ADSG Spring Application";
	}


}
