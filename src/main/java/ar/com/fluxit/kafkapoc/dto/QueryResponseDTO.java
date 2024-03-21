package ar.com.fluxit.kafkapoc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QueryResponseDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("status")
    private String status;
}
