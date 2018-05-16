package com.cms.entity;

public class Course {
    private Integer id;

    private String startDate;

    private String endDate;

    private Short classHour;

    private String testMode;

    private Integer studentNum;
  
    private Integer choiceNum;
    
    private Integer complete;
    
    private String tId;

    private Integer baseCourseId;
    
    private String teacherName;
    private String courseName;
    
    private String result;
    private Integer score;
    
    public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/*
    public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	

    public BaseCourse getBaseCourse() {
		return baseCourse;
	}

	public void setBaseCourse(BaseCourse baseCourse) {
		this.baseCourse = baseCourse;
	}
*/
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Short getClassHour() {
        return classHour;
    }

    public void setClassHour(Short classHour) {
        this.classHour = classHour;
    }

    public String getTestMode() {
        return testMode;
    }

    public void setTestMode(String testMode) {
        this.testMode = testMode == null ? null : testMode.trim();
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId == null ? null : tId.trim();
    }

    public Integer getBaseCourseId() {
        return baseCourseId;
    }

    public void setBaseCourseId(Integer baseCourseId) {
        this.baseCourseId = baseCourseId;
    }

	public Integer getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(Integer studentNum) {
		this.studentNum = studentNum;
	}

	public Integer getChoiceNum() {
		return choiceNum;
	}

	public void setChoiceNum(Integer choiceNum) {
		this.choiceNum = choiceNum;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", startDate=" + startDate + ", endDate="
				+ endDate + ", classHour=" + classHour + ", testMode="
				+ testMode + ", studentNum=" + studentNum + ", choiceNum="
				+ choiceNum + ", tId=" + tId + ", baseCourseId=" + baseCourseId
				+ ", teacherName=" + teacherName + ", courseName=" + courseName
				+ "]";
	}

	public Integer getComplete() {
		return complete;
	}

	public void setComplete(Integer complete) {
		this.complete = complete;
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
		this.result = result;
	}

}