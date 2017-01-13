package br.com.cds.ebc.collaborator.entity;

/**
 * Created by Eduardo on 07/01/2017.
 */
public enum ContactType {

    TELEPHONE("Telefone fixo"), CELLPHONE("Telefone celular"), EMAIL("E-mail"), FACEBOOK("Facebook"), LINKEDIN("Linkedin");

    private final String label;

    ContactType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
