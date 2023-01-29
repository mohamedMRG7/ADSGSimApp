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

	@Autowired
	private BPMConsumers bpmConsumers;

	@GetMapping("/")
	public BpmResponse test() throws Exception {
		return bpmConsumers.queryTasks(new TokenInfo("weblogic","45d23a02-fd0f-49e4-aab4-8e9500c35daa;;G;;ZGP8TsoDt/cMvtFKflRs9Dn9RPTZonDxxIq+AIBnn0vIUFX3Cf6HT93C2ueuSdBG3uPpjOgkL0SV7u9661h3W9fwf/OlmHo1a24Nr4FOPS/l8OZbsLIPyCi4t7DdlZHUPToB3LuhCacTlThZpfJSdA=="));
		//return "Hello Adsg App";
	}


}
