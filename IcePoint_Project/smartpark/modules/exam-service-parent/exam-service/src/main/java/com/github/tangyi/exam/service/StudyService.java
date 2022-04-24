package com.github.tangyi.exam.service;

import com.github.tangyi.exam.api.dto.req.StudyReq;
import com.github.tangyi.exam.api.dto.res.StudyDoingRes;
import com.github.tangyi.exam.api.dto.res.StudyOverRes;
import com.github.tangyi.exam.api.dto.res.StudyStartRes;

public interface StudyService {
    StudyStartRes start(StudyReq studyReq, String anonUserId);

    StudyDoingRes doing(StudyReq studyReq, String anonUserId);

    StudyOverRes finish(StudyReq studyReq, String anonUserId);
}
