package com.pms.service.impl;



import com.pms.exception.ResourceNotFoundException;
import com.pms.model.Project;
import com.pms.payLoad.ProjectDto;
import com.pms.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddProject() {

        Project project = new Project();
        project.setName("Test Project");
        project.setDescription("Test Description");
        project.setStartDate("Test StartDate");
        project.setEndDate("Test EndDate");
        project.setStatus("Test Status");


        Project savedProject = new Project();
        savedProject.setId(1L);
        savedProject.setName("Test Project");
        savedProject.setDescription("Test Description");
        savedProject.setStartDate("Test Start Date");
        savedProject.setEndDate("Test EndDate");
        savedProject.setStatus("Test Status");


        when(projectRepository.save(project)).thenReturn(savedProject);


        Project returnedProject = projectService.addProject(project);


        assertNotNull(returnedProject);
        assertEquals(savedProject.getId(), returnedProject.getId());
        assertEquals(savedProject.getName(), returnedProject.getName());
        assertEquals(savedProject.getDescription(), returnedProject.getDescription());
        assertEquals(savedProject.getStartDate(),returnedProject.getStartDate());
        assertEquals(savedProject.getEndDate(), returnedProject.getEndDate());
        assertEquals(savedProject.getStatus(), returnedProject.getStatus());

    }
    @Test
    void testGetAllProject() {
        List<Project> projectList = new ArrayList<>();
        Project project1 = new Project();
        project1.setId(1L);
        project1.setName("Project 1");
        project1.setDescription("Description 1");
        project1.setEndDate("End Date 1");
        project1.setStartDate("Start Date 1");
        project1.setStatus("Status 1");
        projectList.add(project1);

        when(projectRepository.findAll()).thenReturn(projectList);

        List<ProjectDto> resultDtoList = projectService.getAllProject();

        assertEquals(1, resultDtoList.size());
        assertEquals(project1.getId(), resultDtoList.get(0).getId());
        assertEquals(project1.getName(), resultDtoList.get(0).getName());
        assertEquals(project1.getDescription(), resultDtoList.get(0).getDescription());
        assertEquals(project1.getStartDate(), resultDtoList.get(0).getStartDate());
        assertEquals(project1.getEndDate(), resultDtoList.get(0).getEndDate());
        assertEquals(project1.getStatus(), resultDtoList.get(0).getStatus());

    }

    @Test
    void testGetProjectById() {
        long id = 1L;
        Project project = new Project();
        project.setId(id);
        project.setName("Test Project");
        project.setDescription("Test Description");
        project.setStartDate("Test StartDate");
        project.setEndDate("Test EndDate");
        project.setStatus("Test Status");

        when(projectRepository.findById(id)).thenReturn(Optional.of(project));

        ProjectDto resultDto = projectService.getProjectById(id);

        assertNotNull(resultDto);
        assertEquals(project.getId(), resultDto.getId());
        assertEquals(project.getName(), resultDto.getName());
        assertEquals(project.getDescription(), resultDto.getDescription());
        assertEquals(project.getStartDate(), resultDto.getStartDate());
        assertEquals(project.getEndDate(), resultDto.getEndDate());
        assertEquals(project.getStatus(), resultDto.getStatus());
    }

    @Test
    void testGetProjectById_ResourceNotFoundException() {
        long id = 1L;

        when(projectRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            projectService.getProjectById(id);
        });
    }

    @Test
    void testDeleteById_Success() {
        long id = 1L;


        when(projectRepository.findById(id)).thenReturn(Optional.of(new Project()));


        projectService.deleteById(id);


        verify(projectRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteById_ResourceNotFoundException() {
        long id = 1L;

        when(projectRepository.findById(id)).thenReturn(Optional.empty());


        assertThrows(ResourceNotFoundException.class, () -> projectService.deleteById(id));


        verify(projectRepository, never()).deleteById(id);
    }
    @Test
    void testUpdateProject() {
        long id = 1L;
        Project project = new Project();
        project.setId(id);
        project.setName("Updated Project");
        project.setDescription("Updated Description");
        project.setStartDate("Updated StartDate");
        project.setEndDate("Updated EndDate");
        project.setStatus("Updated Status");

        when(projectRepository.findById(id)).thenReturn(Optional.of(project));
        when(projectRepository.save(any())).thenReturn(project);

        Project resultProject = projectService.updateProject(id, project);

        assertNotNull(resultProject);
        assertEquals(id, resultProject.getId());
        assertEquals("Updated Project", resultProject.getName());
        assertEquals("Updated Description", resultProject.getDescription());
        assertEquals("Updated StartDate", resultProject.getStartDate());
        assertEquals("Updated EndDate", resultProject.getEndDate());
        assertEquals("Updated Status", resultProject.getStatus());
    }

    @Test
    void testUpdateProject_ResourceNotFoundException() {
        long id = 1L;
        Project project = new Project();

        when(projectRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            projectService.updateProject(id, project);
        });
    }
}