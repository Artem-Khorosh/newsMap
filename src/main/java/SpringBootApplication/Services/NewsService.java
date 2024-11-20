package SpringBootApplication.Services;

import SpringBootApplication.News;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class NewsService {
    private final ConcurrentHashMap<Long, News> newsMap = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public News getById(long id) {
        System.out.println("Get news by id: " + id);
        return newsMap.get(id);
    }

    public List<News> getAll() {
        System.out.println("Get all news");
        return newsMap.values().stream().collect(Collectors.toList());
    }

    public News create(News news) {
        System.out.println("Create news: " + news);
        long id = idCounter.incrementAndGet();
        news.setId(id);
        newsMap.put(id, news);
        return news;
    }

    public News update(News news) {
        System.out.println("Update news: " + news);
        if (newsMap.containsKey(news.getId())) {
            newsMap.put(news.getId(), news);
            return news;
        }
        return null;
    }

    public boolean deleteById(long id) {
        System.out.println("Delete news by id: " + id);
        return newsMap.remove(id) != null;
    }
}
