package com.reqres.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class AllUsersDetails {

    private Integer page;
    private Integer per_page;
    private Integer total;
    private Integer total_pages;
    private List<UserData> data;
    private Support support;

    @JsonProperty("_meta")
    private Meta meta;
}

