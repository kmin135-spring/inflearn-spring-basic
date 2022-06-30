package hello.core.member;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Member {
    private Long id;
    private String name;
    private Grade grade;
}
