package com.alexandru.amihalachioaei.logic;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class PathLogic {

    public File getFile(String filename) {

        File file = new File(filename);

        if(file.exists() && file.canRead()){
            return file;
        }

        return null;
    }
}
