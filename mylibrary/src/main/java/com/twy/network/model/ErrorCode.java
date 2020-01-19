package com.twy.network.model;

/**
 * Author by twy, Email 499216359@qq.com, Date on 2019/1/11.
 * PS: Not easy to write code, please indicate.
 */
public enum ErrorCode {
    GetOrPostRequired("必须选择一种请求方式",500),
    ConfigMsgRequired("Net对象未创建",500),
    GetPostOne("请求方式只能选择一种",500),
    UploadFileRequiredPostRequest("上传文件必须是POST请求",500),
    UploadFileTypeRequired("上传文件参数类型必须是java.io.File类型",500),
    BodyInPostRequest("Body注解只能用于POST或PUT请求",500);

    ErrorCode(String name,int code){
        this.name = name;
        this.code = code;
    }
    private String name;
    private int code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
