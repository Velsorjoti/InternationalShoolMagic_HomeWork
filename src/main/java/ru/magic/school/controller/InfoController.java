package ru.magic.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.magic.school.model.AppInfo;

@RequestMapping
@RestController
public class InfoController {

    @Value("${app.env}")
    private String appEnv;

    @GetMapping("/appInfo")
    public ResponseEntity<AppInfo> getThisInfo() {
        AppInfo appInfoThisInfo = new AppInfo("hogwarts-school", "0.0.1", appEnv);
        return ResponseEntity.ok(appInfoThisInfo);
    }
}
