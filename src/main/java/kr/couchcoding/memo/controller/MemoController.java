package kr.couchcoding.memo.controller;

import kr.couchcoding.memo.controller.dto.MemoDto;
import kr.couchcoding.memo.memo.Memo;
import kr.couchcoding.memo.memo.MemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// 사용자의 요청을 받는 부분 (사용자의 요청을 받아서 Service를 호출한다)
@RestController
@RequestMapping("/memos") // localhost:8080/memos
@Slf4j
public class MemoController {
    @Autowired
    private MemoService memoService;

    @PostMapping() // POST /memos
    public Memo createMemo(@RequestBody MemoDto memoDto){
        log.info("request : " + memoDto);
        return memoService.createMemo(memoDto);
    }

    @GetMapping("/{id}") // /memos/{id}
    public Memo getMemoByPath(@PathVariable Long id){
        return memoService.getMemo(id);
    }

//    @GetMapping()
//    public Memo getMemoByParams(@RequestParam Long id){
//        return memoService.getMemo(id);
//    }

    @GetMapping() // ?title={title}
    public Memo getMemoByTitle(@RequestParam String title){
        // call service
        return memoService.getMemoByTitle(title);
    }

}
