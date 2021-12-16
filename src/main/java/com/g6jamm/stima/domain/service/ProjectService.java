package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.ProjectRepository;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Headproject;
import com.g6jamm.stima.domain.model.User;

import java.time.LocalDate;
import java.util.List;

public class ProjectService {

    private final ProjectRepository PROJECT_REPOSITORY;

    public ProjectService(ProjectRepository projectRepository) {
        this.PROJECT_REPOSITORY = projectRepository;
    }

    /**
     * Method for getting a list of projects based on the user.
     * This forwards the request to the repository.
     *
     * @author Mathias
     */
    public List<Headproject> getProjects(User user) throws SystemException {
        return PROJECT_REPOSITORY.getProjects(user);
    }

    /**
     * Method for getting a project based on id and the user.
     * This gets all projects for the user and loops through the list a check if the id exists.
     *
     * @author Mathias
     */
    public Headproject getProjectById(User user, int projectID) throws SystemException {
        for (Headproject project : getProjects(user)) {
            if (projectID == project.getId()) {
                return project;
            }
        }

        return null;
    }

    /**
     * Method for creating a new headproject Here we create an initial object for us to use in the repository.
     * The new object is then passed to the repository to create the rest of the object.
     * <p>
     * a complete object with id is then returned.
     *
     * @author Mathias
     */
    public Headproject createProject(
            String name, LocalDate startDate, LocalDate endDate, String projectColor, User user)
            throws SystemException {

        Headproject project =
                new Headproject.ProjectBuilder()
                        .projectName(name)
                        .startDate(startDate)
                        .endDate(endDate)
                        .colorCode(projectColor)
                        .build();

        return PROJECT_REPOSITORY.createProject(project, user);
    }

    /**
     * Method for editing a project.
     * A new object is build here with the new information. This headproject is then passed to the repository.
     *
     * @author Mathias
     */
    public void editProject(
            int projectId, String name, LocalDate startDate, LocalDate endDate, String projectColor)
            throws SystemException {

        Headproject project =
                new Headproject.ProjectBuilder()
                        .projectId(projectId)
                        .projectName(name)
                        .startDate(startDate)
                        .endDate(endDate)
                        .colorCode(projectColor)
                        .build();

        PROJECT_REPOSITORY.editProject(project);
    }

    /**
     * Method for forwading the delete request to the repository.
     *
     * @author Mathias
     */
    public void deleteProject(int projectId) throws SystemException {
        PROJECT_REPOSITORY.deleteProject(projectId);
    }
}
