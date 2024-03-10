package com.pms.controller;

import com.pms.model.Project;
import com.pms.payLoad.ProjectDto;
import com.pms.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api/pms")
@Api(tags = "Project Management System", description = "Operations related to project management")
public class ProjectController {
    @Autowired
    public ProjectService projectService;


    @PostMapping("/add")
    @ApiOperation("Add a new project")
    public ResponseEntity<?> createProject(@Valid @RequestBody Project project, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            String errorMessage = "Validation error";
            if(bindingResult.getFieldError() != null) {
                errorMessage = bindingResult.getFieldError().getDefaultMessage();
            }
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Project project1 = projectService.addProject(project);
        return new ResponseEntity<>(project1, HttpStatus.CREATED);
    }

    @GetMapping("/read")
    @ApiOperation("Get all project")
    public ResponseEntity<List<ProjectDto>> getAllProject(){
        List<ProjectDto> allProject = projectService.getAllProject();
        return new ResponseEntity<>(allProject,HttpStatus.OK);

    }

    @GetMapping("/read/{id}")
    @ApiOperation("Get a project by ID")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable long id ){
        ProjectDto projectById = projectService.getProjectById(id);
        return new ResponseEntity<>(projectById,HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    @ApiOperation("Delete a project by ID")
    public ResponseEntity<String> deleteById(@PathVariable long id){
        projectService.deleteById(id);
        return new ResponseEntity<>("Record deleted successfully",HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    @ApiOperation("Update a project by ID")
    public ResponseEntity<Project> updateProject(@PathVariable long id, @RequestBody Project project){
        Project project1 = projectService.updateProject(id, project);
        return  new ResponseEntity<>(project1,HttpStatus.OK);
    }
}

