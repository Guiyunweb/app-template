package org.example.project.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by myy on 2021/11/19.
 */
@RestController
public class AuthController {

    @RequestMapping("")
    public String index() {
        return "hello index";
    }

}
