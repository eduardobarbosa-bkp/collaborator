package br.com.cds.ebc.collaborator.controller;

import br.com.cds.ebc.collaborator.entity.*;
import br.com.cds.ebc.collaborator.service.CollaboratorService;
import br.com.cds.ebc.collaborator.service.DepartmentService;
import br.com.cds.ebc.collaborator.service.RoleService;
import br.com.cds.ebc.collaborator.service.SkillService;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by Eduardo on 08/01/2017.
 */
@Controller
@Secured({"ROLE_USER"})
@Scope("session")
public class CollaboratorController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private SkillService skillService;
    @Autowired
    private CollaboratorService service;

    private Collaborator collaborator = new Collaborator();

    private Collection<Role> listRoles;

    private Collection<Department> listDepartments;

    private Collection<Skill> listSkills;

    private Collection<Collaborator> listCollaborators;

    private Integer selectedId;

    private Contact contact = new Contact();

    private ContactType[] contactTypes = ContactType.values();

    private Contact selectedContact;

    private String searchTerm;

    private Integer pageIndex;

    private static final Integer PAGE_SIZE = 20;


    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    @PostConstruct
    public void init() {
      loadDependencies();
      resetPagination();
      listCollaborators = service.findPagination(getPageIndex(), PAGE_SIZE);
    }

    private void loadDependencies() {
        listRoles = roleService.findAll();
        listDepartments = departmentService.findAll();
        listSkills = skillService.findAll();
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public Collection<Skill> completeSkill(String query) {
        Collection<Skill> filteredThemes = this.listSkills.stream().filter(it -> it.getName().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
        return filteredThemes;
    }

    public Collection<Department> completeDepartment(String query) {
        Collection<Department> filteredThemes = this.listDepartments.stream().filter(it -> it.getName().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
        return filteredThemes;
    }

    public Collection<Role> completeRole(String query) {
        Collection<Role> filteredThemes = this.listRoles.stream().filter(it -> it.getDescription().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
        return filteredThemes;
    }

    public Collaborator getCollaborator() {
        return collaborator;
    }

    public void setCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
    }

    public Collection<Role> getListRoles() {
        return listRoles;
    }

    public Collection<Department> getListDepartments() {
        return listDepartments;
    }

    public Collection<Skill> getListSkills() {
        return listSkills;
    }

    public Integer getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(Integer selectedId) {
        this.selectedId = selectedId;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public void save(){
        if(this.collaborator.getId() != null && 0 != this.collaborator.getId()){
            service.update(this.collaborator);
        }else{
            this.collaborator.setId(null);
            service.save(this.collaborator);
        }
       newRecord();
       resetPagination();
       this.listCollaborators = service.findPagination(getPageIndex(), PAGE_SIZE);
    }

    private Integer getPageIndex(){
        return this.pageIndex;
    }

    private void resetPagination() {
       this.pageIndex = 0;
    }

    private void updatePageIndex(Integer value){
       this.pageIndex = value;
    }

    public void newRecord(){
        this.collaborator = new Collaborator();
    }

    public void newContact(){
        this.contact = new Contact();
    }

    public void edit(){
        this.collaborator = service.findById(this.selectedId);
    }

    public void delete(){
        Collaborator  collaborator = new Collaborator();
        collaborator.setId(this.selectedId);
        service.delete(collaborator);
        resetPagination();
        listCollaborators = service.findPagination(getPageIndex(), PAGE_SIZE);
    }

    public Contact getSelectedContact() {
        return selectedContact;
    }

    public void setSelectedContact(Contact selectedContact) {
        this.selectedContact = selectedContact;
    }

    public void deleteContact(){
        this.collaborator.getContacts().remove(this.selectedContact);
    }

    public ContactType[] getContactTypes() {
        return contactTypes;
    }

    public void setContactTypes(ContactType[] contactTypes) {
        this.contactTypes = contactTypes;
    }

    public Collection<Collaborator> getListCollaborators() {
        return listCollaborators;
    }

    public void saveContact(){
        if(this.contact.getId() != null && 0 != this.contact.getId()){
            this.collaborator.getContacts().remove(this.contact);
        }else{
            this.contact.setId(null);
            this.contact.setCollaborator(this.collaborator);
            this.collaborator.getContacts().add(this.contact);
        }
        newContact();
    }

    public void search() {
        try {
            resetPagination();
            if(!StringUtils.isEmpty(this.searchTerm)) {
                this.listCollaborators = service.searchPagination(this.searchTerm, getPageIndex(), PAGE_SIZE);
            }else{
                this.listCollaborators = service.findPagination(getPageIndex(), PAGE_SIZE);
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new
                    FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
        }
    }

    public void more(){
        try {
            updatePageIndex(getPageIndex()+1);
            if(StringUtils.isEmpty(this.searchTerm)){
                this.listCollaborators.addAll(service.findPagination(getPageIndex(), PAGE_SIZE));
            }else{
                this.listCollaborators.addAll(service.searchPagination(this.searchTerm, getPageIndex(), PAGE_SIZE));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new
                    FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
        }
    }
}
