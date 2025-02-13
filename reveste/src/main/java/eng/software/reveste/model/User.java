package eng.software.reveste.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users") // Mudando o nome da tabela (pois 'user' é palavra reservada)
public class User {
    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera o ID automaticamente
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String cpf;
    private LocalDate dateBirth;
    private String username;
    private String password;

    //Construtor padrão (obrigatório para JPA)
    public User() {
        this(null,null,null,null,null,null,null);
    }

    public User(String name, String lastName, String email, String cpf, LocalDate dateBirth, String username,
            String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.cpf = cpf;
        this.dateBirth = dateBirth;
        this.username = username;
        this.password = password;
    }


    //Getters e Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public LocalDate getDateBirth() {
        return dateBirth;
    }
    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    
}
