package com.pms.service.impl;

import com.pms.exception.ResourceNotFoundException;
import com.pms.model.Project;
import com.pms.payLoad.ProjectDto;
import com.pms.repository.ProjectRepository;
import com.pms.service.ProjectService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project addProject(@Valid Project project) {

        Project saved = projectRepository.save(project);

        project.setId(saved.getId());
        project.setDescription(saved.getDescription());
        project.setStartDate(saved.getStartDate());
        project.setEndDate(saved.getEndDate());
        project.setStatus(saved.getStatus());

        return project;
    }

    @Override
    public List<ProjectDto> getAllProject() {
        List<Project> allProjects = projectRepository.findAll();
        List<ProjectDto> projectDtoList = new ArrayList<>();
        for (Project projectEntity : allProjects) {
            ProjectDto projectDto = new ProjectDto();
            projectDto.setId(projectEntity.getId());
            projectDto.setName(projectEntity.getName());
            projectDto.setDescription(projectEntity.getDescription());
            projectDto.setStartDate(projectEntity.getStartDate());
            projectDto.setEndDate(projectEntity.getEndDate());
            projectDto.setStatus(projectEntity.getStatus());
            projectDtoList.add(projectDto);
        }
        return projectDtoList;
    }

    @Override
    public ProjectDto getProjectById(long id) {
        Project projectEntity = projectRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("id with " + id + " is not available ")
        );
        if(projectEntity !=null){
            ProjectDto dto = new ProjectDto();
            dto.setId(projectEntity.getId());
            dto.setName(projectEntity.getName());
            dto.setDescription(projectEntity.getDescription());
            dto.setStartDate(projectEntity.getStartDate());
            dto.setEndDate(projectEntity.getEndDate());
            dto.setStatus(projectEntity.getStatus());
            System.out.println(projectEntity.getStatus());
            return dto;
        }
        return null;

    }

    @Override
    public void deleteById(long id) {
        Project idPresent = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not present"));
        if(idPresent!=null){
            projectRepository.deleteById(id);
        }
    }

    @Override
    public Project updateProject(long id, Project project) {
        Project updateId = projectRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("id not found")
        );
        if (updateId != null){
            project.setId(id);
            Project saved = projectRepository.save(project);
            return saved;
        }
        return null;
    }


}
