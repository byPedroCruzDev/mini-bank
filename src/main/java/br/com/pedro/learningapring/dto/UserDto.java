package br.com.pedro.learningapring.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto {
    @NotEmpty(message = "Name cannot be empty")
    @Size(max = 92, message = "Name must be lower than 92 characters long")
    private String name;
    @NotEmpty(message = "Cpf cannot be empty")
    @Size(max = 11, message = "Cpf must be lower than 11 characters long")
    private String password;
    @NotEmpty(message = "Email cannot be empty")
    @Size(max = 62, message = "Email must be lower than 62 characters long")
    private String type;
    @NotEmpty(message = "Password cannot be empty")
    private String email;
    @NotEmpty(message = "Email cannot be empty")
    @Pattern(regexp = "(COMMON|SELLER)", message = "User type must be COMMON or SELLER")
    private String cpf;

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }
}
