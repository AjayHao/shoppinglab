package com.ajayhao.mslab.crawler.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class EntEquityDetailInfo implements Serializable{

    private static final long serialVersionUID = 1L;

    private String entId;

    private String entName;

    private String pullDate;

    private String subjectType;

    private Integer traceLevel;

    private String nodeId;

    private String parentNodeId;

    private String nodeName;

    private String tags;

    private String conName;

    private BigDecimal conProp;

    private BigDecimal realConProp;

    private BigDecimal regAmt;

    private String position;

    private String idCard;

    private String cerType;

    private String address;

    private String creator;

    private Date createdAt;

    private String updater;

    private Date updatedAt;

    private Integer isDelete;

}