package com.laversi.schoolapi.controllers.personController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping
public class PersonController {
    
    @PostMapping("person")
    public void postPerson(@RequestBody String json) {
        
        System.out.println(json);
    }
    
    
}
