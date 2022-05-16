package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class VersionService {
    @Value(("${application-version}"))
    private String applicationVersion;

    @Value("${schema-version}")
    private String schemaVersion;

    public String getVersionInfo(){
        return String.format("Application version: %s; Database schema version: %s",
                applicationVersion, schemaVersion);
    }
}
