package com.github.tangyi.exam.model.bo;

import com.github.tangyi.model.ExamStudyTaskCourseUserInfo;
import lombok.Data;
import my.convert.Map2Bean;

@Data
public class StudyBo extends ExamStudyTaskCourseUserInfo {

    private Long duration = 5L;

    private Long lastServerTime = 0L;//上一次服务器的时间戳

    private Long lastClientTime = 0L;//上一次客户端的时间

    private Integer courseStudyTime;//课程的最少学习时长

    private Integer courseTime;//课程时长

    public StudyBo() {
    }

    public StudyBo(Long duration, Long lastServerTime, Long lastClientTime, Integer courseStudyTime, Integer courseTime, ExamStudyTaskCourseUserInfo examStudyTaskCourseUserInfo) {
        this.duration = duration;
        this.lastServerTime = lastServerTime;
        this.lastClientTime = lastClientTime;
        this.courseStudyTime = courseStudyTime;
        this.courseTime = courseTime;
        try {
            Map2Bean.getInstance().getBeanFromBean(StudyBo.class, this, examStudyTaskCourseUserInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断当前时间是否合理 ,如果合理，课程学时累加duration
     *
     * @param currentTime
     * @return
     */
    public boolean isRational(Long currentTime) {
        long now = System.currentTimeMillis();
        boolean rational = (currentTime - lastClientTime >= duration - 1000) && (now - lastServerTime < duration * 2)// -1000 是因为网络服务的处理时间，导致误差
                && (now - lastServerTime >= duration - 1000);
        lastClientTime = currentTime;
        lastServerTime = now;
        if (rational) {
            this.setStudyTime((this.getStudyTime() == null ? 0 : this.getStudyTime()) + duration.intValue() / 1000);
            if (this.getStudyTime() > this.courseTime) this.setStudyTime(this.courseTime);
        }

        return rational;
    }

    public boolean isOver() {
        boolean b = this.getStudyTime() - courseStudyTime + duration.intValue() / 1000 > 0;
        setIsFinish(b ? null : 0);
        return b;
    }

    public ExamStudyTaskCourseUserInfo getEntity() {
        try {
            return Map2Bean.getInstance().getBeanFromBean(ExamStudyTaskCourseUserInfo.class, this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
