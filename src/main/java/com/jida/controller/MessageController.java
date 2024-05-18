package com.jida.controller;

import static com.jida.constants.SuccessCode.MESSAGE_REPLY_SUCCESS;
import static com.jida.constants.SuccessCode.MESSAGE_SEND_SUCCESS;
import com.jida.dto.req.MessageRequestDto;
import com.jida.dto.res.message.MessageResponse;
import com.jida.dto.res.message.MessageSendResponse;
import com.jida.dto.res.message.MessageSendResponseDto;
import com.jida.service.MemberService;
import com.jida.service.MessageService;
import com.jida.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/message")
public class MessageController {
    private final MessageService messageService;
    private final JWTUtil jwtUtil;
    @PostMapping("/send/{memberId}")
    public ResponseEntity<MessageSendResponse> sendMessage(@Valid @RequestBody MessageRequestDto messageRequestDto, @PathVariable long memberId, HttpServletRequest request){
        long sendMemberId = jwtUtil.getUserId(request.getHeader("Authorization"));
        MessageSendResponseDto response = messageService.sendMessage(messageRequestDto, sendMemberId, memberId);
        return MessageSendResponse.newResponse(MESSAGE_SEND_SUCCESS, response);
    }

    @PostMapping("/room/{roomId}")
    public ResponseEntity<MessageResponse> replyMessage(@Valid @RequestBody MessageRequestDto messageRequestDto, @PathVariable long roomId, HttpServletRequest request){
        long sendMemberId = jwtUtil.getUserId(request.getHeader("Authorization"));
        messageService.replyMessage(messageRequestDto, roomId, sendMemberId);
        return MessageResponse.newResponse(MESSAGE_REPLY_SUCCESS);
    }

}
