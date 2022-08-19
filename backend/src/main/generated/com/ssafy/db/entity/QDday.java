package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDday is a Querydsl query type for Dday
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDday extends EntityPathBase<Dday> {

    private static final long serialVersionUID = 846021498L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDday dday = new QDday("dday");

    public final NumberPath<Long> ddayNo = createNumber("ddayNo", Long.class);

    public final StringPath ddayTitle = createString("ddayTitle");

    public final QUser user;

    public QDday(String variable) {
        this(Dday.class, forVariable(variable), INITS);
    }

    public QDday(Path<? extends Dday> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDday(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDday(PathMetadata metadata, PathInits inits) {
        this(Dday.class, metadata, inits);
    }

    public QDday(Class<? extends Dday> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

