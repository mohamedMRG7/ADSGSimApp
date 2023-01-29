package com.sim.adsg.adsg.pojo.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiBaseResponse<T> {
    private Boolean status;
    private HttpStatus httpStatuses;
    private  T result ;
}
