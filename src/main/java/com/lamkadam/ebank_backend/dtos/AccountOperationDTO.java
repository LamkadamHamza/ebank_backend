package com.lamkadam.ebank_backend.dtos;

import com.lamkadam.ebank_backend.enums.OperationType;
import lombok.Data;

import java.util.Date;

@Data
public class AccountOperationDTO {

    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType type;
    private String description;
}
