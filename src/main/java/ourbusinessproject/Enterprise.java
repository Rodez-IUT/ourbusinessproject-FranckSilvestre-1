package ourbusinessproject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Collection;
import java.util.HashSet;

/**
 * An enterprise is a company that is a project partner.
 *
 */
@Entity
public class Enterprise {
    @NotEmpty private String name;
    @Size(min = 10) private String description;
    @NotEmpty private String contactName;
    @NotEmpty @Email
    private String contactEmail;
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(mappedBy = "enterprise") @JsonIgnore
    private Collection<Project> projects;

    /**
     * Set the name of the enterprise
     * @param name the name of the enterprise
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the name of the enterprise
     * @return the name of the enterprise
     */
    public String getName() {
        return name;
    }

    /**
     * Set the description of the enterprise
     * @param description the description of the enterprise
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the description of the enterprise
     * @return the description of the enterprise
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the contact name of the enterprise
     * @param contactName the contact name of the enterprise
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Get the contact name of the enterprise
     * @return the contact name of the enterprise
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Set the contact email of the enterprise
     * @param contactEmail the contact email of the enterprise
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * Get the contact email of the enterprise
     * @return the contact email of the enterprise
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * Get the id of the enterprise
     * @return the id of the enterprise
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the projects of the enterprise
     * @return the projects of the enterprise
     */
    public Collection<Project> getProjects() {
        return projects;
    }

    /**
     * Add a project to the enterprise
     * @param project the project to add
     */
    public void addProject(Project project) {
        if (projects == null)
            projects = new HashSet<>();
        this.projects.add(project);
    }
}
