package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;

import exercise.model.Post;
import exercise.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {
    private final List<Post> posts = Data.getPosts();

    @GetMapping("/users/{userId}/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> index(@PathVariable Integer userId) {
        return posts.stream()
                .filter(p -> p.getUserId() == userId)
                .toList();
    }

    @PostMapping("/users/{userId}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@PathVariable Integer userId, @RequestBody Post data) {
        data.setUserId(userId);
        posts.add(data);
        return data;
    }
}
// END
