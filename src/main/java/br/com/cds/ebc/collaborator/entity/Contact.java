package br.com.cds.ebc.collaborator.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Eduardo on 07/01/2017.
 */
@Entity
public class Contact implements Serializable {

    private static final long serialVersionUID = 5665431616398923376L;

    @Id
    @GeneratedValue
    private Integer id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo obrigatorio")
    private ContactType contactType;

    @Column(length = 150, nullable = false)
    @NotBlank(message = "O valor do campo deve ser preenchido!")
    private String description;

    @ManyToOne
    private Collaborator collaborator;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collaborator getCollaborator() {
        return collaborator;
    }

    public void setCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (id != null ? !id.equals(contact.id) : contact.id != null) return false;
        if (contactType != contact.contactType) return false;
        if (description != null ? !description.equals(contact.description) : contact.description != null) return false;
        return !(collaborator != null ? !collaborator.equals(contact.collaborator) : contact.collaborator != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (contactType != null ? contactType.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (collaborator != null ? collaborator.hashCode() : 0);
        return result;
    }
}
