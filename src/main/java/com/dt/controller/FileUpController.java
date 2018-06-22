package com.dt.controller;

import com.dt.vo.FileVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("file")
@Controller
public class FileUpController {
    @RequestMapping("up")
    @ResponseBody
    public FileVo fileUp(MultipartFile file, HttpServletRequest request){
        FileVo fileVo = new FileVo();
        try{
            //使用原始文件名称
            String fileName = file.getOriginalFilename();
            //重新格式化文件名称
            //String fileName = getFileName(file.getOriginalFilename());
            String path = request.getSession().getServletContext().getRealPath("upload");
            String names = getFileName(fileName);
            File dir = new File(path,names);
            if(!dir.exists()){
                dir.mkdirs();
            }
            file.transferTo(dir);
            Map<String,String> data = new HashMap<String, String>();
            data.put("src","/upload/"+names);
            fileVo.setData(data);
            System.out.println("保存到数据库的图片地址:/upload/"+names);
            fileVo.setCode(0);
        }catch (Exception e){
            e.printStackTrace();
            fileVo.setCode(1);
        }
        fileVo.setMsg("上传成功!");
        return  fileVo;
    }
    @RequestMapping("upVedio")
    @ResponseBody
    public FileVo upVedio(MultipartFile file, HttpServletRequest request){
        FileVo fileVo = new FileVo();
        try{
            //使用原始文件名称
            String fileName = getFileName(file.getOriginalFilename());
            //重新格式化文件名称
            //String fileName = getFileName(file.getOriginalFilename());
            String path = request.getSession().getServletContext().getRealPath("upload/vedio/");
            File dir = new File(path,fileName);
            if(!dir.exists()){
                dir.mkdirs();
            }
            file.transferTo(dir);
            Map<String,String> data = new HashMap<String, String>();
            data.put("src","/upload/vedio/"+fileName);
            fileVo.setData(data);
            System.out.println("保存到数据库的图片地址:/upload/vedio/"+fileName);
            fileVo.setCode(0);
        }catch (Exception e){
            e.printStackTrace();
            fileVo.setCode(1);
        }
        fileVo.setMsg("上传成功!");
        return  fileVo;
    }
    @RequestMapping("upPage")
    public String upPage(){
        return "user/upVedio";
    }
    //重命名文件名称
    private synchronized String getFileName(String filename) {
        int position = filename.lastIndexOf(".");
        String ext = filename.substring(position);
        return System.nanoTime() + ext;
    }
}