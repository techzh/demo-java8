package org.my.demo.java8;

import java.util.List;

public class Depart {
    private Integer id;
    private String name;
    private Integer level;
    private Integer parentId;
    private List<Depart> subDepart;

    public Depart() {
    }

    public Depart(Integer id, String name, Integer level, Integer parentId) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Depart{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", parentId=" + parentId +
                ", subDepart=" + subDepart +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<Depart> getSubDepart() {
        return subDepart;
    }

    public void setSubDepart(List<Depart> subDepart) {
        this.subDepart = subDepart;
    }
}
