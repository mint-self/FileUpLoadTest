package com.xm.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author mintFM
 * @create 2021-09-18 8:37
 */
public class uploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置解决中文乱码问题
        req.setCharacterEncoding("utf-8");
        //先判断文件是否为多段数据，只有是多段数据，才是文件上传
        if (ServletFileUpload.isMultipartContent(req)) {
            //结果为true表示是多段数据，为文件上传
            //先创建一个工厂实现类，来辅助文件的上传
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            //创建用于解析上传数据的工具类ServletFileload类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);

            try {
                //解析上传的数据，得到每一个表单项fileItem
                List<FileItem> list = servletFileUpload.parseRequest(req);

                //循环遍历，判断每一个表单项是普通项还是文件上传项，对不同的类型进行不同的操作
                for (FileItem fileItem : list) {
                    if (fileItem.isFormField()) {
                        //结果为true表示是普通项，则可以获取表单项中的name属性，value属性等
                        System.out.println("表单项的name属性是：" + fileItem.getFieldName());
                        //获取表单项的value值，同时传入参数UTF-8来解决中文乱码问题
                        System.out.println("表单项的value属性是：" + fileItem.getString("UTF-8"));
                    } else {
                        //否则如果结果false，为文件上传类型
                        System.out.println("表单项的name属性：" + fileItem.getFieldName());
                        servletFileUpload.setHeaderEncoding("UTF-8");
                        System.out.println("上传的文件名：" + fileItem.getName());
                        //将上传的文件写到指定的硬盘位置
                        fileItem.write(new File("E:\\Java" + fileItem.getName()));
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
