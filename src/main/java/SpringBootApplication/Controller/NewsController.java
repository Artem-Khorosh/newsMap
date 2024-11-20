package SpringBootApplication.Controller;

import SpringBootApplication.News;
import SpringBootApplication.Services.NewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        News news = newsService.getById(id);
        if (news == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"News with id " + id + " not found.\"}");
        }
        return ResponseEntity.ok(news);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(newsService.getAll());
    }

    @PostMapping
    public ResponseEntity<News> create(@RequestBody News news) {
        News createdNews = newsService.create(news);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNews);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody News news) {
        News updatedNews = newsService.update(news);
        if (updatedNews == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"News with id " + news.getId() + " not found.\"}");
        }
        return ResponseEntity.ok(updatedNews);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id) {
        if (newsService.deleteById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"News with id " + id + " not found.\"}");
    }
}
