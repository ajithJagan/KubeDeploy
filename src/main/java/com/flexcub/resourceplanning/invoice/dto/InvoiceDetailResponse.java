package com.flexcub.resourceplanning.invoice.dto;


import com.flexcub.resourceplanning.invoice.entity.InvoiceStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class InvoiceDetailResponse {

    private Date startDate;

    private Date endDate;

    private Date dueDate;

    private Date submittedDate;

    private int skillPartnerId;

    private InvoiceStatus status;

    private Date invoiceDate;

    private List<WorkEfforts> invoiceData;
    private String comments;
}
