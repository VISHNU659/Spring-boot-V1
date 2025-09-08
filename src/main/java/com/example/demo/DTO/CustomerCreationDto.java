package com.example.demo.DTO;



import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerCreationDto {
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

