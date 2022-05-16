package com.example.demo.controller;

import com.example.demo.service.VersionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "version")
public class VersionInfoController {
    private final VersionService versionService;

    public VersionInfoController(VersionService versionService) {
        this.versionService = versionService;
    }

    @GetMapping
    public String getVersionInfo(){
        return versionService.getVersionInfo();
    }
}
