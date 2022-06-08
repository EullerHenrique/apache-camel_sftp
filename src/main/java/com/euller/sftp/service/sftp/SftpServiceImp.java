package com.euller.sftp.service.sftp;

import com.euller.sftp.service.sftp.thread.ThreadService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutionException;


@Service
@AllArgsConstructor
public class SftpServiceImp implements SftpService{
    private final ThreadService t;

    public Boolean download(String file)  {
        try {
            return (Boolean) t.create(file).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }


}
