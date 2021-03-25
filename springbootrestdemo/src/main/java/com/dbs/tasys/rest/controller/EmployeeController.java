package com.dbs.tasys.rest.controller;

import java.net.URI;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dbs.tasys.dao.EmployeeDAO;
import com.dbs.tasys.model.Employee;
import com.dbs.tasys.model.Employees;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController 
{
    @Autowired
    private EmployeeDAO employeeDao;
    
    @GetMapping(path="/", produces = "application/json")
    public Employees getEmployees() 
    {
        return employeeDao.getAllEmployees();
    }
    
    @PostMapping(path= "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addEmployee(
                        @RequestHeader(name = "X-COM-PERSIST", required = true) String headerPersist,
                        @RequestHeader(name = "X-COM-LOCATION", required = false, defaultValue = "ASIA") String headerLocation,
                        @RequestBody Employee employee) 
                 throws Exception 
    {       
        //Generate resource id
        Integer id = employeeDao.getAllEmployees().getEmployeeList().size() + 1;
        employee.setId(id);
        
        //add resource
        employeeDao.addEmployee(employee);
        
        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(employee.getId())
                                    .toUri();
        
        //Send location in response
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/setloglevel/{loglevel}")
    public void setWarnLoglevel(@PathVariable String loglevel) {
        System.out.println("===================================");

        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

        ch.qos.logback.classic.Logger logger = loggerContext.getLogger("com.howtodoinjava");
        System.out.println("com.dbs.tasys" + " current logger level: " + logger.getLevel());
        System.out.println(" You entered: " + loglevel);

        logger.setLevel(Level.toLevel(loglevel));

    }


}
