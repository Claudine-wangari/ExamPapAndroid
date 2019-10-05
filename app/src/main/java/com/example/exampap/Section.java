package com.example.exampap;

class Section {
    private String name;
    private int thumbNail;

    public Section(String name, int thumbNail) {
        this.name = name;
        this.thumbNail = thumbNail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getThumbNail() {
        return thumbNail;
    }

    public void setThumbNail(int thumbNail) {
        this.thumbNail = thumbNail;
    }
}
