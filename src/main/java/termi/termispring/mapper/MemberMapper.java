package termi.termispring.mapper;


import termi.termispring.domain.Member;
import termi.termispring.dto.MemberCreateRequest;

public interface MemberMapper {

    Member requestToMember(MemberCreateRequest request);
}
