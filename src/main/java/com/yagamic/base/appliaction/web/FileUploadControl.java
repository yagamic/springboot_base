package com.yagamic.base.appliaction.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



public class FileUploadControl {

    //@Value("${consul:file.temp_location}")
    String TEMP_LOCATION = "/opt/web/temp";

    //@Value("${consul:file.location}")
    String LOCATION = "/opt/web/file";


    public boolean submitFile(String filename) {
        if (filename == null) return true;

        try {
            Files.move(Paths.get("/opt/web/temp", filename), Paths.get("/opt/web/file", filename));
            return true;
        } catch (IOException e) {
            return Files.exists(Paths.get("/opt/web/file", filename));
        }
    }
}
