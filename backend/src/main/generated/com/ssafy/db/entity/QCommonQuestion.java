package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommonQuestion is a Querydsl query type for CommonQuestion
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCommonQuestion extends EntityPathBase<CommonQuestion> {

    private static final long serialVersionUID = -1125257421L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommonQuestion commonQuestion = new QCommonQuestion("commonQuestion");

    public final StringPath contents = createString("contents");

    public final NumberPath<Long> questionNo = createNumber("questionNo", Long.class);

    public final StringPath questionType = createString("questionType");

    public final QStudy study;

    public final NumberPath<Long> writerNo = createNumber("writerNo", Long.class);

    public QCommonQuestion(String variable) {
        this(CommonQuestion.class, forVariable(variable), INITS);
    }

    public QCommonQuestion(Path<? extends CommonQuestion> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommonQuestion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommonQuestion(PathMetadata metadata, PathInits inits) {
        this(CommonQuestion.class, metadata, inits);
    }

    public QCommonQuestion(Class<? extends CommonQuestion> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.study = inits.isInitialized("study") ? new QStudy(forProperty("study")) : null;
    }

}

