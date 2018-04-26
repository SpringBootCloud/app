package net.boddin.clouddemo.controller;


import net.boddin.clouddemo.config.MyConfig;
import net.boddin.clouddemo.entity.Feed;
import net.boddin.clouddemo.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
// @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
public class DemoController {

    @Value("${my.property}")
    private String prop;

    @Autowired
    private MyConfig myConfig;

    private FeedRepository feedRepository;

    public DemoController(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
    }

    @Secured({"ROLE_USER", "ROLE_ADMINISTRATOR"})
    @RequestMapping("/one")
    public Object getDemoObject(){
        String foo = "hello " + prop + myConfig.getString1();
        return foo;
    }

    @RequestMapping("/admin")
    @Secured("ROLE_ADMINISTRATOR")
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
