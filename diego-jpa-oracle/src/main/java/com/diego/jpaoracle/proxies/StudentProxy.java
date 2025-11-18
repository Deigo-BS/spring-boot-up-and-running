package com.diego.jpaoracle.proxies;

import com.diego.jpaoracle.model.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "students",
             url = "${api.service-url}")
public interface StudentProxy {

    @GetMapping("/student")
    Student getStudent();

}
