package kr.couchcoding.memo.controller.dto;

import lombok.Data;

@Data
public class MemoDto {
    private String userId;
    private String title;
    private String contents;
}
