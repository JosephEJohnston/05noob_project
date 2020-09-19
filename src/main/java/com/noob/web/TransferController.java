package com.noob.web;

import com.noob.annotation.AuthToken;
import com.sun.net.httpserver.HttpServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Api("TransferController")
@Controller
public class TransferController {

    @ApiOperation("跳转到 index 页面")
    @GetMapping("/")
    public String IndexPage() { return "index"; }

    @ApiOperation("跳转到 passport 页面")
    @GetMapping("/passport")
    public String passportPage() {
        return "passport";
    }

    @ApiOperation("跳转到 people 页面")
    @AuthToken
    @GetMapping("/people")
    public String peoplePage() {

        return "people";
    }
}
