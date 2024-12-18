package exercise;

import java.net.URI;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> index(@RequestParam(defaultValue = "10") Integer limit) {
         var list = posts.stream()
                .limit(limit)
                .toList();
         return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(posts.size()))
                .body(list);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> show(@PathVariable String id) {
        var page = posts.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
        return page.map(post -> ResponseEntity.status(HttpStatus.OK).body(post))
                   .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
       // var status = page.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> create(@RequestBody Post data) {
        posts.add(data);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(data);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post data) {
        var findPost = posts.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
        var status = HttpStatus.NO_CONTENT;
        if (findPost.isPresent()) {
            var page = findPost.get();
            page.setId(data.getId());
            page.setTitle(data.getTitle());
            page.setBody(data.getBody());
            status = HttpStatus.OK;
        }
        return ResponseEntity.status(status)
                .body(data);
    }
    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
