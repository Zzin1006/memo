package kr.couchcoding.memo.memo;

import kr.couchcoding.memo.controller.dto.MemoDto;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import javax.persistence.criteria.Predicate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
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

    // 검색어를 포함하는 모든 메모를 가져온다.
    public Page<MemoDto> getMemosByContainWord(String word, Pageable pageable) {
        Page<Memo> memos = memoRepository.findByTitleContainingOrContentsContaining(word, word, pageable);
        return memos.map(memo -> modelMapper.map(memo, MemoDto.class));
    }

    // 검색어를 포함하고 작성자가 author와 같은 모든 Memo를 가져온다.
    @Transactional(readOnly = true)
    public Page<MemoDto> getMemosByContainWordAndAuthor(String word, String author, Pageable pageable) {
        // findAll 메서드는 검색 조건이 맞는 모든 데이터를 리턴합니다.
        Page<Memo> memos = memoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            log.info("word : " + word);
            log.info("author : " + author);

            List<Predicate> predicates = new java.util.ArrayList<>();
            
            // 단어 검색 조건이 정상적으로 왔으면 
            if(word != null && word.length() > 0){
                // 제목이나 내용에 검색어가 포함된 것만 가져오기
                Predicate wordPredicate = criteriaBuilder.or(
                        criteriaBuilder.like(root.get("title"), "%" + word + "%"),
                        criteriaBuilder.like(root.get("contents"), "%" + word + "%")
                );
                predicates.add(wordPredicate);
            }
            // 작성자가 정상적으로 왔으면
            if(author != null && author.length() > 0){
                // 작성자가 검색어와 같은 것만 가져오기
                Predicate authorPredicate = criteriaBuilder.equal(root.get("userId"), author);
                predicates.add(authorPredicate);
            }
            
            // 모든 검색 조건을 and로 통합
            Predicate predicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            return predicate;
        }, pageable);
        // memos를 Dto로 변환
        return memos.map(memo -> modelMapper.map(memo, MemoDto.class));
    }

}
