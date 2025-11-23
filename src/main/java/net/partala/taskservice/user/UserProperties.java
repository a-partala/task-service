package net.partala.taskservice.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.user")
public class UserProperties {

    @Getter
    @Setter
    private int assignedTasksLimit;
}
