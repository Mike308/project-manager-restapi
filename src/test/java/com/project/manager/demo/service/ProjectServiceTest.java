package com.project.manager.demo.service;

import com.project.manager.demo.model.Project;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.Mockito.mock;


public class ProjectServiceTest {
    private ProjectService projectService = mock(ProjectService.class);

    @Test
    public void shouldInsertNewProject() {
       Project project =  projectService.addProject(new Project("Test", "Test", LocalDate.of(2021, 5, 31)));
        System.out.println(project);
    }
}
