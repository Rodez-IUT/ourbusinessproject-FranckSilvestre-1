package ourbusinessproject;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EnterpriseProjectServiceIntegrationTest {

    @Autowired
    private EnterpriseProjectService enterpriseProjectService;

    private Enterprise enterprise;
    private Project project;

    @BeforeEach
    public void setUp() {
        // given: a persisted enterprise
        enterprise = enterpriseProjectService.newEnterprise(
                "a name",
                "a description",
                "a contact name",
                "acontact@email.com"
        );
    }

    @Test
    @DisplayName("Test a valid project is saved when created")
    public void testProjectIsSavedWhenCreated() {
        // Given: a persisted project
        project = enterpriseProjectService.newProject(
                "a title",
                "a description",
                enterprise
        );
        // then: the project as generated id
        assertNotNull(project.getId());
        // when trying to find the project by id
        Project foundProject = enterpriseProjectService.findProjectById(project.getId());
        // then: the found project is the same as the created project
        assertEquals(project, foundProject);
        // and: the found project has the same enterprise as the created project
        assertEquals(enterprise, foundProject.getEnterprise());
    }

    @Test
    @DisplayName("Test a valid project is saved when created even with a detached enterprise")
    public void testProjectIsSavedWhenCreatedWithDetachedEnterprise() {
        // Given: a detached enterprise
        enterpriseProjectService.getEntityManager().detach(enterprise);
        // Given: a persisted project
        project = enterpriseProjectService.newProject(
                "a title",
                "a description",
                enterprise
        );
        // then: the project as generated id
        assertNotNull(project.getId());
        // when trying to find the project by id
        Project foundProject = enterpriseProjectService.findProjectById(project.getId());
        // then: the found project is the same as the created project
        assertEquals(project, foundProject);
        // and: the found project has the same enterprise as the created project
        assertEquals(enterprise, foundProject.getEnterprise());
    }

    @Test
    @DisplayName("Test a valid enterprise is saved when created")
    public void testEnterpriseIsSavedWhenCreated() {
        // Given: a persisted enterprise
        // then: the enterprise as a generated id
        assertNotNull(enterprise.getId());
        // when: trying to find the enterprise by id
        Enterprise foundEnterprise = enterpriseProjectService.findEnterpriseById(enterprise.getId());
        // then: the found enterprise is the same as the created enterprise
        assertEquals(enterprise, foundEnterprise);
    }

    @Test
    @DisplayName("Test a non valid project is not saved when created")
    public void testProjectIsNotSavedWhenCreated() {
        // when: trying to get a new project with invalid parameters
        // then: ConstraintViolationException is thrown
        assertThrows(ConstraintViolationException.class,
                () -> enterpriseProjectService.newProject("title", "a description", null));
    }

    @Test
    @DisplayName("Test a non valid enterprise is not saved when created")
    public void testEnterpriseIsNotSavedWhenCreated() {
        // when: trying to get a new enterprise with invalid parameters
        // then: ConstraintViolationException is thrown
        assertThrows(ConstraintViolationException.class,
                () -> enterpriseProjectService.newEnterprise(
                "a name",
                "a description",
                "a contact name",
                null
        ));

    }
}