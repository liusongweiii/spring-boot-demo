package com.util;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author Administrator
 */
public class UploadUtil {

    /**
     * @param request
     * @param savePath
     *            必须包含文件名
     * @throws IOException
     */
    public static String saveFileWithNameByUUID(HttpServletRequest request, String savePath) throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");
        // 获得文件：
        if (file == null || file.getSize() == 0 || "".equals(file.getSize()))
            return "";
        String oriName = file.getOriginalFilename();
        String type = oriName.substring(oriName.lastIndexOf("."), oriName.length());
        // 对扩展名进行小写转换
        type = type.toLowerCase();
        String fileName = UUIDUtil.getRandomByUUID() + type;
        File directory = new File(savePath);
        if (!directory.exists()){
            directory.mkdirs();
        }
        savePath = savePath + "/" + fileName;
        // 获得文件名：
        InputStream is = file.getInputStream();
        FileOutputStream os = new FileOutputStream(new File(savePath));

        try {
            int i = -1;
            byte[] b = new byte[1024];
            while ((i = is.read(b)) != -1) {
                os.write(b, 0, i);
            }
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.flush();
                os.close();
            }
        }

        return savePath;
    }

    /**
     * @param request
     * @param savePath
     *            必须包含文件名
     * @throws IOException
     */
    public static String saveFile(HttpServletRequest request, String savePath, String attrName, String fileName) throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 获得文件：
        MultipartFile file = multipartRequest.getFile(attrName);
        if (file.isEmpty()) {
            return "";
        }
        String oriName = file.getOriginalFilename();
        String type = oriName.substring(oriName.lastIndexOf("."), oriName.length());
        // 对扩展名进行小写转换
        type = type.toLowerCase();
        File directory = new File(savePath);
        if (!directory.exists()){
            directory.mkdirs();
        }
        savePath = savePath + "/" + fileName + type;
        // 获得文件名：
        InputStream is = file.getInputStream();
        FileOutputStream os = new FileOutputStream(new File(savePath));

        try {
            int i = -1;
            byte[] b = new byte[1024];
            while ((i = is.read(b)) != -1) {
                os.write(b, 0, i);
            }
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.flush();
                os.close();
            }
        }

        return savePath;
    }

    public static String saveFile(HttpServletRequest request, String savePath, String fileName) throws IOException {
        return saveFile(request,savePath,"file",fileName);
    }

    /**
     * @param request
     * @param savePath
     *            必须包含文件名
     * @throws IOException
     */
    public static String saveFile(HttpServletRequest request, String savePath) throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 获得文件：
        MultipartFile file = multipartRequest.getFile("file");
        String fileName = file.getOriginalFilename();
        File directory = new File(savePath);
        if (!directory.exists()){
            directory.mkdirs();
        }
        savePath = savePath + "/" + fileName;

        // 获得文件名：
        InputStream is = file.getInputStream();
        FileOutputStream os = new FileOutputStream(new File(savePath));

        try {
            int i = -1;
            byte[] b = new byte[1024];
            while ((i = is.read(b)) != -1) {
                os.write(b, 0, i);
            }
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.flush();
                os.close();
            }
        }

        return savePath;
    }

    public static String getUploadFileName(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 获得文件：
        MultipartFile file = multipartRequest.getFile("file");
        String fileName = file.getOriginalFilename();
        return fileName;
    }

    public static void downloadFile(HttpServletResponse response, String filePath) throws Exception {
        File file = new File(filePath);
        String fileName = URLEncoder.encode(file.getName(), "UTF-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        InputStream is = new FileInputStream(file);
        OutputStream os = response.getOutputStream();
        try {
            byte[] b = new byte[1024];
            int length;
            while ((length = is.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
        }
    }

    public static void downloadFileByBrowser(HttpServletResponse response, String filePath, boolean isfirefox) throws Exception {
        File file = new File(filePath);
        String fileName = URLEncoder.encode(file.getName(), "UTF-8");
        if (isfirefox) {
            fileName = file.getName();
            fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
        }
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        InputStream is = new FileInputStream(file);
        OutputStream os = response.getOutputStream();
        try {
            byte[] b = new byte[1024];
            int length;
            while ((length = is.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
        }
    }

    public static void downloadFileByBrowser(HttpServletResponse response, HttpServletRequest request, String filePath) throws Exception {
        File file = new File(filePath);
        String fileName = URLEncoder.encode(file.getName(), "UTF-8");
        // 如果是firefox
        if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
            fileName = file.getName();
            fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
        }
        // 如果是Safari
        if (request.getHeader("User-Agent").contains("Safari")) {
            fileName = file.getName();
            fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
        }
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        InputStream is = new FileInputStream(file);
        OutputStream os = response.getOutputStream();
        try {
            byte[] b = new byte[1024];
            int length;
            while ((length = is.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
        }
    }

    public static void downloadFile(HttpServletResponse response, HttpServletRequest request, String filePath) throws Exception {
        File file = new File(filePath);
        String name = file.getName();
        String fileName = URLEncoder.encode(file.getName(), "UTF-8");
        String fontName = URLEncoder.encode(file.getName(), "UTF-8");
        fileName = name;
        fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        String userAgent = request.getHeader("USER-AGENT");
        if (userAgent.indexOf("MSIE") != -1) {
            fileName = new String(fontName.getBytes("UTF-8"), "gb2312");
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        } else {
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        }
        InputStream is = new FileInputStream(file);
        OutputStream os = response.getOutputStream();
        try {
            byte[] b = new byte[1024];
            int length;
            while ((length = is.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.flush();
                os.close();
            }
        }
    }

}
