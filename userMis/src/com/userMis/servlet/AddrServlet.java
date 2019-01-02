package com.userMis.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.userMis.model.AddrModel;
import com.userMis.service.AddrService;
public class AddrServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AddrService addrService = new AddrService();
	
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

    private String addOrUpd(HttpServletRequest request, int type) {
        String account = request.getParameter("account");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String addr1Code = request.getParameter("addr1Code");
        String addr2Code = request.getParameter("addr2Code");
        String addr3Code = request.getParameter("addr3Code");
        String addr4 = request.getParameter("addr4");
        AddrModel am = new AddrModel(account, name, phone, addr1Code, addr2Code, addr3Code, addr4);
        if (type == 1) {
            return addrService.add(am);
        } else if (type == 2) {
            am.setId(Integer.valueOf(request.getParameter("id")));
            return addrService.update(am);
        }
        return "好像出错了";
    }

    private void openDialog(HttpServletRequest request, HttpServletResponse response, int type) 
            throws ServletException, IOException {
        switch (type) {
        case 2:
            AddrModel am = addrService.getAddrById(Integer.valueOf(request.getParameter("id")));
            request.setAttribute("am", am);
        case 1:
            request.getRequestDispatcher("/web/page/addr/addrAddOrUpd.jsp").forward(request, response);
            break;

        default:
            break;
        }
    }

    private String delete(HttpServletRequest request) {
        String[] ids = request.getParameterValues("id");
        if (ids.length == 1) {
            AddrModel am = new AddrModel();
            am.setId(Integer.valueOf(ids[0]));
            return addrService.delete(am);
        } else {
            List<AddrModel> list = new ArrayList<>();
            for (String id : ids) {
                AddrModel am = new AddrModel();
                am.setId(Integer.valueOf(id));
                list.add(am);
            }
            return addrService.deleteAll(list);
        }
    }

    /**
     * @param request
     * @return rowCount list
     */
    private String getList(HttpServletRequest request) {
        String account = request.getParameter("account");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String addr1Code = request.getParameter("addr1Code");
        String addr2Code = request.getParameter("addr2Code");
        String addr3Code = request.getParameter("addr3Code");
        String addr4 = request.getParameter("addr4");
        String pageIndex = request.getParameter("pageIndex");
        String pageLimit = request.getParameter("pageLimit");
        
        AddrModel am = new AddrModel(account, "%" + name + "%", "%" + phone + "%", addr1Code, addr2Code,
                addr3Code, "%" + addr4 + "%");
        am.setOrderBy("account");
        am.setPageOn(true);
        am.setPageIndex(Integer.valueOf(pageIndex));
        am.setPageLimit(Integer.valueOf(pageLimit));
        JSONObject obj = new JSONObject();
        obj.put("rowCount", addrService.getCount(am));
        obj.put("list", addrService.getList(am));
        return obj.toString();
    }

}
