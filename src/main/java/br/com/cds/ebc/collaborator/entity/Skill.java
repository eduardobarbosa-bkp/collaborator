package br.com.cds.ebc.collaborator.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Eduardo on 07/01/2017.
 */
@Entity
public class Skill implements Serializable {

    private static final long serialVersionUID = 7407787385403588100L;

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

        Skill skill = (Skill) o;

        return id.equals(skill.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }


}
