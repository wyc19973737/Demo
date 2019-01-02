package com.userMis.service;

import java.util.List;

import com.userMis.dao.AddrDao;
import com.userMis.model.AddrModel;

public class AddrService {

    private AddrDao addrDao = new AddrDao();
    
    public List<AddrModel> getList(AddrModel am) {
        return addrDao.selectList(am);
    }
    
    public int getCount(AddrModel am) {
        return addrDao.getCount(am);
    }

    public String delete(AddrModel am) {
        return addrDao.deleteById(am) > 0 ? "" : "删除失败";
    }

    public String deleteAll(List<AddrModel> list) {
        return addrDao.deleteAllById(list) == list.size() ? "" : "删除失败";
    }
    
    public AddrModel getAddrById(Integer id) {
        AddrModel am = new AddrModel();
        am.setId(id);
        List<AddrModel> list = getList(am);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }
    
    public String add(AddrModel am) {
        return addrDao.insert(am) > 0 ? "" : "添加失败";
    }
    
    public String update(AddrModel am) {
        return addrDao.updateById(am) > 0 ? "" : "修改失败";
    }
}
