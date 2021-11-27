package com.github.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonAutoDetect
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {

    @JsonProperty(value = "statusCode")
    private int statusCode;

    @JsonProperty(value = "errorMessage")
    private String errorMessage;

}
