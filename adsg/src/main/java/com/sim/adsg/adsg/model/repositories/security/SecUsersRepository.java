package com.sim.adsg.adsg.model.repositories.security;

import com.sim.adsg.adsg.model.entities.SecUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecUsersRepository extends JpaRepository<SecUsersEntity,String> {

    Optional<SecUsersEntity> findByUserCode(String userCode);
}
