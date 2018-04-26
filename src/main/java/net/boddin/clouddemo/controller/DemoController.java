package net.boddin.clouddemo.controller;


import net.boddin.clouddemo.config.MyConfig;
import net.boddin.clouddemo.entity.Feed;
import net.boddin.clouddemo.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
// @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
public class DemoController {


    private CounterService counterService;

    @Value("${my.property}")
    private String prop;

    @Autowired
    private MyConfig myConfig;

    private FeedRepository feedRepository;

    public DemoController(@Qualifier("counterService") CounterService counterService, FeedRepository feedRepository) {
        this.counterService = counterService;
        this.feedRepository = feedRepository;
    }

    @RequestMapping("/one")
    public Object getDemoObject(){
        counterService.increment("custom.metric");
        String foo = "hello " + prop + myConfig.getString1();
        return foo;
    }

    @RequestMapping("/admin")
    public Object helloAdmin(){
        String foo = "hello admin";
        return foo;
    }

    @RequestMapping("/feed")
    public Object createFeed(){
        Feed f = new Feed();
        f.setName("Feed 1");
        feedRepository.save(f);
        return "ok";
    }
}
