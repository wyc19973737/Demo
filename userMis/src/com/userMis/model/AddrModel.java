package com.userMis.model;

public class AddrModel extends PageModel {

    private Integer id;
    private String account;// 账号
    private String name;// 收货人
    private String phone;// 电话
    private String addr1Code;// 省
    private String addr2Code;// 市
    private String addr3Code;// 区
    private String addr4;// 详细地址
    private String petName;// 账号对应昵称
    private String addr1;
    private String addr2;
    private String addr3;

    public AddrModel() {
        super();
    }

    public AddrModel(String account, String name, String phone, String addr1Code, String addr2Code, String addr3Code,
            String addr4) {
        super();
        this.account = account;
        this.name = name;
        this.phone = phone;
        this.addr1Code = addr1Code;
        this.addr2Code = addr2Code;
        this.addr3Code = addr3Code;
        this.addr4 = addr4;
    }

    @Override
    public String toString() {
        return "AddrModel [id=" + id + ", account=" + account + ", name=" + name + ", phone=" + phone + ", addr1Code="
                + addr1Code + ", addr2Code=" + addr2Code + ", addr3Code=" + addr3Code + ", addr4=" + addr4
                + ", petName=" + petName + ", addr1=" + addr1 + ", addr2=" + addr2 + ", addr3=" + addr3 + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr1Code() {
        return addr1Code;
    }

    public void setAddr1Code(String addr1Code) {
        this.addr1Code = addr1Code;
    }

    public String getAddr2Code() {
        return addr2Code;
    }

    public void setAddr2Code(String addr2Code) {
        this.addr2Code = addr2Code;
    }

    public String getAddr3Code() {
        return addr3Code;
    }

    public void setAddr3Code(String addr3Code) {
        this.addr3Code = addr3Code;
    }

    public String getAddr4() {
        return addr4;
    }

    public void setAddr4(String addr4) {
        this.addr4 = addr4;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public String getAddr3() {
        return addr3;
    }

    public void setAddr3(String addr3) {
        this.addr3 = addr3;
    }

}
