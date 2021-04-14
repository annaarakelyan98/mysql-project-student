package com.students.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class Students {
    @NotNull(message = ("Enter number equals or greater 1"))
    private int id;

    @Size(min = 3, message = "Name must be greater than 3 symbols")
    private String name;

    @NotEmpty
    @Size(max = 40)
    private String surname;

    @Size(max = 25)
    private String city;

    @NotNull
    @Email(message = "Not a valid email")
    private String mail;

    @Override
    public String toString() {
        return "Students{" +
                "id: " + id +
                ", name: " + name +
                ", surname: " + surname +
                ", city: " + city +
                ", mail: " + mail +
                "}";
    }
}
