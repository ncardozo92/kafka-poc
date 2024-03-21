package ar.com.fluxit.kafkapoc.entity;

import ar.com.fluxit.kafkapoc.util.Status;
import lombok.Data;

@Data
public class Payment {
    private String id;
    private String qr;
    private Double amount;
    private Status status;
}
