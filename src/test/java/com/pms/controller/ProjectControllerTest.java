package com.pms.controller;


import com.pms.model.Project;
import com.pms.payLoad.ProjectDto;
import com.pms.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class ProjectControllerTest {

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    @Mock
    private BindingResult bindingResult;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateProject_WithNoFieldErrors() {

        Project project = new Project();
        project.setName("Test Project");
        project.setDescription("Test Description");
        project.setStartDate("Test Start Date");
        project.setEndDate("Test End Date");
        project.setStatus("Test Status");


        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getFieldError()).thenReturn(null);


        ResponseEntity<?> responseEntity = projectController.createProject(project, bindingResult);


        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Validation error", responseEntity.getBody());
    }

    @Test
    void testGetAllProject() {
        List<ProjectDto> projectList = new ArrayList<>();
        when(projectService.getAllProject()).thenReturn(projectList);

        ResponseEntity<List<ProjectDto>> response = projectController.getAllProject();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(projectList, response.getBody());
        verify(projectService, times(1)).getAllProject();
    }

    @Test
    void testGetProjectById() {
        long id = 1L;
        ProjectDto projectDto = new ProjectDto();
        when(projectService.getProjectById(id)).thenReturn(projectDto);

        ResponseEntity<ProjectDto> response = projectController.getProjectById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(projectDto, response.getBody());
        verify(projectService, times(1)).getProjectById(id);
    }

    @Test
    void testDeleteById() {
        long id = 1L;

        ResponseEntity<String> response = projectController.deleteById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Record deleted successfully", response.getBody());
        verify(projectService, times(1)).deleteById(id);
    }

    @Test
    void testUpdateProject() {
        long id = 1L;
        Project project = new Project();
        when(projectService.updateProject(id, project)).thenReturn(project);

        ResponseEntity<Project> response = projectController.updateProject(id, project);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(project, response.getBody());
        verify(projectService, times(1)).updateProject(id, project);
    }
}
