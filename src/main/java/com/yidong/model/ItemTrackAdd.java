package com.yidong.model;

public class ItemTrackAdd {
    private Integer id;

    private String oldTitle;

    private String oldEvaluate;

    private String time;

    private String content;

    private String sign;

    private String trackId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOldTitle() {
        return oldTitle;
    }

    public void setOldTitle(String oldTitle) {
        this.oldTitle = oldTitle == null ? null : oldTitle.trim();
    }

    public String getOldEvaluate() {
        return oldEvaluate;
    }

    public void setOldEvaluate(String oldEvaluate) {
        this.oldEvaluate = oldEvaluate == null ? null : oldEvaluate.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId == null ? null : trackId.trim();
    }
}