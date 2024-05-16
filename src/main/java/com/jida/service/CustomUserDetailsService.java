package com.jida.service;

import com.jida.domain.Member;
import com.jida.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public CustomUserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member member = memberMapper.findById(memberId);
        if(member!=null){
            return new CustomUserDetails(member);
        }
        return null;
        //        return memberMapper.findById(memberId)
        //                .map(this::createUserDetails)
        //                .orElseThrow(() -> new UsernameNotFoundException("해당하는 회원을 찾을 수 없습니다."));
    }

    private CustomUserDetails createUserDetails(Member member){
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getAuthority().toString());

        return new CustomUserDetails(Member.createMember(member.getId(), member.getPassword(), member.getEmail(), member.getNickname(), member.getAuthority()));
        //return (UserDetails) Member.createMember(member.getId(), member.getPassword(), member.getEmail(), member.getNickname(), member.getAuthority());
    }
}