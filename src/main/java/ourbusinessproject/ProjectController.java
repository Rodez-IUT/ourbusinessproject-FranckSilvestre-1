package ourbusinessproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This controller manages the projects.
 */
@RestController
public class ProjectController {
    private final EnterpriseProjectService enterpriseProjectService;

    /**
     * Creates a new project controller.
     * @param enterpriseProjectService the service to use to manage the projects
     */
    public ProjectController(@Autowired EnterpriseProjectService enterpriseProjectService) {
        this.enterpriseProjectService = enterpriseProjectService;
    }

    /**
     * @return the list of all projects with their enterprises
     */
    @RequestMapping("/api/projects")
    public List<Project> findAllProjectsWithEnterprises() {
        return enterpriseProjectService.findAllProjects();
    }
}
