package com.sim.adsg.adsg.services.authentication;

import com.sim.adsg.adsg.exceptions.BaseException;
import com.sim.adsg.adsg.model.entities.SecUsersEntity;
import com.sim.adsg.adsg.model.repositories.security.SecUsersRepository;
import com.sim.adsg.adsg.services.bpm.BPMConsumers;
import com.sim.adsg.adsg.services.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    private BPMConsumers bpmConsumers;

    private final JwtService jwtService;

    private final SecUsersRepository repository;

    public String login(String username , String password){

        Optional<SecUsersEntity> byUserCode = repository.findByUserCode(username);

        if(!byUserCode.isPresent())
            throw new BaseException("User not found" , HttpStatus.FORBIDDEN);

        String bpmToken = bpmConsumers.authenticateOnBehalf(username);

        if(bpmToken == null)
            throw new BaseException("User not found on server" , HttpStatus.FORBIDDEN);


        Map<String,Object> claims = new HashMap<>();
        claims.put("bpmToken",bpmToken);

        return jwtService.generateToken(claims,byUserCode.get());
    }
}
