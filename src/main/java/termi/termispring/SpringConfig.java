package termi.termispring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import termi.termispring.repository.JdbcTemplateMessageRepository;
import termi.termispring.repository.MessageRepository;
import termi.termispring.service.MessageService;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MessageService messageService() {
        return new MessageService(messageRepository());
    }

    @Bean
    public MessageRepository messageRepository() {
        return new JdbcTemplateMessageRepository(dataSource);
    }

}
