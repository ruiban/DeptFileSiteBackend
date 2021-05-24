package com.dept.filesite.entity;

public class PlanningImageList {
    private int id;
    private int planning_id;
    private int imagelist_id;

    public int getId(){
        return id;
    }
    public void setId(int id){ this.id = id;}

    public int getPlanning_id() {
        return planning_id;
    }
    public void setPlanning_id(int id){ this.planning_id = id;}

    public int getImagelist_id(int id){ return  imagelist_id;}
    public void setImagelist_id(int id) {this.imagelist_id = id;}
}
