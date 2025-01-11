package hackathon.spring.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hackathon.spring.apiPayload.code.status.ErrorStatus;
import hackathon.spring.apiPayload.exception.GeneralException;
import hackathon.spring.domain.Location;
import hackathon.spring.domain.QReview;
import hackathon.spring.domain.Review;
import hackathon.spring.domain.enums.Sun;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom{

    private static final int ALL = 0;
    private static final int SUN_RISE = 1;
    private static final int SUN_SET = 2;

    private final JPAQueryFactory jpaQueryFactory;
    private final QReview review = QReview.review;

    @Override
    public List<Review> findAllByRegionWithSunEvent(String region, Integer sunEvent, Long lastId, Pageable pageable) {

        BooleanExpression locationExp = createLocationDynamicExp(region);
        BooleanExpression sunEventExp = createSunEventDynamicExp(sunEvent);


        // 1. lastId가 null인 경우 - 더 이상 조회할 데이터가 없음
        if (lastId == null) {
            throw new GeneralException(ErrorStatus.NO_MORE_REVIEW_DATA);
        }

        // 2. lastId가 0인 경우 - 첫 페이지 요청
        if (lastId == 0L) {
            return jpaQueryFactory
                    .selectFrom(review)
                    .where(locationExp, sunEventExp)
                    .orderBy(review.id.desc())  // ID 기준으로 정렬
                    .limit(pageable.getPageSize())
                    .fetch();
        }

        BooleanExpression cursorExp = createCursorExp(lastId);
        return jpaQueryFactory
                .selectFrom(review)
                .where(locationExp, sunEventExp, cursorExp)
                .orderBy(review.id.desc())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private BooleanExpression createCursorExp(Long lastId) {
        return review.id.lt(lastId);
    }

    private BooleanExpression createLocationDynamicExp(String region) {
        if (region != null) {

            Long count = jpaQueryFactory
                    .select(review.count())
                    .from(review)
                    .where(review.location.address.contains(region))
                    .fetchOne();

            if (count == null || count == 0) {
                throw new GeneralException(ErrorStatus.REGION_NOT_FOUND);
            }

            return review.location.address.contains(region);
        }
        return null;
    }

    private BooleanExpression createSunEventDynamicExp(Integer sunEvent) {
        if (sunEvent == null) {
            return null;
        }

        return switch (sunEvent) {
            case SUN_RISE -> review.sunEvent.eq(Sun.SUNRISE);
            case SUN_SET -> review.sunEvent.eq(Sun.SUNSET);
            case ALL -> review.sunEvent.in(Sun.SUNRISE, Sun.SUNSET);
            default -> throw new GeneralException(ErrorStatus.SUN_EVENT_NOT_FOUND);
        };
    }
}
