package com.icepoint.framework.workorder.configuration.agilebpm.sys;

import cn.hutool.extra.mail.MailAccount;
import com.dstz.sys.api.jms.producer.JmsProducer;
import com.dstz.sys.simplemq.producer.SynchronousQueueProducer;
import com.dstz.sys.util.EmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties({MQMailConfigProperties.class, AbSimpleMessageQueueProperties.class})
public class SimpleMqAutoConfiguration {

    private final MQMailConfigProperties mQMailConfigProperties;

    @ConditionalOnExpression("'${ab.simple-mq.message-queue-type:synchronous}'.toLowerCase() == 'synchronous'")
    @Bean
    public JmsProducer synchronousQueueProducer() {
        return new SynchronousQueueProducer();
    }

    @Component
    class MailAccountAutoConfiguration implements ApplicationListener<ContextRefreshedEvent> {
        MailAccountAutoConfiguration() {
        }

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            if (event.getApplicationContext().getParent() == null) {
                this.setEmailConfiguration();
            }

        }

        private void setEmailConfiguration() {
            MailAccount account = new MailAccount();
            account.setHost(mQMailConfigProperties.getSendHost());
            account.setPort(mQMailConfigProperties.getSendPort());
            account.setFrom(mQMailConfigProperties.getMailAddress());
            account.setUser(mQMailConfigProperties.getNickName());
            account.setPass(mQMailConfigProperties.getPassword());
            account.setSslEnable(mQMailConfigProperties.isSSL());
            EmailUtil.setAccount(account);
        }
    }
}