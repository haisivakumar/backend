package com.dbs.tasys.rest.controller;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.dbs.tasys.dao.EmployeeDAO;
import com.dbs.tasys.model.Employee;
import com.dbs.tasys.model.Employees;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/log")
public class LogController
{


    @GetMapping("/setloglevel/{loglevel}")
    public void setWarnLoglevel(@PathVariable String loglevel) {
        System.out.println("===================================");

        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

        ch.qos.logback.classic.Logger logger = loggerContext.getLogger("com.dbs.tasys");
        System.out.println("com.dbs.tasys" + " current logger level: " + logger.getLevel());
        System.out.println(" You entered: " + loglevel);

        logger.setLevel(Level.toLevel(loglevel));

    }


}
