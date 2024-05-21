package ourbusinessproject;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ProjectTest {

    private Validator validator;
    private Project project;
    @Mock
    private Enterprise enterprise;


    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        // given : a valid project
        project = new Project();
        project.setTitle("A project");
        project.setDescription("Project description");
        project.setEnterprise(enterprise);
    }

    @Test
    @DisplayName("Test the validation of a valid project")
    public void testProjectValidation() {

        // given : a project with a non empty title and a non empty description

        // expect : project is valid
        assertTrue(validator.validate(project).isEmpty(),"expected no constraints violation");

        // when: the project has an empty description
        project.setDescription("");

        // then: the project is still valid
        assertTrue(validator.validate(project).isEmpty(), "expected no constraints violation");

        // when: the project has no description
        project.setDescription(null);

        // then: the project is still valid
        assertTrue(validator.validate(project).isEmpty(),"expected no constraints violation");

    }

    @Test
    @DisplayName("Test the title of a project cannot be empty or null")
    public void testProjectInvalidation() {

        // given : a project with a non empty title and a non empty description

        // when: the project has an empty title
        project.setTitle("");

        // then: the project is no more valid
        assertFalse(validator.validate(project).isEmpty(), "expected one constraint violation");

        // when: the project has no title
        project.setTitle(null);

        // then: the project is no more valid
        assertFalse(validator.validate(project).isEmpty(),"expected one constraint violation");

    }

    @Test
    @DisplayName("Test the enterprise of a project cannot null")
    public void testProjectMustHaveAnEnterprise() {

        // given : a project with no enterprise
        project.setEnterprise(null);

        // then: the project is no more valid
        assertFalse(validator.validate(project).isEmpty(),"expected one constraint violation");

    }

}