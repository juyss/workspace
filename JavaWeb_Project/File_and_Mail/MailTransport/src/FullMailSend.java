import com.sun.mail.util.MailSSLSocketFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: FullMailSend
 * @Desc: 复杂邮件发送(包含附件和图片)
 * @package PACKAGE_NAME
 * @project File_and_Mail
 * @date 2020/7/21 10:49
 */
public class FullMailSend {
    public static void main(String[] args) {
        InputStream is = null;
        Transport transport = null;
        try {
            is = new FileInputStream("MailTransport\\src\\mailInfo.properties");
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

            //2. 获取Transport对象
            transport = session.getTransport();

            //3. 连接邮箱服务器
            transport.connect(host, user, password);

            //4. 创建复杂邮件
            MimeMessage message = new MimeMessage(session);
            InternetAddress senderAddress = new InternetAddress(user);//发件人(需开启smtp服务)
            InternetAddress receiverAddress = new InternetAddress(receiver);//收件人
            message.setFrom(senderAddress); //设置发件人
            message.setRecipient(Message.RecipientType.TO, receiverAddress); //设置收件人
            message.setSubject("FullMailSendTest");//设置主题

            //设置邮件内容(包含附件和图片)
            //图片
            MimeBodyPart image = new MimeBodyPart();
            DataHandler imgHandler = new DataHandler(new FileDataSource("MailTransport\\src\\1.jpg"));
            image.setDataHandler(imgHandler);
            image.setContentID("image");
            //附件
            MimeBodyPart attachment = new MimeBodyPart();
            DataHandler fileHandler = new DataHandler(new FileDataSource("MailTransport\\auth.txt"));
            attachment.setDataHandler(fileHandler);
            attachment.setFileName("auth.txt");
            //文本内容
            MimeBodyPart mail = new MimeBodyPart();
            mail.setContent("邮件正文\n图片<img src='cid:image'>\n邮件结束","text/html;charset=UTF-8");
            //封装全部内容
            MimeMultipart mimeContentPart = new MimeMultipart();
            mimeContentPart.addBodyPart(mail);
            mimeContentPart.addBodyPart(image);
            mimeContentPart.setSubType("related");
            MimeBodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(mimeContentPart);

            MimeMultipart fullMail = new MimeMultipart();
            fullMail.addBodyPart(contentPart);
            fullMail.addBodyPart(attachment);


            //添加到MimeMessage实例中
            message.setContent(fullMail);
            message.saveChanges();


            //5. 发送邮件
            transport.sendMessage(message, message.getAllRecipients());
            System.out.println("邮件发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("邮件发送失败");
        } finally {
            //6. 关闭资源
            try {
                if (transport != null)
                    transport.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
