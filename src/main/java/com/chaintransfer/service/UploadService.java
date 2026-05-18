package com.chaintransfer.service;

import com.chaintransfer.native_.ChaintransferClient;
import jakarta.annotation.Nonnull;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.resteasy.reactive.multipart.FileUpload;

@ApplicationScoped
public class UploadService {
    private final ChaintransferClient
            chaintransferClient = new ChaintransferClient();

    public boolean send(@Nonnull FileUpload file) {
        chaintransferClient.chaintransfer(file.filePath().toString());
        return true;
    }
}
