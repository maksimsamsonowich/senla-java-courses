package com.github.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@JsonAutoDetect
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ErrorDto {

    @JsonProperty(value = "statusCode")
    private int statusCode;

    @JsonProperty(value = "errorMessage")
    private String errorMessage;

}
