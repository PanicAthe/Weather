package panicathe.weather.repository;

import panicathe.weather.domain.Memo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import panicathe.weather.repository.JdbcMemoRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JdbcMemoRepositoryTest {

    @Autowired
    JdbcMemoRepository jdbcMemoRepository;

    @Test
    void insertMemoTest(){
        //given
        Memo newMemo = new Memo(2, "Insert Memo test");
        //when
        jdbcMemoRepository.save(newMemo);
        //then
        Optional<Memo> result = jdbcMemoRepository.findById(1);
        assertSame("Insert Memo test", result.get().getText());
    }

    @Test
    void findAllMemoTest(){
        //given

        //when
        List<Memo> memoList = jdbcMemoRepository.findAll();

        //then
        System.out.println(memoList);
        assertNotNull(memoList);
    }
}