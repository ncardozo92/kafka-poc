package ar.com.fluxit.kafkapoc.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentRequestDTO {

    @JsonProperty("qr")
    private String qr;

    @JsonProperty("amount")
    private Double amount;
}
