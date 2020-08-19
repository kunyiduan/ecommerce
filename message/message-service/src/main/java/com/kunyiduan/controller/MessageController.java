package com.kunyiduan.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
@Api(description = "短信",tags = {"message"})
public class MessageController {

}
