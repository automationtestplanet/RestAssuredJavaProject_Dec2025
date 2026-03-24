package com.reqres.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Credentials {
	@JsonProperty("UserName")
	private String userName;

	@JsonProperty("Password")
	private String password;

}
