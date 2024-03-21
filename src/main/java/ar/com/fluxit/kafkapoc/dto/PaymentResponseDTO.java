package ar.com.fluxit.kafkapoc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentResponseDTO {
    @JsonProperty("id")
    private String id;
}
