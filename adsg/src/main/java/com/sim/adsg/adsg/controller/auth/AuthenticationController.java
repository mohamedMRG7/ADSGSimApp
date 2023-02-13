package com.sim.adsg.adsg.controller.auth;

import com.sim.adsg.adsg.exceptions.pojo.ExceptionResponse;
import com.sim.adsg.adsg.model.repositories.security.SecUsersRepository;
import com.sim.adsg.adsg.pojo.common.response.ApiBaseResponse;
import com.sim.adsg.adsg.services.authentication.AuthService;
import com.sim.adsg.adsg.services.bpm.BPMConsumers;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/")
public class AuthenticationController {

    private final AuthService authService;

    @GetMapping("/login")
    public ResponseEntity<ApiBaseResponse> authenticate(@PathParam("username") String username ,
                                                        @PathParam("password") String password ){

        return new ResponseEntity<>
                (new ApiBaseResponse<>(Boolean.TRUE, HttpStatus.OK,authService.login(username,password))
                       ,HttpStatus.OK);
    }
}
