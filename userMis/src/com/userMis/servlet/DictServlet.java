package com.userMis.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.userMis.model.DictModel;
import com.userMis.service.DictService;

public class DictServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DictService dictService = new DictService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html; charset=UTF-8");
        
        String data = "好像出错了";
        switch (request.getParameter("action")) {
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
            case "delete":
                data = delete(request);
                break;
            case "add":
                data = addOrUpd(request, 1);
                break;
            case "upd":
                data = addOrUpd(request, 2);
                break;
    
            default:
                break;
        }
    
        response.getWriter().print(data);
	}

    /**
     * @param request
     * @return ""=删除成功 其他=失败信息
     */
    private String delete(HttpServletRequest request) {
        String[] codes = request.getParameterValues("code");
        if (codes.length == 1) {
            return dictService.delete(new DictModel(codes[0], null, null));
        } else {
            List<DictModel> list = new ArrayList<>();
            for (String code : codes) {
                list.add(new DictModel(code, null, null));
            }
            return dictService.deleteAll(list);
        }
    }

    /**
     * @param request
     * @param type
     *          1=添加 2=修改
     * @return 
     *          ""=成功 其他=失败信息
     */
    private String addOrUpd(HttpServletRequest request, int type) {
        String code = request.getParameter("code");
        if (type == 1 && dictService.getDict(code) != null) {
            return "编号已存在";
        }
        String name = request.getParameter("name");
        String parentCode = request.getParameter("parentCode");
        
        DictModel dm = new DictModel(code, name, parentCode);
        if (type == 1) {
            return dictService.insert(dm);
        } else if (type == 2) {
            return dictService.update(dm);
        }
        return "好像出错了";
    }

    private String getAllList(HttpServletRequest request) {
        DictModel dm = new DictModel();
        dm.setParentCode(request.getParameter("parentCode"));
        dm.setOrderBy("parent_code,code");
        return new JSONArray(dictService.getList(dm)).toString();
    }

    /**
     * @param request
     * @param response
     * @param type 1="添加" 2="修改"
     * @throws IOException 
     * @throws ServletException 
     */
    private void openDialog(HttpServletRequest request, HttpServletResponse response, int type)
            throws ServletException, IOException {
        switch (type) {
        case 2:
            request.setAttribute("dm", dictService.getDict(request.getParameter("code")));
        case 1:
            request.getRequestDispatcher("/web/page/basis/dictAddOrUpd.jsp").forward(request, response);
            break;

        default:
            break;
        }
    }

    /**
     * @param request
     * @return rowCount list
     */
    private String getList(HttpServletRequest request) {
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String parentCode = request.getParameter("parentCode");
        String pageIndex = request.getParameter("pageIndex");
        String pageLimit = request.getParameter("pageLimit");
        
        DictModel dm = new DictModel(code, "%" + name + "%", parentCode);
        dm.setOrderBy("parent_code,code");
        dm.setPageOn(true);
        dm.setPageIndex(Integer.parseInt(pageIndex));
        dm.setPageLimit(Integer.parseInt(pageLimit));
        JSONObject obj = new JSONObject();
        obj.put("rowCount", dictService.getCount(dm));
        obj.put("list", dictService.getList(dm));
        return obj.toString();
    }

}
