package hackathon.spring.domain;

import hackathon.spring.domain.common.BaseEntity;
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
public class Location extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "latitude", columnDefinition = "varchar(20)")
    private String latitude;

    @Column(name = "longitude", columnDefinition = "varchar(20)")
    private String longitude;

    @Column(name = "address", columnDefinition = "varchar(100)")
    private String address;
}
