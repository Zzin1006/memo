package kr.couchcoding.memo.memo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository //DB랑 연결해주는 부분
public interface MemoRepository extends JpaRepository<Memo, Long>, JpaSpecificationExecutor<Memo> {

    Memo findByTitle (String title);

    Page<Memo> findByTitleContainingOrContentsContaining(String title, String contents, Pageable pageable);

    Page<Memo> findByUserId(String authorId, Pageable pageable);

}
