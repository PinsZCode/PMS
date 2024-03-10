package com.pms.service;





import com.pms.model.Project;
import com.pms.payLoad.ProjectDto;
import javax.validation.Valid;


import java.util.List;

public interface ProjectService {
    Project addProject(@Valid Project project);

    List<ProjectDto> getAllProject();

    ProjectDto getProjectById(long id);

    void deleteById(long id);

    Project updateProject(long id, Project project);
}
