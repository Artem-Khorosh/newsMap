package SpringBootApplication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class News {
    private long id;
    private String title;
    private String text;
    private Instant date;

}
