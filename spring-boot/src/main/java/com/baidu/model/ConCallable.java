package com.baidu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Mypc on 2018/3/21 0021.
 */
public class ConCallable implements Callable{

    private List<String> list;

    @Override
    public Object call() throws Exception {
        List listRe = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            //含有‘4599’的字符串都返回
            if(list.get(i).contains("4599")){
                listRe.add(list.get(i));
            }
        }
        return listRe;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
