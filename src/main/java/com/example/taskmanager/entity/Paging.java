package com.example.taskmanager.entity;

import java.util.List;

public class Paging {
    //페이지에 따라 서브리스트를 만들어내는 용

    private int pageNumber;
    private int pageSize = 5;
    private int offset;
    private int limit;
    private List<Task> pageTaskList;

    public Paging(int pageNumber, List<Task> taskList){
        this.pageNumber = pageNumber;
        this.pageTaskList = taskList;
    }

    public List<Task> slicePageData(){
        if(pageNumber < 1) {
            offset = 0;
            limit = pageSize;
        }
        else {
            offset = (pageNumber - 1) * pageSize;
            limit = pageNumber * pageSize;
        }

        if(offset >= pageTaskList.size())
            pageTaskList.clear();   // 범위 넘어서면 빈 배열 반환
        else{
            if(limit > pageTaskList.size()){
                limit = pageTaskList.size();
            }
            pageTaskList = pageTaskList.subList(offset, limit);
        }
        return pageTaskList;
    }
}
