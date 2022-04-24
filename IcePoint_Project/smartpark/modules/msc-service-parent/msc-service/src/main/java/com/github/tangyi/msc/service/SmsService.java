package com.github.tangyi.msc.service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.utils.JsonMapper;
import com.github.tangyi.core.common.util.JsonUtils;
import com.github.tangyi.core.mybatis.service.CommonDaoService;
import com.github.tangyi.model.SmsRecord;
import com.github.tangyi.msc.api.dto.SmsDto;
import com.github.tangyi.msc.api.model.SmsResponse;
import com.github.tangyi.msc.properties.RhyxSmsProperties;
import com.github.tangyi.msc.properties.SmsProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tangyi
 * @date 2019/6/22 13:23
 */
@Slf4j
@AllArgsConstructor
@Service
public class SmsService {

    private static Logger logger = LoggerFactory.getLogger(SmsService.class);

    private final SmsProperties smsProperties;
    private final RhyxSmsProperties rhyxSmsProperties;
    private RestTemplate restTemplate;
    private CommonDaoService commonDaoService;

    /**
     * 发送短信
     *
     * @param smsDto smsDto
     * @return SmsResponse
     * @author tangyi
     * @date 2019/06/22 13:28
     */
    public SmsResponse sendSms(SmsDto smsDto) {
        if (smsProperties != null && smsProperties.getAppKey() != null) return sendSmsAliyu(smsDto);
        else return sendSmsRhyx(smsDto);
    }

    private SmsResponse sendSmsRhyx(SmsDto smsDto) {
        String[] templates = rhyxSmsProperties.getTemplates();
        int index = -1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < templates.length; i++) {
            String template = templates[i];
            String regStr = "^" + template.replaceAll("#P#", "(.*)") + "$";
            Pattern compile = Pattern.compile(regStr);
            Matcher matcher = compile.matcher(smsDto.getContent());

            if (matcher.find()) {
                index = i;
                int i1 = matcher.groupCount();
                logger.info("groupCount: {}", i1);
                for (int j = 1; j <= i1; j++) {
                    sb.append(matcher.group(j)).append(",");
                }
                break;
            }
        }
        String msg = sb.length() > 0 ? sb.substring(0, sb.length() - 1) : "";
        if (index == -1) throw new CommonException("短信内容不能匹配模板");
        SmsRecord smsRecord = new SmsRecord();
        smsRecord.setContent(smsDto.getContent());
        smsRecord.setMobile(smsDto.getReceiver());
        logger.info("templateId is {}.", index);
        smsRecord.setTempletid(rhyxSmsProperties.getTemplateids()[index]);
        smsRecord.setTempletName(rhyxSmsProperties.getTemplateNames()[index]);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json;chaset=UTF-8");

        RhyxParam param = getParam(msg, smsRecord.getMobile(), smsRecord.getTempletid());
        HttpEntity<String> entity = new HttpEntity<String>(JsonUtils.toString(param), headers);

        ResponseEntity<String> result = restTemplate.exchange(rhyxSmsProperties.getSendApiUrl(), HttpMethod.POST, entity, String.class);
        logger.info(result.getBody());
        JsonNode jsonNode = JsonUtils.toJsonNode(result.getBody());
        smsRecord.setSuccess(jsonNode.get("resultcode").asInt() == 0 ? 1 : 0);
        smsRecord.setResJson(result.getBody());
        smsRecord.setSendBy("rhyx");
        smsRecord.setCommonValue("admin", "EXAM", "gitee");
        commonDaoService.insert(smsRecord);

        SmsResponse smsResponse = new SmsResponse();
        smsResponse.setCode(jsonNode.get("resultcode").asText());
        smsResponse.setMessage(jsonNode.get("resultmsg").asText());
        smsResponse.setRequestId(jsonNode.get("taskid").asText());
        return smsResponse;
    }

    public SmsResponse sendSmsAliyu(SmsDto smsDto) {
        DefaultProfile profile = DefaultProfile.getProfile(smsProperties.getRegionId(), smsProperties.getAppKey(), smsProperties.getAppSecret());
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(smsProperties.getDomain());
        request.putQueryParameter("RegionId", smsProperties.getRegionId());
        request.putQueryParameter("PhoneNumbers", smsDto.getReceiver());
        request.putQueryParameter("SignName", smsProperties.getSignName());
        request.putQueryParameter("TemplateCode", smsProperties.getTemplateCode());
        request.putQueryParameter("TemplateParam", smsDto.getContent());
        request.setVersion(smsProperties.getVersion());
        request.setAction(smsProperties.getAction());
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info("response: {}", response.getData());
            if (response.getHttpStatus() != 200)
                throw new CommonException(response.getData());
            SmsResponse smsResponse = JsonMapper.getInstance().fromJson(response.getData(), SmsResponse.class);
            if (smsResponse == null)
                throw new CommonException("Parse response error");
            if (!"OK".equals(smsResponse.getCode()))
                throw new CommonException(smsResponse.getMessage());
            return smsResponse;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new CommonException("Send message failed: " + e.getMessage());
        }
    }

    @Data
    public static class RhyxParam {

        private String cpcode;//渠道在融合云信平台申请的cpcode登录自服务平台，商户信息页面中的【商户编码】
        private String msg;//模板参数值，如果包含多个参数，以半角英文逗号分隔
        private String mobiles;//11位手机号，如果包含多个手机号，请用半角英文逗号分隔，最多支持300个手机号
        private String excode = "";//渠道自定义接入号的扩展码，可为空；为时传空字符串””
        private String templetid;//渠道在融合云信平台申请的模板短信ID
        private String sign;//MD5签名，签名字符串为cpcode+ msg+mobiles+excode+templetid+key，其中key为融合云信平台分配的私钥，生成签名后转换为小写字符串。

    }

    private RhyxParam getParam(String msg, String moblies, String templetid) {
        RhyxParam rhyxParam = new RhyxParam();
        rhyxParam.setCpcode(rhyxSmsProperties.getCpcode());
        rhyxParam.setMsg(msg);
        rhyxParam.setMobiles(moblies);
        rhyxParam.setTempletid(templetid);
        rhyxParam.setSign(makeMD5(rhyxParam.getCpcode() + msg + moblies + templetid + rhyxSmsProperties.getKey()));
        return rhyxParam;
    }

    public static String makeMD5(String plainText) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            re_md5 = buf.toString().toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }

}


