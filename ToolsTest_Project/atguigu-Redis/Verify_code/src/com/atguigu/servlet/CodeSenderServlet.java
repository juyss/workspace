package com.atguigu.servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.utils.VerifyCodeConfig;

import redis.clients.jedis.Jedis;

/**
 * Servlet implementation class CodeSenderServlet
 */
@WebServlet("/CodeSenderServlet")
public class CodeSenderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CodeSenderServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //获取手机号
        String phone_no = request.getParameter("phone_no");
        //获取验证码
        String code = getCode();
        //拼接key
        String codeKey = VerifyCodeConfig.PHONE_PREFIX + phone_no + VerifyCodeConfig.CODE_SUFFIX; //Verify_code:12345:code
        String countKey = VerifyCodeConfig.PHONE_PREFIX + phone_no + VerifyCodeConfig.COUNT_SUFFIX; //Verify_code:12345:count

        Jedis jedis = new Jedis(VerifyCodeConfig.HOST, VerifyCodeConfig.PORT);
        //判断发送验证码的次数
        String count = jedis.get(countKey);
        if (count == null) {
            //代表第一次
            jedis.setex(countKey, VerifyCodeConfig.SECONDS_PER_DAY, "1");
        } else if (Integer.parseInt(count) <= VerifyCodeConfig.COUNT_TIMES_1DAY) {
            jedis.incr(countKey);
        } else if (Integer.parseInt(count) > VerifyCodeConfig.COUNT_TIMES_1DAY) {
            response.getWriter().print("limit");
            jedis.close();
            return;
        }

        //向redis中进行存储，以手机号为键，以验证码为值
        jedis.setex(codeKey, VerifyCodeConfig.CODE_TIMEOUT, code);
        jedis.close();
        response.getWriter().print(true);

    }

    /**
     * 获取随机数字作为验证码
     * @return String 验证码
     */
    private String getCode() {
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < VerifyCodeConfig.CODE_LEN; i++) {
            int rand = random.nextInt(10);
            code.append(rand);
        }
        return code.toString();
    }

}
