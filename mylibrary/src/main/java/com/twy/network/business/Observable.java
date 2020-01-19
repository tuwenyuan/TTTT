package com.twy.network.business;

import com.twy.network.interfaces.DELETE;
import com.twy.network.interfaces.GET;
import com.twy.network.interfaces.POST;
import com.twy.network.interfaces.PUT;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Author by twy, Email 499216359@qq.com, Date on 2019/1/11.
 * PS: Not easy to write code, please indicate.
 */
public class Observable<T> {
    public GET get;
    public POST post;
    public PUT put;
    public DELETE delete;
    public List<Object> paramNames = new ArrayList<>();
    public Object[] paramValues;
    public String[] headers;//[ache-Control: max-age=640000,ache-Control: max-age=640000]
    public Type type;
    public boolean isMultipart;
}
