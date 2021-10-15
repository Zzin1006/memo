package kr.couchcoding.memo.memo;

import kr.couchcoding.memo.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Memo {
    @Id // PrimaryKey
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID 자동 생성
    private Long id;
    @Column
    private String title;
    @Column
    private String contents;
    @Column(name="user_id")
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    User user;

    public Memo(String title, String contents, String userId){
        this.title = title;
        this.contents = contents;
        this.userId = userId;
    }
}
