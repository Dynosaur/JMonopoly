package com.github.dynosaur.io;

import java.io.File;
import java.io.IOException;

public class IOHandler {
    private String[] requiredFileNames;

    public IOHandler(String[] requiredDirNames) {
        this.requiredFileNames = requiredDirNames;
    }

    public void createMissingFiles() throws IOException {
        for (String fileName : requiredFileNames) {
            File file = new File(fileName);
            if (!file.exists()) file.mkdir();
        }
    }
}
