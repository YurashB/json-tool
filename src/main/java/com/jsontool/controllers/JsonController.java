package com.jsontool.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsontool.services.JsonService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/jsontool/jsons")
@AllArgsConstructor
public class JsonController {

    private final JsonService service;

    record JsonValidationResponse(String message, Boolean isValid){}
    @GetMapping("/validation")
    public JsonValidationResponse validate(@RequestParam String json) {
        String validationMessage = service.validate(json);

        return new JsonValidationResponse(validationMessage,
                validationMessage.equals(JsonService.VALIDATION_SUCCESS_MESSAGE));
    }
}