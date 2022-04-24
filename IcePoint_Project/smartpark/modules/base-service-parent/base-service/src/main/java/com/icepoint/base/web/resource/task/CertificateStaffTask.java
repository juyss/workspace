package com.icepoint.base.web.resource.task;

import com.github.tangyi.msc.api.dto.SmsDto;
import com.github.tangyi.msc.api.feign.MscServiceClient;
import com.icepoint.base.api.domain.GenericEntity;
import com.icepoint.base.api.domain.SerializeType;
import com.icepoint.base.web.resource.component.query.FieldOperation;
import com.icepoint.base.web.resource.component.query.GenericQueryParameter;
import com.icepoint.base.web.resource.component.query.Match;
import com.icepoint.base.web.resource.component.query.QueryParameter;
import com.icepoint.base.web.resource.service.complex.upper.GenericEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * @Author longfei xiang
 * @Description 持证人员到期提醒 定时任务
 * @Date 2020/12/2
 * @Param
 * @return
 **/
@Component
@EnableScheduling
public class CertificateStaffTask {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private GenericEntityService genericEntityService;

    @Autowired
    private MscServiceClient mscServiceClient;

    //同步用户数据 每天执行一次
    @Scheduled(cron = "0 0 12 * * ?")
    //@Scheduled(cron = "*/5 * * * * ?")
    public void check() {
        try {
            logger.info("持证人员到期提醒的定时任务开始执行");
            LocalDate nowLocalDate = LocalDate.now();
            List<Map<String, String>> delayPersonList = new LinkedList<>();
            List<Map<String, String>> delayEnterpriseList = new LinkedList<>();

            QueryParameter queryParameter = new GenericQueryParameter();
            Map<String, FieldOperation> fieldOps = new LinkedHashMap<>();
            Match match = new Match(fieldOps);
            ((GenericQueryParameter) queryParameter).setMatch(match);
            List<GenericEntity> certificateStaffList = genericEntityService.page(queryParameter, "certificateStaff", SerializeType.VALUE, PageRequest.of(0, 99999, Sort.by(Sort.Direction.DESC, "id"))).getContent();

            for (GenericEntity genericProperties : certificateStaffList) {
                // dueDate - dueType <= now = delayed
                boolean delayed = false;
                if (!StringUtils.hasText(genericProperties.getPropertyValue("dueDate").toString())) {
                    continue;
                }
                LocalDate dueDate = LocalDate.parse(genericProperties.getPropertyValue("dueDate").toString());

                String dueType = genericProperties.getPropertyValue("dueType").toString();
                switch (dueType) {
                    // 到期当天提醒
                    case "0":
                        delayed = nowLocalDate.until(dueDate, ChronoUnit.DAYS) == 0;
                        break;
                    // 到期前三天提醒
                    case "1":
                        delayed = nowLocalDate.until(dueDate,ChronoUnit.DAYS) == 3;
                        break;
                    // 到期前五天提醒
                    case "2":
                        delayed = nowLocalDate.until(dueDate,ChronoUnit.DAYS) == 5;
                        break;
                }

                if (delayed) {
                    // 个人列表
                    HashMap<String, String> person = new HashMap<>();
                    person.put("receiver", genericProperties.getPropertyValue("contact").toString());
                    person.put("content", "您的" + genericProperties.getPropertyValue("certificateType").toString() + genericProperties.getPropertyValue("certificateNo").toString() + "即将过期，请您及时处理。");
                    delayPersonList.add(person);

                    // 企业列表
                    /*HashMap<String, String> enterprise = new HashMap<>();
                    GenericEntity enterpriseEntity = genericEntityService.get(null, "certificateStaff", genericProperties.getPropertyValue("enterpriseId"), SerializeType.VALUE);
                    enterprise.put("receiver", enterpriseEntity.getPropertyValue("entContacts").toString());
                    enterprise.put("content", "贵司的" + genericProperties.getPropertyValue("name").toString() + "的" + genericProperties.getPropertyValue("certificateType").toString() + "证件即将过期，请及时处理。");

                    delayEnterpriseList.add(enterprise);*/
                }
            }

            // 发短信提醒个人
            for (Map<String, String> map : delayPersonList) {
                SmsDto smsDto = new SmsDto();
                smsDto.setContent(map.get("content"));
                smsDto.setReceiver(map.get("receiver"));
                mscServiceClient.sendSms(smsDto);
            }
            // 提醒企业
            for (Map<String, String> map : delayEnterpriseList) {
                SmsDto smsDto = new SmsDto();
                smsDto.setContent(map.get("content"));
                smsDto.setReceiver(map.get("receiver"));
                mscServiceClient.sendSms(smsDto);
            }

        } catch (Exception e) {
            logger.info("持证人员到期提醒的定时任务执行异常 {}", e);
        }
    }

}
