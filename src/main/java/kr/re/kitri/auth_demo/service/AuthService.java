package kr.re.kitri.auth_demo.service;

import kr.re.kitri.auth_demo.dao.MemberDao;
import kr.re.kitri.auth_demo.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private MemberDao memberDao;

    public Member authenticate(String userId, String pw) {

        //Member member = this.memberDao.selectMemberByIdAndPassword(userId, pw);

        return new Member("kim", "김갑돌");
    }
}
