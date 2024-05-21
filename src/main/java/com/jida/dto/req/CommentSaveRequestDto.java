package com.jida.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
public class CommentSaveRequestDto {
    @NotBlank(message = "댓글 내용이 없습니다.")
    @Length(max = 2000, message = "글자수가 2000자를 초과했습니다.")
    private String content;
    private Long parentId;
    private Long postId;
}
