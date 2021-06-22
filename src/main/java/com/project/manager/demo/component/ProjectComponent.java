package com.project.manager.demo.component;

import com.project.manager.demo.model.Project;
import com.project.manager.demo.service.ProjectService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class ProjectComponent {
    private final ProjectService projectService;

    public ProjectComponent(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostConstruct
    private void addTestProject() {
        Project project =  projectService.addProject(new Project("Test", "Test", LocalDate.of(2021, 6, 1)));
        System.out.println(project);
    }
}
