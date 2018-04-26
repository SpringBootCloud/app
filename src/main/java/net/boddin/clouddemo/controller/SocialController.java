package net.boddin.clouddemo.controller;

import net.boddin.clouddemo.dto.PostCreateDto;
import net.boddin.clouddemo.entity.Feed;
import net.boddin.clouddemo.entity.Post;
import net.boddin.clouddemo.entity.SmallFeed;
import net.boddin.clouddemo.entity.User;
import net.boddin.clouddemo.repository.FeedRepository;
import net.boddin.clouddemo.repository.PostRepository;
import net.boddin.clouddemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class SocialController {

    private UserRepository userRepository;
    private FeedRepository feedRepository;
    private PostRepository postRepository;

    @Autowired
    public SocialController(UserRepository userRepository, FeedRepository feedRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.feedRepository = feedRepository;
        this.postRepository = postRepository;
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping("/myfeeds")
    public Collection<SmallFeed> listFeeds(){
        User user = getCurrentUser();
        return feedRepository.findAllByUsersContains(user.getId());
    }

    @GetMapping("/post/{feedId}/{text}")
    public int createPost(@PathVariable String text, @PathVariable int feedId) {
        User user = getCurrentUser();
        return createPost(text, user.getId(), feedId);
    }


    @PostMapping("/post")
    public int createPost(@RequestBody PostCreateDto dto, @PathVariable int id) {
        User user = getCurrentUser();
        return createPost(dto.getText(), user.getId(), dto.getFeedId());
    }

    private int createPost(String text, int userId, int feedId) {
        User user = userRepository.findOne(userId);
        Feed feed = feedRepository.findOne(feedId);
        Post p = new Post();
        p.setFeed(feed);
        p.setUser(user);
        p.setText(text);
        p = postRepository.save(p);
        return p.getId();

    }
}
