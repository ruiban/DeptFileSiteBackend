package com.dept.filesite.utils;

import com.dept.filesite.entity.APIException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * @className: FileUtil
 * @description: 文件工具类
 * @author: 201998
 * @create: 2019-12-27 14:05
 */

public class FileUtil {


    private static volatile FileUtil fileUtil;

    public static FileUtil getInstance(){
        if (fileUtil == null){
            synchronized (FileUtil.class){
                if (fileUtil == null){
                    fileUtil = new FileUtil();
                }
            }
        }

        return fileUtil;
    }

    public  void download(String filePath, HttpServletResponse response){
        // 获取文件
        File file = new File(filePath);
        String fileName = file.getName(); //下载的文件名


        // 如果文件存在，则进行下载
        if (file.exists()) {
            System.out.println(file);
            // 配置文件下载
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream");
            // 对文件名进行一次转码,下载文件能正常显示中文
            try {
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                throw new APIException("文件下载失败");
            }

            // 实现文件下载
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }

            }
            catch (Exception e) {
                throw new APIException("文件下载失败");
            }
            finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

    }

    /**
     * @author  201998
     * @Date 2019-12-27
     * @Description
     * @param file
     * @param path
     * @return
     */
    public  boolean saveFile(MultipartFile file, String path) {

        //存入服务器文件夹
        File toFile = new File(path);
        if(!toFile.getParentFile().exists()){
            toFile.getParentFile().mkdirs();
        }
        boolean result = false;
        //保存文件
        try {
            file.transferTo(toFile);
            result = true;
        }catch (IOException e){
            e.printStackTrace();
           result = false;
        }finally {
            return result;
        }

    }


    public void viewImage(String file, HttpServletResponse response) {

        File image = new File(file);
        String fileName = image.getName();

        if(image.exists()) {
            System.out.println(image);
        }

    }
    public  boolean copyFile(String oldPath, String newPath){


            File newFile = new File(newPath);
            if(!newFile.exists()){
                //创建目录
                newFile.getParentFile().mkdirs();
            }
            boolean flag = false;
            byte[] buffer = new byte[1024];
            FileInputStream oldFis = null;
            BufferedInputStream oldBis = null;
            FileOutputStream newFos = null;
            BufferedOutputStream newBos = null;

            int len = 0;
            try {
                oldFis = new FileInputStream(oldPath);
                oldBis = new BufferedInputStream(oldFis);
                newFos = new FileOutputStream(newPath);
                newBos = new BufferedOutputStream(newFos);

                while ((len = oldBis.read(buffer)) != -1) {
                    newBos.write(buffer, 0, len);
                }

                flag = true;

            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {

                if(oldBis != null){
                    try {
                        oldBis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if(newBos != null){
                    try {
                        newBos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return flag;
            }
    }

    public  boolean copyFolder(String oldPath, String newPath){
        try {
            System.out.println(newPath);
            File oldFolder = new File(oldPath);
            //新文件夹不存在，创建文件夹
            File newFolder = new File(newPath);
            if(!newFolder.exists()){
                newFolder.mkdirs();
            }
            String[] childFiles = oldFolder.list();

            File temp = null;
            for (int i = 0; i < childFiles.length; i++){
                temp = new File(new File(oldPath),childFiles[i]);
                boolean isCopy;
                //如果是文件夹
                if (temp.isDirectory()){
                    //递归调用，继续遍历
                    isCopy = copyFolder(oldPath + File.separator + childFiles[i], newPath + File.separator + childFiles[i]);
                    if(!isCopy){
                        return false;
                    }
                }

                //如果是文件
                if (temp.isFile()){
                    isCopy = copyFile(oldPath+File.separator+childFiles[i],newPath+File.separator+childFiles[i]);
                    if(!isCopy){
                        return false;
                    }
                }
            }


            return true;
        }catch (Exception e){
            System.out.println("复制整个文件夹失败!");
            return false;
        }
    }

    public  boolean deleteFile(File file){

        if (file.isDirectory()){
            String[] childFiles = file.list();

            for (int i = 0; i < childFiles.length; i++){
                //System.out.println(childFiles[i]);
                boolean isDelete = deleteFile(new File(file,childFiles[i]));
                if (!isDelete){
                    break;
                }
            }

        }
        return file.delete();
    }

    //移动文件或文件夹   file systems不一致也可以使用
    public  boolean moveFolder(String oldPath, String newPath){
        File old = new File(oldPath);
        boolean isCopy = false;
        if (old.isFile()){
            isCopy = copyFile(oldPath,newPath);
        }
        if (old.isDirectory()){
            isCopy = copyFolder(oldPath,newPath);
        }

        boolean isDelete = false;
        //保证复制成功后才可以删除源文件
        if (isCopy){
            isDelete = deleteFile(old);
        }
        return isCopy && isDelete;
    }

    public  List<String> ergodic(File file, List<String> fileNames){

        File[] files = file.listFiles();
        if (files == null){
            return fileNames;
        }
        for (File f : files){
            //判断是否是文件夹
            if (f.isDirectory()){
                fileNames.add(f.getPath());
                //继续遍历,查找子目录
                ergodic(f,fileNames);
            }else{
                fileNames.add(f.getPath());
            }
        }
        return fileNames;
    }

    /**
     * 重命名
     */
    public boolean renameFile(String oldPath,String newName){
        File oldFile = new File(oldPath);
        if(!oldFile.exists()){
            return false;
        }
        String newPath = oldFile.getParent() + File.separator + newName;
        int index = oldPath.lastIndexOf(".");
        if (index > -1){
            String suffix = oldPath.substring(index);
            newPath+=suffix;
        }
        File newFile = new File(newPath);
        return oldFile.renameTo(newFile);
    }

}
