package com.sim.adsg.adsg.pojo.bpm.common;

import com.sim.adsg.adsg.pojo.bpm.common.task.HTaskCustom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BpmResponse  implements Serializable {

    boolean status;
    String message;
    TokenInfo token;
    List<HTaskCustom> result;
}
