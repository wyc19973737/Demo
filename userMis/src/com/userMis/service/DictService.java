package com.userMis.service;

import java.util.List;

import com.userMis.dao.DictDao;
import com.userMis.model.DictModel;

public class DictService {

    private DictDao dictDao = new DictDao();
    
    public List<DictModel> getList(DictModel dm) {
        return dictDao.selectList(dm);
    }
    
    public int getCount(DictModel dm) {
        return dictDao.getCount(dm);
    }
    
    /**
     * @param code
     * @return null或DictModel
     */
    public DictModel getDict(String code) {
        DictModel dm = new DictModel();
        dm.setCode(code);
        List<DictModel> list = getList(dm);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }
    
    /**
     * @param dm
     * @return ""=成功 或 "添加失败"
     */
    public String insert(DictModel dm) {
        return dictDao.insert(dm) > 0 ? "" : "添加失败";
    }
    
    /**
     * @param dm
     * @return ""=成功 或 "修改失败"
     */
    public String update(DictModel dm) {
        return dictDao.updateByAccount(dm) > 0 ? "" : "修改失败";
    }
    
    /**
     * @param dm
     * @return ""=成功 或 "删除失败"
     */
    public String delete(DictModel dm) {
        if (isParent(dm)) {
            return "该分类下有子分类，不能删除";
        }
        return dictDao.deleteByAccount(dm) > 0 ? "" : "删除失败";
    }
    
    /**
     * @param list
     * @return ""=成功 或 "删除失败"
     */
    public String deleteAll(List<DictModel> list) {
        for (DictModel dm : list) {
            if (isParent(dm)) {
                return "选中分类中有父分类，不能删除";
            }
        }
        return dictDao.deleteAllByAccount(list) == list.size() ? "" : "删除失败";
    }
    
    /**
     * @param dm
     * @return true=是父分类 false=不是
     */
    public boolean isParent(DictModel dm) {
        return dictDao.getCount(new DictModel(null, null, dm.getCode())) > 0;
    }
}
