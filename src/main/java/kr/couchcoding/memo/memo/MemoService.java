package kr.couchcoding.memo.memo;

import kr.couchcoding.memo.controller.dto.MemoDto;
import kr.couchcoding.memo.controller.dto.UserDto;
import kr.couchcoding.memo.user.User;
import kr.couchcoding.memo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemoService {
    @Autowired
    MemoRepository memoRepository;

    public Memo createMemo(MemoDto memoDto){
        Memo memo = new Memo(memoDto.getTitle(), memoDto.getContents(), memoDto.getUserId());
        return memoRepository.save(memo); //id 생성, DB 저장
    }

    public Memo getMemo(Long id){
        return memoRepository.getById(id); //DB에 생성된 ID로 가져온다.
    }


    // title로 가져오는 것 만들기
    public Memo getMemoByTitle (String title){ return memoRepository.findByTitle(title); }

}
