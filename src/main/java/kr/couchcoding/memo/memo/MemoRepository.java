package kr.couchcoding.memo.memo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //DB랑 연결해주는 부분
public interface MemoRepository extends JpaRepository<Memo, Long> {

    Memo findByTitle (String title);
}
