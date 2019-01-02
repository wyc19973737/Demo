package com.userMis.model;

public class UserModel extends PageModel {

    private Integer id;
    private String account;
    private String password;
    private String petName;
    private String sex;
    private String hobby;
    private String address;
    private String headImg;

    public UserModel() {
        super();
    }

    public UserModel(String account, String petName, String sex, String address) {
        super();
        this.account = account;
        this.petName = petName;
        this.sex = sex;
        this.address = address;
    }

    public UserModel(String account, String password, String petName, String sex, String hobby, String address) {
        super();
        this.account = account;
        this.password = password;
        this.petName = petName;
        this.sex = sex;
        this.hobby = hobby;
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserModel [id=" + id + ", account=" + account + ", password=" + password + ", petName=" + petName
                + ", sex=" + sex + ", hobby=" + hobby + ", address=" + address + ", headImg=" + headImg + "]";
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
}
