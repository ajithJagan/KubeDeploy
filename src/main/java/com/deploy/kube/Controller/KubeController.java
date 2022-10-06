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

    @GetMapping(value = "/gett")
    public String gett() {
        return "Welcome to Docker ....";
    }

    @GetMapping(value = "/get")
    public String neq() {
        return "Welcome to Kubernetes ....";
    }

    @GetMapping(value = "/gett")
    public String ge() {
        return "Welcome to Docker ....";
    }
}
