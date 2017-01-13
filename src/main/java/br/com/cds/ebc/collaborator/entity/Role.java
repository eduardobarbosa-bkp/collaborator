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
public class Role implements Serializable {

    private static final long serialVersionUID = 7238707371770814385L;

    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 150, nullable = false)
    @NotBlank(message = "O valor do campo deve ser preenchido!")
    private String description;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return id.equals(role.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
