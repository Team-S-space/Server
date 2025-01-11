package hackathon.spring.domain;

import hackathon.spring.domain.common.BaseEntity;
import hackathon.spring.domain.enums.Sun;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicUpdate
@DynamicInsert
@Builder
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", columnDefinition = "varchar(20)")
    private String title;

    @Column(name = "image_url", columnDefinition = "varchar(20)")
    private String image_url;

    @Enumerated(EnumType.STRING)
    private Sun sun_event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;
}
