package net.boddin.clouddemo.actuator;

import net.boddin.clouddemo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class PostsHealth implements HealthIndicator {

    private PostRepository postRepository;

    @Autowired
    public PostsHealth(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Health health() {
        long count = postRepository.count();
        if(count > 0){
            return Health.status(Status.UP).withDetail("postCount", count).build();
        } else {
            return Health.down().withDetail("postCount", count).build();
        }
    }
}
