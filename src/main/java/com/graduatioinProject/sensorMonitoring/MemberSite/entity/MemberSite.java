package com.graduatioinProject.sensorMonitoring.MemberSite.entity;

import com.graduatioinProject.sensorMonitoring.member.entity.Member;
import com.graduatioinProject.sensorMonitoring.productData.site.entity.Site;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/09/13
 */

@Entity
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MemberSite {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "site_id")
    private Site site;
}
