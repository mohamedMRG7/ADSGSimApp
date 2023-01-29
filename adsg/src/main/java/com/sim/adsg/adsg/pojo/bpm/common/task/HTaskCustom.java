package com.sim.adsg.adsg.pojo.bpm.common.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HTaskCustom implements Serializable {
    private int number;

    private String id;
    private String title;
    private String state;
    private String outcome;
    private int priority;
    private String creator;
    private String acquirer;
    private Date createDate;
    private Date expiryDate;
    private Date updateDate;
    private String instanceid;
    private String flowId;
    private String compositeInstanceid;
    private Date assignedDate;
    private String processName;
    private String activityName;
    private String activityId;
    private String swimlaneRole;
    private String assigneeStr;
    private boolean fyi;
    private int assigneesNumber;
    private Map<String,Map[]> availablectionsMap;
    private String subProcessCode;
    private int requestId;
    private int circleId;
    private String circleName;
    private String beneficiaryCivilId;
    private String beneficiaryName;
    private String requesterCivilId;
    private String requesterName;
    private String reviewedUsers;
    private String descriptionAr;
    private String descriptionEn;
    private List<String> activeActions;


}
