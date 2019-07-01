package com.finance.homework.controllers;

import com.finance.homework.controllers.documentation.ExtentionControllerDoc;
import com.finance.homework.converters.ExtentionConverter;
import com.finance.homework.domain.requests.ExtentionRequest;
import com.finance.homework.domain.responses.ExtentionResponse;
import com.finance.homework.services.ExtentionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/extention")
public class LoanExtentionController implements ExtentionControllerDoc {


    @Autowired
    ExtentionService extentionService;

    @GetMapping
    public ResponseEntity<?> getAllExtentions() {

        log.info("Retrieving extention list for all users.");

        return new ResponseEntity<List<ExtentionResponse>>(ExtentionConverter.toResponse(extentionService.findAllExtentions()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createExtention(@Valid @RequestBody ExtentionRequest extentionRequest) {

        log.info("Extending loan : {}", extentionRequest);

        return new ResponseEntity<ExtentionResponse>(ExtentionConverter.toResponse(extentionService.createExtention(extentionRequest)), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{extId}")
    public ResponseEntity<?> getExtention(@PathVariable String extId) {

        log.info("Retrieving extention by Id {}.", extId);

        return new ResponseEntity<ExtentionResponse>(ExtentionConverter.toResponse(extentionService.getExtentionById(extId)), HttpStatus.OK);
    }
}
