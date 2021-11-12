package com.verborum.fefuchbackend.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/tea-pot")
public class TeaPotController {

    @GetMapping(path = "/make-coffee")
    public @ResponseBody ResponseEntity<String> getAllPosts() {
        return new ResponseEntity<>("I'm a teapot!", HttpStatus.I_AM_A_TEAPOT);
    }

}
