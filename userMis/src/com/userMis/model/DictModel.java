package com.userMis.model;

public class DictModel extends PageModel {

    private Integer id;
    private String code;
    private String name;
    private String parentCode;

    public DictModel() {
        super();
    }

    public DictModel(String code, String name, String parentCode) {
        super();
        this.code = code;
        this.name = name;
        this.parentCode = parentCode;
    }

    @Override
    public String toString() {
        return "DictModel [id=" + id + ", code=" + code + ", name=" + name + ", parentCode=" + parentCode + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
