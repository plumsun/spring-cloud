package com.itheima.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @date: 2022/4/18 15:11
 * @author: LiHaoHan
 * @program: com.itheima.user.config
 */
public class tes {

    @Value("${shim.video.uploadPath}")
    private String shimUploadPath;
    public static void main(String[] args) throws Exception {
       Long[] startPos= new Long[10];
        Long[] endPos= new Long[10];
        RandomAccessFile rantmpfile = new RandomAccessFile("D:/wook.rb", "rw");
        for (int i = 0; i < 10; i++) {
            rantmpfile.seek(8 * i + 8); // 8 16
            startPos[i] = rantmpfile.readLong();

            rantmpfile.seek(8 * (i + 1000) + 16); // 8016 8024
            endPos[i] = rantmpfile.readLong();

            System.out.println("the Array content in the exit file: ");
            System.out.println("thre thread" + (i + 1) + " startPos:"
                    + startPos[i] + ", endPos: " + endPos[i]);
        }
    }




    public void fileOutputStream(HttpServletRequest request, HttpServletResponse response, MultipartFile multipartFile) throws ServletException, IOException {
        //获取请求头信息
        String rangeStr = request.getHeader("Range");

        //读取文件
        File file = new File(shimUploadPath+multipartFile.getOriginalFilename());
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        Long fileSize = file.length();

        //解析Range
        Map<String, Integer> range = this.analyzeRange(request.getHeader("Range"), fileSize.intValue());

        //检查是否是Range请求
        if (!StringUtils.isEmpty(rangeStr)) {//播放
            //设置响应头
            response.setContentType("video/mp4");
            response.setHeader("Content-Length", String.valueOf(fileSize.intValue()));
            response.setHeader("Content-Range", "bytes " + range.get("startByte") + "-" + range.get("endByte") + "/" + fileSize.intValue());
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Accept-Ranges", "bytes");
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
        } else {//下载
            //设置响应头，把文件名字设置
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
            //设置文件长度
            response.setHeader("Content-Length", String.valueOf(fileSize));
            //解决编码问题
            response.setHeader("Content-Type", "application/octet-stream");
        }
        //开始输出
        OutputStream os = response.getOutputStream();

        //传输字节长度
        int length = range.get("endByte") - range.get("startByte");
        System.out.println("传输字节长度length：" + length);

        byte[] buffer = new byte[length < 1024 ? length : 1024];
        in.skip(range.get("startByte"));
        int i = in.read(buffer);

        //避免多发生一次io，计算需要读取的长度
        length = length - buffer.length;
        while (i != -1) {
            os.write(buffer, 0, i);
            if (length <= 0) {
                break;
            }
            i = in.read(buffer);
            length = length - buffer.length;
        }
        os.flush();
        //关闭
        os.close();
        in.close();
    }

    /**
     * 解析range，解析出起始byte（startByte）和结束byte（endBytes）
     *
     * @param range    请求发来的range
     * @param fileSize 目标文件的大小
     * @return #意味读取从0到文件尾的范围
     */
    private Map<String, Integer> analyzeRange(String range, Integer fileSize) {
        // range: bytes=0-
        String[] split = range.replace("bytes=", "").split("-");//split:[0,?]
        Map<String, Integer> result = new HashMap<>();
        if (split.length == 1) {
            //从xxx长度读取到结尾
            Integer startBytes = new Integer(split[0]);
            result.put("startByte", startBytes);
            result.put("endByte", fileSize - 1);
        } else if (split.length == 2) {
            //从xxx长度读取到yyy长度
            Integer startBytes = new Integer(split[0].replaceAll("bytes=", "").replaceAll("-", ""));
            Integer endBytes = new Integer(split[1].replaceAll("bytes=", "").replaceAll("-", ""));
            result.put("startByte", startBytes);
            result.put("endByte", endBytes > fileSize ? fileSize : endBytes);
        } else {
            System.out.println("未识别的range：" + range);
        }
        return result;
    }
}
