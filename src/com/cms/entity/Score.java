package com.cms.entity;

public class Score {
    private Integer id;

    private Integer score;

    private String result;

    private Integer cId;

    private String sId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId == null ? null : sId.trim();
    }

	@Override
	public String toString() {
		return "Score [id=" + id + ", score=" + score + ", result=" + result
				+ ", cId=" + cId + ", sId=" + sId + "]";
	}
    
}