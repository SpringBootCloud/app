package net.boddin.clouddemo.controller;

import net.boddin.clouddemo.dto.PostCreateDto;
import net.boddin.clouddemo.entity.Feed;
import net.boddin.clouddemo.entity.Post;
import net.boddin.clouddemo.entity.User;
import net.boddin.clouddemo.repository.FeedRepository;
import net.boddin.clouddemo.repository.PostRepository;
import net.boddin.clouddemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserRepository userRepository;
    private FeedRepository feedRepository;
    private PostRepository postRepository;

    @Autowired
    public UserController(UserRepository userRepository, FeedRepository feedRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.feedRepository = feedRepository;
        this.postRepository = postRepository;
    }

    // @PostMapping("/user/{id}/post")
    @GetMapping("/post/{feedId}/{text}")
    public int createPost(@PathVariable String text, @PathVariable int feedId){
//        User user = userRepository.findOne(id);
//        Feed feed = feedRepository.findOne(dto.getFeedId());
//        Post p = new Post();
//        p.setFeed(feed);
//        p.setUser(user);
//        p.setText(dto.getText());
//        p = postRepository.save(p);
//        return p.getId();
        return 0;
    }
}
