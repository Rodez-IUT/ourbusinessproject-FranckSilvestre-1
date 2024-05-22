package ourbusinessproject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class is a service that allows to create and find projects and enterprises.
 */
@Service
public class EnterpriseProjectService {

    /**
     * The entity manager.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
    public EnterpriseProjectService() {
    }

    /**
     * Create a new enterprise project service.
     * @param entityManager the entity manager
     */
    public EnterpriseProjectService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Get the entity manager.
     * @return the entity manager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Create a new project with a title and a description.
     * @param aTitle the title of the project
     * @param aDescription the description of the project
     * @return the new project
     */
    public Project newProject(String aTitle, String aDescription, Enterprise enterprise) {
        Project project = new Project();
        project.setTitle(aTitle);
        project.setDescription(aDescription);
        project.setEnterprise(enterprise);
        entityManager.persist(project);
        entityManager.flush();
        enterprise.addProject(project);
        return project;
    }

    /**
     * Create a new enterprise with a name, a description, a contact name and a mail.
     * @param aName the name of the enterprise
     * @param aDescription the description of the enterprise
     * @param aContactName the contact name of the enterprise
     * @param mail the mail of the enterprise
     * @return the new enterprise
     */
    public Enterprise newEnterprise(String aName, String aDescription, String aContactName, String mail) {
        Enterprise enterprise = new Enterprise();
        enterprise.setName(aName);
        enterprise.setDescription(aDescription);
        enterprise.setContactName(aContactName);
        enterprise.setContactEmail(mail);
        entityManager.persist(enterprise);
        entityManager.flush();
        return enterprise;
    }

    /**
     * Find a project by its id.
     *
     * @param anId the id of the project
     * @return the found project or null if no project is found
     */
    public Project findProjectById(Long anId) {
        return entityManager.find(Project.class, anId);
    }

    /**
     * Find an enterprise by its id.
     *
     * @param anId the id of the enterprise
     * @return the found enterprise or null if no enterprise is found
     */
    public Enterprise findEnterpriseById(Long anId) {
        return entityManager.find(Enterprise.class, anId);
    }

    /**
     * Find all projects.
     *
     * @return the list of all projects ordered by title
     */
    public List<Project> findAllProjects() {
        String query = "select p from Project p order by p.title";
        TypedQuery<Project> typedQuery = entityManager.createQuery(query, Project.class);
        return typedQuery.getResultList();
    }
}
