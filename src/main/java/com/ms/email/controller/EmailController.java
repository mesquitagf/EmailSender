package com.ms.email.controller;

import com.ms.email.dto.EmailDTO;
import com.ms.email.model.EmailModel;
import com.ms.email.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/emails")
    public ResponseEntity<EmailModel> sendEmail(@RequestBody @Valid EmailDTO emailDTO) {
        return new ResponseEntity<>(emailService.sendEmail(emailDTO.covertToEmailModel(emailDTO)), HttpStatus.CREATED);
    }

    @GetMapping("/emails")
    public ResponseEntity<Page<EmailModel>> getEmails(@PageableDefault(page = 0, size = 5, sort = "emailId",
            direction = Sort.Direction.DESC) Pageable pageable) {
        return new ResponseEntity<>(emailService.getEmails(pageable), HttpStatus.OK);
    }

    @GetMapping("/emails/{emailId}")
    public ResponseEntity<Object> getOneEmail(@PathVariable("emailId") UUID emailId) {
        Optional<EmailModel> emailModelOptional = emailService.findById(emailId);
        if (!emailModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(emailModelOptional.get());
        }
    }
}
