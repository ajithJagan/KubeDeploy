package com.deploy.kube.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kube")
public class KubeController {

    @GetMapping(value = "/get")
    public String get() {
        return "Welcome to Kubernetes ....";
    }


}
