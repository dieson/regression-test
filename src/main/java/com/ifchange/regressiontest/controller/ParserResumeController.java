package com.ifchange.regressiontest.controller;

import com.ifchange.regressiontest.dto.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Objects;

/**
 * @ClassName: ParserResumeController
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 10:00 2019/7/12
 */
@RestController
public class ParserResumeController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/parser_resume", method = RequestMethod.POST)
    public ServerResponse parserResume(HttpServletRequest request) {
        logger.info("简历解析");
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            String uploadFilePath = Objects.requireNonNull(multipartRequest.getFile("resumeFile")).getOriginalFilename();
            String uploadFileName = uploadFilePath.substring(uploadFilePath.lastIndexOf('\\') + 1, uploadFilePath.indexOf('.'));
            String uploadFileSuffix = uploadFilePath.substring(uploadFilePath.indexOf('.') + 1);

            FileInputStream fis = (FileInputStream) Objects.requireNonNull(multipartRequest.getFile("resumeFile")).getInputStream();
            FileOutputStream fos = new FileOutputStream(new File(".//uploadFiles//" + uploadFileName + ".") + uploadFileSuffix);
            byte[] temp = new byte[1024];
            int i = fis.read(temp);
            while (i != -1) {
                fos.write(temp, 0, temp.length);
                fos.flush();
                i = fis.read(temp);
            }

            return ServerResponse.createByErrorMessage("简历解析失败");
        } catch (Exception e) {
            logger.error("简历解析失败： ", e);
            return ServerResponse.createByErrorMessage("简历解析失败");
        }

    }
}
