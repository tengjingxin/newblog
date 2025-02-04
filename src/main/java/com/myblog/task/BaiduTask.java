package com.myblog.task;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author zepherywen
 * @since 2020/12/19
 */
@Slf4j
@Component
public class BaiduTask {

    @Scheduled(initialDelay = 20 * 1000, fixedDelay = 24 * 3600 * 1000)
    private void reaew() throws IOException {
        log.info("aaa");
        aaaa();

    }

    private void aaaa() throws IOException {

        String path = "./getbaidu.py";

        InputStream stream = getClass().getClassLoader().getResourceAsStream("pythonfiles/getbaidu.py");
        File targetFile = new File(path);
        FileUtils.copyInputStreamToFile(stream, targetFile);


        Process proc;
        try {
            proc = Runtime.getRuntime().exec("python3 " + path);// 执行py文件
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
