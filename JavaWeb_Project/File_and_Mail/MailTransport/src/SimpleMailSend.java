import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: SimpleMailSend
 * @Desc: 简单邮件发送实现
 * @package PACKAGE_NAME
 * @project File_and_Mail
 * @date 2020/7/18 19:25
 */
public class SimpleMailSend {
    public static void main(String[] args) {
        Transport transport = null;
        try {
            InputStream is = new FileInputStream("MailTransport\\src\\mailInfo.properties");
            Properties props = new Properties();
            props.load(is);
            MailSSLSocketFactory mailSSLSocket = new MailSSLSocketFactory();
            mailSSLSocket.setTrustAllHosts(true);
            props.put("mail.smtp.ssl.socketFactory", mailSSLSocket);
            String host = props.getProperty("mail.host");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            String receiver = props.getProperty("receiver");
            //1.创建整个程序所需的环境信息的Session对象
            PasswordAuthentication passwordAuthentication = new PasswordAuthentication(user, password);
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return passwordAuthentication;
                }
            };
            Session session = Session.getDefaultInstance(props, authenticator);

            session.setDebug(true);//开启session的debug模式

            //2.获取Transport对象
            transport = session.getTransport();

            //3.连接邮件服务器

            transport.connect(host, user, password);

            //4.创建简单邮件(文本邮件)
            MimeMessage message = new MimeMessage(session); //创建session对象
            InternetAddress senderAddress = new InternetAddress(user);//发件人(需开启smtp服务)
            InternetAddress receiverAddress = new InternetAddress(receiver);//收件人

            message.setFrom(senderAddress);//设置发件人
            message.setRecipient(Message.RecipientType.TO, receiverAddress);//设置收件人类型和收件人
            message.setSubject("SimpleTextMail");//设置主题

            //两个方法不能同时存在,后面的会覆盖前面的邮件内容
//            message.setText("第一段<br>第二段<br>第三段<br>", "UTF-8");//设置发送内容(普通文本内容)
            message.setContent("<h1>一级标题</h1><hr><h2>二级标题</h2>", "text/html;charset=UTF-8");//设置发送内容(HTML格式)

            //5.发送邮件
            transport.sendMessage(message, message.getAllRecipients());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6.关闭资源
            try {
                if (transport != null)
                    transport.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }


    }
}
