package com.server.tdMindJelly.basicEmo;

import jakarta.persistence.*;
import lombok.*;

/**
* @className : BasicEmo
* @description : BasicEmo(기본 감정) Entity
* @modification : 2024-12-23 (Jinhyeok)
* @author : Jinhyeok
* @date : 2024-12-23
* @version : 1.0
     ====개정이력(Modification Information)====
  수정일        수정자        수정내용    -----------------------------------------
   2024-12-23     Jinhyeok      주석최초생성
 */
@Getter
@Entity
@NoArgsConstructor
@Table(name="basicEmo")
public class BasicEmo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emoId;

    @Column(length = 10,nullable = false)
    private String emoName;

    @Column(nullable = false)
    private String emoIcon;

    @Builder
    public BasicEmo(Long emoId,String emoName,String emoIcon){
        this.emoId = emoId;
        this.emoName = emoName;
        this.emoIcon=emoIcon;
    }
}
