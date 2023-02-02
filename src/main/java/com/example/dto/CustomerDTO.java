package com.example.dto;

public class CustomerDTO {
    private String email;
    private String mobile;

    public CustomerDTO() {}

    public CustomerDTO(String email, String mobile) {
        this.email = email;
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
