package com.example.bai2.demo8;

import java.util.HashMap;

public class ToDo {
    private String id,tiltle,content;
    //phương thức xử lý dữ liệu thao tác với fireBase
    public HashMap<String,Object> convertHashMap(){
        HashMap<String,Object> work = new HashMap<>();
        work.put("id",id);
        work.put("tiltle",tiltle);
        work.put("content",content);
        return work;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTiltle() {
        return tiltle;
    }

    public void setTiltle(String tiltle) {
        this.tiltle = tiltle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ToDo() {
    }

    public ToDo(String id, String tiltle, String content) {
        this.id = id;
        this.tiltle = tiltle;
        this.content = content;
    }
}
