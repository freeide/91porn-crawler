package club.bluetroy.controller;

import club.bluetroy.crawler.dao.MovieRepository;
import club.bluetroy.crawler.domain.Movie;
import club.bluetroy.crawler.domain.MovieStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: heyixin
 * Date: 2019-02-14
 * Time: 15:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Transactional
public class MovieRepositoryTest {
    @Autowired
    private MovieRepository repository;
    private String key = "2ae67ee3fde76ac11111";
    private Movie movie;

    @Before
    public void setUp() {
        movie = new Movie("布里斯班小姐姐", "00:43", "2019年2月11日21时", "澳洲小哥哥可国内", 22995, 172, 2, 0, "http://91porn.com/view_video.php?viewkey=2ae67ee3fde76ac11111&page=1&viewtype=basic&category=hot");
        Movie save = repository.save(movie);
    }

    @Test
    public void testUpdateFilteredByKeys() {
        int updateCount = repository.updateFilteredByKeys(Collections.singletonList(key));
        assertEquals(1, updateCount);
    }

    @Test
    public void testFindAllByStatus() {
        List<Movie> scannedMovies = repository.findAllByStatus(MovieStatus.SCANNED);
        assertTrue(scannedMovies.size() > 0);
        assertEquals(MovieStatus.SCANNED, scannedMovies.get(0).getStatus());
    }

    @Test
    public void testFindByKey() {
        Movie movieFindByKey = repository.findByKey(key);
        assertEquals(0, movie.compareTo(movieFindByKey));
    }

    @Test
    public void testUpdateDownloadUrlByKey() {
        int updateCount = repository.updateDownloadUrlByKey("testdownloadUrl", key);
        assertEquals(1, updateCount);
    }


}