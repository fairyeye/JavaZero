package com.zero.spring.guides.consumingrest.entity;

public class Hitokoto {
    private int id;
    private String uuid;
    private String hitokoto;
    private String type;
    private String from;
    private String from_who;
    private String creator;
    private int creator_uid;
    private int reviewer;
    private String commit_from;
    private String created_at;
    private int length;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setHitokoto(String hitokoto) {
        this.hitokoto = hitokoto;
    }

    public String getHitokoto() {
        return this.hitokoto;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom_who(String from_who) {
        this.from_who = from_who;
    }

    public String getFrom_who() {
        return this.from_who;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreator_uid(int creator_uid) {
        this.creator_uid = creator_uid;
    }

    public int getCreator_uid() {
        return this.creator_uid;
    }

    public void setReviewer(int reviewer) {
        this.reviewer = reviewer;
    }

    public int getReviewer() {
        return this.reviewer;
    }

    public void setCommit_from(String commit_from) {
        this.commit_from = commit_from;
    }

    public String getCommit_from() {
        return this.commit_from;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return this.length;
    }


    @Override
    public String toString() {
        return "Hitokoto{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", hitokoto='" + hitokoto + '\'' +
                ", type='" + type + '\'' +
                ", from='" + from + '\'' +
                ", from_who='" + from_who + '\'' +
                ", creator='" + creator + '\'' +
                ", creator_uid=" + creator_uid +
                ", reviewer=" + reviewer +
                ", commit_from='" + commit_from + '\'' +
                ", created_at='" + created_at + '\'' +
                ", length=" + length +
                '}';
    }
}