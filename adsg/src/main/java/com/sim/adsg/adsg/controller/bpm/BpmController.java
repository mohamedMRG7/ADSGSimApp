package com.sim.adsg.adsg.controller.bpm;

import com.sim.adsg.adsg.pojo.bpm.common.BpmResponse;
import com.sim.adsg.adsg.pojo.bpm.common.TokenInfo;
import com.sim.adsg.adsg.pojo.bpm.common.invoke.BPMTask;
import com.sim.adsg.adsg.services.bpm.BPMConsumers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/bpm")
public class BpmController {

    @Autowired
    private BPMConsumers bpmConsumers;

    @GetMapping("/login")
    public String authenticate(@PathParam("username") String username ,
                               @PathParam("password") String password ,
                               @RequestParam(value = "onBehalf" , defaultValue = "T") String onBehalf){
        if("F".equalsIgnoreCase(onBehalf))
            return bpmConsumers.authenticate(username,password);

        return bpmConsumers.authenticateOnBehalf(username);
    }

    @PostMapping("/list")
    public BpmResponse queryTasks(@RequestBody TokenInfo tokenInfo){
        return bpmConsumers.queryTasks(tokenInfo);
    }

    @PostMapping("/invoke")
    public boolean invokeAction(@RequestBody BPMTask bpmTask){
        return bpmConsumers.invokeAction(bpmTask);
    }

}
