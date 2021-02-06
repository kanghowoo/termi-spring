package termi.termispring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import termi.termispring.repository.*;
import termi.termispring.service.MessageServiceImpl;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

//    @Bean
//    public MessageServiceImpl messageService() {
//        return new MessageServiceImpl(messageRepository());
//    }

    @Bean
    public MessageRepository messageRepository() {
        return new JdbcTemplateMessageRepository(dataSource);
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JdbcTemplateMemberRepository(dataSource);
    }

    @Bean
    public AccessTokenRepository accessTokenRepository () {
        return new JdbcTemplateAccessTokenRepository(dataSource);
    }

}
