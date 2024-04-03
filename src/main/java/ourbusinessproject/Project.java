package ourbusinessproject;

import jakarta.validation.constraints.NotEmpty;

public class Project {
    @NotEmpty
    private String title;
    private String description;

    /**
     * Set the title
     * @param title yhe new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the title
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the description
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the description
     * @return the description
     */
    public String getDescription() {
        return description;
    }
}
