package com.reqres.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleUserDetails {
    private UserData data;
    private Support support;
    @JsonProperty("_meta")
    private Meta meta;
}

