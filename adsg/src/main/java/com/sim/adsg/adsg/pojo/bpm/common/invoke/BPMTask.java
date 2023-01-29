package com.sim.adsg.adsg.pojo.bpm.common.invoke;

import com.sim.adsg.adsg.pojo.bpm.common.TokenInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BPMTask implements Serializable {
    int bpmTaskNumber;
    String action;
    TokenInfo tokenInfo;
}
