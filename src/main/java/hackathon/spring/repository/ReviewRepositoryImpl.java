package hackathon.spring.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hackathon.spring.domain.Location;
import hackathon.spring.domain.QReview;
import hackathon.spring.domain.Review;
import hackathon.spring.domain.enums.Sun;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom{

    private static final int ALL = 0;
    private static final int SUN_RISE = 1;
    private static final int SUN_SET = 2;

    private final JPAQueryFactory jpaQueryFactory;
    private final LocationRepository locationRepository;
    private final QReview review = QReview.review;

    @Override
    public List<Review> findAllByRegionWithSunEvent(String region, Integer sunEvent, Long lastId, Pageable pageable) {

        BooleanExpression locationExp = createLocationDynamicExp(region);
        BooleanExpression sunEventExp = createSunEventDynamicExp(sunEvent);

        // lastId가 0인 경우 (첫 페이지 요청)
        if (lastId == null || lastId == 0L) {
            lastId = jpaQueryFactory
                    .select(review.id.max())
                    .from(review)
                    .where(locationExp, sunEventExp)
                    .fetchOne();
        }
        BooleanExpression cursorExp = createCursorExp(lastId);
        // lastId가 있는 경우 (다음 페이지 요청)
        return jpaQueryFactory
                .selectFrom(review)
                .where(locationExp, sunEventExp, cursorExp)
                .orderBy(review.createdAt.desc())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private BooleanExpression createCursorExp(Long lastId) {
        return review.id.loe(lastId);
    }

    private BooleanExpression createLocationDynamicExp(String region) {
        if (region != null) {
            return review.location.address.contains(region);
        } else {
            return null;
        }
    }

    private BooleanExpression createSunEventDynamicExp(Integer sunEvent) {
        if (sunEvent == null) {
            return null;
        }

        return switch (sunEvent) {
            case SUN_RISE -> review.sunEvent.eq(Sun.SUNRISE);
            case SUN_SET -> review.sunEvent.eq(Sun.SUNSET);
            default -> review.sunEvent.in(Sun.SUNRISE, Sun.SUNSET);
        };
    }
}
