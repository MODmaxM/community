package com.ccy.community.pojo;

public class Comment {
    private Integer id;

    private Integer parentid;

    private Integer type;

    private String commentor;

    private Long gmtcreate;

    private Long gmtmodified;

    private Long likecount;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCommentor() {
        return commentor;
    }

    public void setCommentor(String commentor) {
        this.commentor = commentor == null ? null : commentor.trim();
    }

    public Long getGmtcreate() {
        return gmtcreate;
    }

    public void setGmtcreate(Long gmtcreate) {
        this.gmtcreate = gmtcreate;
    }

    public Long getGmtmodified() {
        return gmtmodified;
    }

    public void setGmtmodified(Long gmtmodified) {
        this.gmtmodified = gmtmodified;
    }

    public Long getLikecount() {
        return likecount;
    }

    public void setLikecount(Long likecount) {
        this.likecount = likecount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentid=").append(parentid);
        sb.append(", type=").append(type);
        sb.append(", commentor=").append(commentor);
        sb.append(", gmtcreate=").append(gmtcreate);
        sb.append(", gmtmodified=").append(gmtmodified);
        sb.append(", likecount=").append(likecount);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }
}