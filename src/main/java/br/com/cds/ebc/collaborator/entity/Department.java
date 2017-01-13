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
public class Department implements Serializable {

    private static final long serialVersionUID = 2680235973161783969L;

    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 150, nullable = false)
    @NotBlank(message = "O valor do campo deve ser preenchido!")
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

        Department that = (Department) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
