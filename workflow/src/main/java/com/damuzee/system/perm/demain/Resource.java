package com.damuzee.system.perm.demain;

import java.util.List;

/**
 * Created by karkaw on 2014/8/4.
 */
public class Resource {
    private String resName;
    private String description;
    private String parentId;
    private String type;
    private String code;
    private String url;
    private Object[] func;

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getFunc() {
        return func;
    }

    public void setFunc(Object[] func) {
        this.func = func;
    }
}
