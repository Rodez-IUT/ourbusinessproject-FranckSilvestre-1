package ourbusinessproject;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This service is responsible for initializing the project data.
 */
@Service
public class InitializationService {
    private Project project1E1;
    private Project project1E2;
    private Project project2E1;
    private Enterprise enterprise1;
    private Enterprise enterprise2;

    /**
     * This method initializes the project data.
     */
    @Autowired
    private EnterpriseProjectService enterpriseProjectService;

    /**
     * Initialization of the initial list of projects
     */
    @Transactional
    public void initProjects() {
        initEnterprise();
        project1E1 = enterpriseProjectService.newProject("p1E1","P1E1 desc",enterprise1);
        project1E2 = enterpriseProjectService.newProject("p1E2","P1E2 desc",enterprise2);
        project2E1 = enterpriseProjectService.newProject("p2E1","P2E1 desc",enterprise1);
    }

    private void initEnterprise() {
        // enterprise 1
        enterprise1 = enterpriseProjectService.newEnterprise(
                "MyComp1",
                "My comp1 description",
                "comp1 contact name",
                "comp1@com.com");
        // enterprise 2
        enterprise2 = enterpriseProjectService.newEnterprise(
                "MyComp2",
                "My comp2 description",
                "comp2 contact name",
                "comp2@com.com");
    }

    /**
     * @return the project1E1
     */
    public Project getProject1E1() {
        return project1E1;
    }

    /**
     * @return the project1E2
     */
    public Project getProject1E2() {
        return project1E2;
    }

    /**
     * @return the project2E1
     */
    public Project getProject2E1() {
        return project2E1;
    }

    /**
     * @return the enterprise1
     */
    public Enterprise getEnterprise1() {
        return enterprise1;
    }

    /**
     * @return the enterprise2
     */
    public Enterprise getEnterprise2() {
        return enterprise2;
    }
}
