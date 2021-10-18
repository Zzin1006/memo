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

    public Page<MemoDto> getMemosByContainWord(String word, Pageable pageable) {
        Page<Memo> memos = memoRepository.findByTitleContainingOrContentsContaining(word, word, pageable);
        return memos.map(memo -> modelMapper.map(memo, MemoDto.class));
    }

    public Page<MemoDto> getMemosByContainWord2(String word, Pageable pageable){
        Page<Memo> memos = null;
        if(word != null && word.length() > 0){
            memos = memoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
                return criteriaBuilder.or(
                        criteriaBuilder.like(root.get("title"), "%" + word + "%"),
                        criteriaBuilder.like(root.get("contents"), "%" + word + "%")
                );
            }, pageable);
        } else {
            memos = memoRepository.findAll(pageable);
        }

        return memos.map(memo -> modelMapper.map(memo, MemoDto.class));
    }

    @Transactional(readOnly = true)
    public Page<MemoDto> getMemosByContainWordAndAuthor(String word, String author, Pageable pageable) {
        Page<Memo> memos = memoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            log.info("word : " + word);
            log.info("author : " + author);
            List<Predicate> predicates = new java.util.ArrayList<>();
            if(word != null && word.length() > 0){
                Predicate wordPredicate = criteriaBuilder.like(root.get("title"), "%" + word + "%");
                wordPredicate = criteriaBuilder.or(wordPredicate, criteriaBuilder.like(root.get("contents"), "%" + word + "%"));
                predicates.add(wordPredicate);
            }
            if(author != null && author.length() > 0){
                predicates.add(criteriaBuilder.equal(root.get("userId"), author));
            }
            Predicate predicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            log.info("predicate : " + predicate);
            return predicate;
        }, pageable);
        return memos.map(memo -> modelMapper.map(memo, MemoDto.class));
    }

}
