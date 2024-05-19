package com.jida.controller;

import com.jida.dto.req.MessageRequestDto;
import com.jida.dto.res.message.*;
import com.jida.service.MessageService;
import com.jida.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.jida.constants.SuccessCode.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/message")
public class MessageController {
    private final MessageService messageService;
    private final JWTUtil jwtUtil;
    @PostMapping("/{memberId}")
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

    @GetMapping("")
    public ResponseEntity<MessageRoomResponse> showRoomList(HttpServletRequest request){
        long memberId = jwtUtil.getUserId(request.getHeader("Authorization"));
        MessageRoomResponseDto response = messageService.showRoomList(memberId);
        return MessageRoomResponse.newResponse(MESSAGE_ROOM_READ_SUCCESS, response);
    }
    @GetMapping("{roomId}")
    public ResponseEntity<MessageDetailResponse> showMessageList(@PathVariable long roomId, HttpServletRequest request){
        long memberId = jwtUtil.getUserId(request.getHeader("Authorization"));
        MessageDetailResponseDto response = messageService.showMessageList(memberId, roomId);
        return MessageDetailResponse.newResponse(MESSAGE_READ_SUCCESS, response);
    }


}
