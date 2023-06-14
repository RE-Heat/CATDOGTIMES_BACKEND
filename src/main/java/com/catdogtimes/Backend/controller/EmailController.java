package com.catdogtimes.Backend.controller;

import com.catdogtimes.Backend.dto.EmailAuthRequestDTO;
import com.catdogtimes.Backend.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

//    @GetMapping("/times/member/save/mailConfirm")
//    public String mailForm(){
//        return "emailauth";
//    }


    @PostMapping("/times/member/save/mailConfirm")
    public String mailConfirm(@RequestBody EmailAuthRequestDTO emailDTO) throws MessagingException, UnsupportedEncodingException {
        String authCode = emailService.sendEmail(emailDTO.getEmail());
        return authCode;
    }
}
