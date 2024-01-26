package com.app.splitwise.mapper;

import com.app.splitwise.dto.MemberRequestDTO;
import com.app.splitwise.modal.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberRequestMapper implements Mapper<MemberRequestDTO, Member> {
    @Override
    public Member map(MemberRequestDTO source) {
        return Member.builder().paid(source.getPaid()).owed(source.getOwed()).userName(source.getUserName()).build();
    }
}
