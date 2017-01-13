package br.com.cds.ebc.collaborator.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Eduardo on 07/01/2017.
 */
@Entity
public class User implements Serializable{

    private static final long serialVersionUID = -3589914567448793712L;

    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 255, unique = true, nullable = false)
    @NotBlank(message = "O valor do campo deve ser preenchido!")
    private String username;

    @Column(length = 5000)
    private String password;

    @Column(length = 255, nullable = false)
    @NotBlank(message = "O valor do campo deve ser preenchido!")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id.equals(user.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
