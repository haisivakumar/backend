package com.dbs.tasys.dao;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.springframework.stereotype.Repository;

import com.dbs.tasys.model.Employee;
import com.dbs.tasys.model.Employees;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;

@Repository
public class EmployeeDAO 
{
    private static Employees list = new Employees();
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDAO.class);

    static 
    {
        list.getEmployeeList().add(new Employee(1, "Lokesh", "Gupta", "howtodoinjava@gmail.com"));
        list.getEmployeeList().add(new Employee(2, "Alex", "Kolenchiskey", "abc@gmail.com"));
        list.getEmployeeList().add(new Employee(3, "David", "Kameron", "titanic@gmail.com"));
    }
    
    public Employees getAllEmployees() 
    {
        String value = "test" ;
        LOGGER.trace("doStuff needed more information - {}", value);
        LOGGER.debug("doStuff needed to debug - {}", value);
        LOGGER.info("doStuff took input - {}", value);
        LOGGER.warn("doStuff needed to warn - {}", value);
        LOGGER.error("doStuff encountered an error with value - {}", value);

        return list;
    }

    public void addEmployee(Employee employee) {
        list.getEmployeeList().add(employee);
    }
}
