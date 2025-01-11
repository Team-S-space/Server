package hackathon.spring.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTemp is a Querydsl query type for Temp
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTemp extends EntityPathBase<Temp> {

    private static final long serialVersionUID = -2031110760L;

    public static final QTemp temp = new QTemp("temp");

    public final hackathon.spring.domain.common.QBaseEntity _super = new hackathon.spring.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QTemp(String variable) {
        super(Temp.class, forVariable(variable));
    }

    public QTemp(Path<? extends Temp> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTemp(PathMetadata metadata) {
        super(Temp.class, metadata);
    }

}

