package kr.couchcoding.memo.memo;

import kr.couchcoding.memo.controller.dto.MemoDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MemoService {
    @Autowired
    private MemoRepository memoRepository;
    @Autowired
    private ModelMapper modelMapper;

    public MemoDto createMemo(MemoDto memoDto){
        Memo memo = new Memo(memoDto.getTitle(), memoDto.getContents(), memoDto.getUserId());
        memo = memoRepository.save(memo); //id 생성, DB 저장
        return modelMapper.map(memo, MemoDto.class);
    }

    public MemoDto getMemo(Long id){
        Memo memo = memoRepository.getById(id); //DB에 생성된 ID로 가져온다.
        return modelMapper.map(memo, MemoDto.class);
    }


    // title로 가져오는 것 만들기
    public MemoDto getMemoByTitle (String title){
        Memo memo = memoRepository.findByTitle(title);
        return modelMapper.map(memo, MemoDto.class);
    }

    public Page<MemoDto> getMemosByContainWord(String word, Pageable pageable) {
        Page<Memo> memos = memoRepository.findByTitleContainingOrContentsContaining(word, word, pageable);
        return memos.map(memo -> modelMapper.map(memo, MemoDto.class));
    }

}
