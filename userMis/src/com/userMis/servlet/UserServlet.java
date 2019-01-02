package com.userMis.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONObject;

import com.userMis.model.UserModel;
import com.userMis.service.UserService;
import com.userMis.util.FormatEmpty;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html; charset=UTF-8");
	    
	    String data = "好像出错了";
	    switch (request.getParameter("action")) {
            case "login":
                data = login(request);
                break;
            case "register":
                data = addOrUpd(request, 1);
                break;
            case "getList":
                data = getList(request);
                break;
            case "getAllList":
                data = getAllList(request);
                break;
            case "addDialog":
                openDialog(request, response, 1);
                return;
            case "updDialog":
                openDialog(request, response, 2);
                return;
            case "imgDialog":
                openDialog(request, response, 3);
                return;
            case "delete":
                data = delete(request);
                break;
            case "add":
                data = addOrUpd(request, 1);
                break;
            case "upd":
                data = addOrUpd(request, 2);
                break;
            case "headImgUpd":
                data = headImgUpd(request);
                break;
    
            default:
                break;
        }
	    
	    response.getWriter().print(data);
	}

    private String getAllList(HttpServletRequest request) {
        UserModel um = new UserModel();
        um.setOrderBy("account");
        return new JSONArray(userService.getUserList(um)).toString();
    }

    /**
     * @param request
     * @return key: message   value: ""=上传成功
     * @throws IOException
     */
    private String headImgUpd(HttpServletRequest request) throws IOException {
        String message = "好像出错了";
        File file = new File("H:/image/headImg");
        if (!file.exists()) {
            file.mkdir();
        }
        List<FileItem> items = null;
        try {
            items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        
        UserModel user = new UserModel();
        for (FileItem item : items) {
            if (item.isFormField()) {
                UserModel um = userService.getUserByAccount(new String(item.get()));
                String headImg = um.getHeadImg();
                if (!FormatEmpty.isEmpty(headImg)) {
                    File temp = new File(file + "/" + headImg);
                    temp.delete();
                }
                um.setHeadImg(user.getHeadImg());
                message = userService.update(um);
            } else {
                String name = item.getName();
                String suffixName = name.substring(name.lastIndexOf("."));// 获取后缀名
                String imgName = UUID.randomUUID().toString() + suffixName;
                String imgSrc = file + "/" + imgName;
                
                byte[] data = item.get();
                FileOutputStream fos = new FileOutputStream(imgSrc);
                fos.write(data, 0, data.length);
                fos.flush();
                fos.close();
                user.setHeadImg(imgName);
            }
        }
        JSONObject obj = new JSONObject();
        obj.put("message", message);
        return obj.toString();
    }

    /**
     * @param request
     * @param response
     * @param type 
     *          1=添加 2=修改 3=上传头像
     */
    private void openDialog(HttpServletRequest request, HttpServletResponse response, int type)
            throws ServletException, IOException {
        switch (type) {
        case 2:
            UserModel um = userService.getUserByAccount(request.getParameter("account"));
            request.setAttribute("um", um);
        case 1:
            request.getRequestDispatcher("/web/page/user/userAddOrUpd.jsp").forward(request, response);
            break;
        case 3:
            request.setAttribute("account", request.getParameter("account"));
            request.getRequestDispatcher("/web/page/user/headImgUpd.jsp").forward(request, response);
            break;

        default:
            break;
        }
    }

    /**
     * @param request
     * @return ""=删除成功
     */
    private String delete(HttpServletRequest request) {
        String[] account = request.getParameterValues("account");
        if (account.length == 1) {
            return userService.delete(new UserModel(account[0], null, null, null));
        } else {
            List<UserModel> list = new ArrayList<>();
            for (String str : account) {
                list.add(new UserModel(str, null, null, null));
            }
            return userService.deleteAll(list);
        }
    }

    /**
     * @param request
     * @return UserList rowCount
     */
    private String getList(HttpServletRequest request) {
        String account = request.getParameter("account");
        String petName = request.getParameter("petName");
        String sex = request.getParameter("sex");
        String address = request.getParameter("address");
        String pageIndex = request.getParameter("pageIndex");
        String pageLimit = request.getParameter("pageLimit");
        
        UserModel um = new UserModel(account, petName, sex, address);
        um.setPageOn(true);
        um.setPageIndex(Integer.parseInt(pageIndex));
        um.setPageLimit(Integer.parseInt(pageLimit));
        JSONObject obj = new JSONObject();
        obj.put("rowCount", userService.getCount(um));
        obj.put("list", userService.getUserList(um));
        return obj.toString();
    }

    /**
     * @param request
     * @param type
     *            1=添加 2=修改
     * @return ""=成功 其它=失败信息
     */
    private String addOrUpd(HttpServletRequest request, int type) {
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        if (!password.equals(confirmPassword)) {
            return "两次密码不一致";
        }
        
        String account = request.getParameter("account");
        if (type == 1 && userService.getUserByAccount(account) != null) {
            return "账号已存在";
        }
        
        String petName = request.getParameter("petName");
        String sex = request.getParameter("sex");
        String hobby = getHobby(request);
        String address = request.getParameter("address");
        UserModel um = new UserModel(account, password, petName, sex, hobby, address);
        if (type == 1) {
            return userService.add(um);
        } else if (type == 2) {
            return userService.update(um);
        }
        return "好像出错了";
    }

    private String getHobby(HttpServletRequest request) {
        String[] hobbyNames = {"hobby[read]", "hobby[sing]", "hobby[dai]"};
        List<String> hobbies = new ArrayList<>();
        for (int i = 0; i < hobbyNames.length; i++) {
            String temp = request.getParameter(hobbyNames[i]);
            if (temp != null) {
                hobbies.add(temp);
            }
        }
        StringBuffer hobby = new StringBuffer("");
        if (!hobbies.isEmpty()) {
            hobby.append(hobbies.get(0));
            for (int i = 1; i < hobbies.size(); i++) {
                hobby.append("," + hobbies.get(i));
            }
        }
        return hobby.toString();
    }

    /**
     * @param request
     * @return ""=登录成功，其它=登录失败信息
     */
    private String login(HttpServletRequest request) {
        String inputCode = request.getParameter("authCode");
        if (!isAuthCode(request, inputCode)) {
            return "验证码不正确";
        }
        String account = request.getParameter("account");
        UserModel um = userService.getUserByAccount(account);
        if (um == null) {
            return "账号不存在";
        }
        String password = request.getParameter("password");
        if (!um.getPassword().equals(password)) {
            return "密码不正确";
        }
        request.getSession().setAttribute("user", um);
        return "";
    }

    private boolean isAuthCode(HttpServletRequest request, String inputCode) {
        return request.getSession().getAttribute("authCode").toString().equalsIgnoreCase(inputCode);
    }
}
