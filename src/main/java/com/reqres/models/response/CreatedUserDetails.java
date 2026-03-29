package com.reqres.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatedUserDetails {
    private String name;
    private String job;
    private String id;
    private String createdAt;

    @JsonProperty("_meta")
    private Meta meta;
}


