package com.yagamic.base.appliaction.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;



@Api("文件上传")
@RestController
public class FileController {

    //@Value("${consul:file.temp_location}")
    String TEMP_LOCATION = "/opt/web/temp";

    //@Value("${consul:file.location}")
    String LOCATION = "/opt/web/file";


    @ApiOperation(value = "上传文件")
    @RequestMapping(value = "/api/file", method = RequestMethod.POST)
    ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        //System.out.println("------TEMP_LOCATION:" + TEMP_LOCATION);
        file.getName();
        String type = file.getOriginalFilename().split("\\.(?=[^\\.]+$)")[1];
        String fileName = String.valueOf(UUID.randomUUID()) + "." + type;
       // Files.copy(file.getInputStream(), Paths.get("/opt/web/temp", fileName));
        Files.copy(file.getInputStream(), Paths.get("/opt/web/file", fileName));
        return new ResponseEntity(fileName, HttpStatus.OK);
    }

}
