package br.com.pedro.learningapring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 96, nullable = false)
    private String name;
    @Column(length = 11, nullable = false, unique = true)
    private String cpf;
    @Column(columnDefinition = "TEXT",nullable = false)
    private String password;
    @Column(length = 63, unique = true, nullable = false)
    private String email;
    @Column(length = 6, nullable = false)
    private String type;
    @Column(columnDefinition = "DECIMAL DEFAULT 0.0")
    private float balance;

    public  User(){

    }
    public User(String name, String cpf, String password, String email, String type) {
        this.name = name;
        this.cpf = cpf;
        this.password = password;
        this.email = email;
        this.type = type;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public float getBalance() {
        return balance;
    }
    //auxiliaa
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                ", balance=" + balance +
                '}';
    }
}
