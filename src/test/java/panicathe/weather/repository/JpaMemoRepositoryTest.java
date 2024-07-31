package panicathe.weather.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import panicathe.weather.domain.Memo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaMemoRepositoryTest {

    @Autowired
    JpaMemoRepository JpaMemoRepository;
    @Autowired
    private JpaMemoRepository jpaMemoRepository;

    @Test
    void insertMemoTest(){
        //given
        Memo newMemo = new Memo(10, "This is jpa memo");
        //when
        jpaMemoRepository.save(newMemo);
        //then
        List<Memo> memoList = jpaMemoRepository.findAll();
        assertTrue(memoList.size() > 0);
    }

    @Test
    void findByIdTest(){
        //given
        Memo newMemo = new Memo(11, "jpa");
        //when
        Memo memo = jpaMemoRepository.save(newMemo);
        //then
        Optional<Memo> result = jpaMemoRepository.findById(memo.getId());
        assertEquals("jpa", result.get().getText());
    }
}