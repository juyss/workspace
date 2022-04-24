package com.github.tangyi.tools;

/**
 * @author gaokx
 * @Description
 * @create 2021-03-24 23:04
 **/
public class Constants {
  /**
   * 模板推送地址
   */
  public static String TEMPLATE_MESSAGE_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
  /**
   * TOKEN  获取地址
   */
  public static String TOKEN_ADDRESS = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
  /**
   * TOKEN测试是否可用地址
   */
  public static String TEST_TOKEN_ADDRESS = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=%s";
  /**
   * token key
   */
  public static String WX_ACCESS_TOKEN_KEY = "wx_access_token";
  /**
   * token 调用分布式锁key
   */
  public static String WX_ACCESS_LOCK_KEY = "wx_Access_lock";

  /**
   * 资讯消息模板id
   * 编号OPENTM401927615
   * 详细内容{{first.DATA}}
   * 新闻栏目：{{keyword1.DATA}}
   * 新闻标题：{{keyword2.DATA}}
   * 添加时间：{{keyword3.DATA}}
   * {{remark.DATA}}
   */
  public static String NEWS_TEMPLATE_ID = "TNFyGRC-S_vuHzavZPLdDJ1B8P504xdmtal_G0XofKs";
  /**
   * 获取关注用户列表
   */
  public final static String WECHAT_USER_GET_URL = "https://api.weixin.qq.com/cgi-bin/user/get?"; //?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID

}
