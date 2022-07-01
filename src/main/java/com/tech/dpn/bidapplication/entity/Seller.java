package com.tech.dpn.bidapplication.entity;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Component
public class Seller {
    @Size(min = 5, max = 30, message = "First Name should have character length between 5 and 30 ")
    private String firstName;
    @Size(min = 5, max = 25, message = "Last Name should have character length between 5 and 25 ")
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String pin;
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Phone number must have 10 digits")
    @NotBlank
    private String phone;
    @Email(message = "Email format is not correct, please enter correct email id")
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}