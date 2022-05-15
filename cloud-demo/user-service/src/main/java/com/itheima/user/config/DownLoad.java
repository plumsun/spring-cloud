package com.itheima.user.config;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownLoad {
    // 声明下载路径“红色警戒2”
    public static final String PATH = "http://down.sandai.net/thunder9/Thunder9.1.40.898.exe";
    public static int threadCount = 0;// 声明线程数量
    public static void main(String[] args) {
        try {
            URL url = new URL(PATH);
            // 获取连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 通过获取连接定义文件名
            String[] str = PATH.split("/");
            String fileName = str[str.length-1];
            // 获取下载文件大小
            int fileLength = conn.getContentLength();
            System.out.println(fileName);
            // 在本地创建一个与服务器大小一致的可随机写入文件
            RandomAccessFile raf = new RandomAccessFile(fileName, "rwd");
            System.out.println(fileLength);// 测试用
            raf.setLength(fileLength);
            // 自定义线程数量
            threadCount = 3;
            // 计算每条线程下载数据的大小
            int blockSize = fileLength / threadCount;
            // 启动线程下载
            for (int threadId = 1; threadId <= threadCount; threadId++) {
                // 核心代码，定义每个线程开始以及结束的下载位置
                int startPos = (threadId - 1) * blockSize;// 开始下载的位置
                int endPos = (threadId * blockSize) - 1;// 结束下载的位置（不包含最后一块）
                if (threadCount == threadId) {
                    endPos = fileLength;
                }
                new Thread(new DownLoadThread(threadId, startPos, endPos, PATH))
                        .start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 实现下载线程
    static class DownLoadThread implements Runnable {
        private int threadId;
        private int startPos;
        private int endPos;
        private String path;
        public DownLoadThread(int threadId, int startPos, int endPos,
                              String path) {
            super();
            this.threadId = threadId;
            this.startPos = startPos;
            this.endPos = endPos;
            this.path = path;
        }
        public void run() {
            try {
                URL url = new URL(path);
                String[] str = PATH.split("/");
                String fileName = str[str.length-1];
                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                // 设置URL请求的方法（具体参考API）
                conn.setRequestMethod("GET");
                // 设置500毫秒为超时值
                conn.setReadTimeout(5000);
                File file = new File(threadId + ".txt");
                if (file.exists() && file.length() > 0) {
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(new FileInputStream(file)));
                    String saveStartPos = br.readLine();
                    if (saveStartPos != null && saveStartPos.length() > 0) {
                        startPos = Integer.parseInt(saveStartPos);
                    }
                }
                // 注意双引号内的格式，不能包含空格（等其他字符），否则报416
                conn.setRequestProperty("Range", "bytes=" + startPos + "-"
                        + endPos);
                RandomAccessFile raf = new RandomAccessFile(fileName, "rwd");// 存储下载文件的随机写入文件
                raf.seek(startPos);// 设置开始下载的位置
                System.out.println("线程" + threadId + ":" + startPos + "~~"
                        + endPos);
                InputStream is = conn.getInputStream();
                byte[] b = new byte[1024 * 1024 * 10];
                int len = -1;
                int newPos = startPos;
                System.out.println("newPos = " + newPos);
                while ((len = is.read(b)) != -1) {
                    raf.write(b, 0, len);
                    // 将下载标记存入指定文档
                    System.out.println("len = " + len);
                }
                is.close();
                raf.close();
                System.out.println("下载完成");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
