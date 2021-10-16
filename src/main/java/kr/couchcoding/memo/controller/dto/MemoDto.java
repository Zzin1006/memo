package kr.couchcoding.memo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemoDto {
    private Long id;
    private String userId;
    private String title;
    private String contents;

}
