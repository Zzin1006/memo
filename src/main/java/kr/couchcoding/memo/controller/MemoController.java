package kr.couchcoding.memo.controller;

import kr.couchcoding.memo.controller.dto.MemoDto;
import kr.couchcoding.memo.memo.Memo;
import kr.couchcoding.memo.memo.MemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

// 사용자의 요청을 받는 부분 (사용자의 요청을 받아서 Service를 호출한다)
@RestController
@RequestMapping("/memos") // localhost:8080/memos
@Slf4j
public class MemoController {
    @Autowired
    private MemoService memoService;

    @PostMapping() // POST /memos
    public MemoDto createMemo(@RequestBody MemoDto memoDto){
        log.info("request : " + memoDto);
        return memoService.createMemo(memoDto);
    }

    @GetMapping("/{id}") // /memos/{id}
    public MemoDto getMemoByPath(@PathVariable Long id){
        return memoService.getMemo(id);
    }

    @GetMapping()
    public Page<MemoDto> getMemoByFilter(@RequestParam(required = false) String word,
                                         @RequestParam(required = false) String author,
                                         Pageable pageable){
        // call service
        return memoService.getMemosByContainWordAndAuthor(word, author, pageable);
        //return memoService.getMemosByContainWord(word, pageable);
    }
}
