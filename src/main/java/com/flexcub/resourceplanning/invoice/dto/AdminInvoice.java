package com.flexcub.resourceplanning.invoice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AdminInvoice {
    private int invoiceDataId;
    private int ownerId;
    private String ownerName;
    private String position;
    private String client;
    private int totalHours;
    private double actualAmount;
    private int amount;
    private LocalDate invoiceCreationDate;
    private LocalDate invoiceDueDate;
    private int rateCard;

}
