package kr.couchcoding.memo.memo;

import kr.couchcoding.memo.controller.dto.MemoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class MemoServiceBak {
    Long lastId = 0L;
    Map<Long, Memo> memos = new HashMap<>();
    Map<String, Memo> memosByTitle = new HashMap<>();

    public Memo getMemo(Long id){
        return memos.get(id);
    }

    //get by title
    public Memo getMemo(String title){
        return memosByTitle.get(title);
    }

    public Memo createMemo(MemoDto memoDto){
        log.info("memoDto: " + memoDto);

        Memo memo = new Memo();
        lastId++;
        memo.setId(lastId);
        memo.setTitle(memoDto.getTitle());
        memo.setContents(memoDto.getContents());
        memos.put(lastId, memo);
        memosByTitle.put(memoDto.getTitle(), memo);
        return memo;
    }
}
