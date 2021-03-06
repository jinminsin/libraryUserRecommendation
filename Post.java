package com.slave_mk14.libraryuserrecommendation;

class Post {
    private int seedid;
    private int cid;
    private int id;
    private String title;
    private String subtitle;
    private String owner;
    private String createDate;

    public Post(int seedid, int cid, String title, String subtitle, String owner, String createDate){
        this.seedid = seedid;
        this.cid = cid;
        this.title = title;
        this.subtitle = subtitle;
        this.owner = owner;
        this.createDate = createDate;
    }

    public Post(int seedid, int cid, int id, String title, String subtitle, String owner, String createDate){
        this.seedid= seedid;
        this.cid = cid;
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.owner = owner;
        this.createDate = createDate;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeedid() {
        return seedid;
    }

    public void setSeedid(int seedid) {
        this.seedid = seedid;
    }
}
