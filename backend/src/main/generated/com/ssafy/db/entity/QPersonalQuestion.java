package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPersonalQuestion is a Querydsl query type for PersonalQuestion
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPersonalQuestion extends EntityPathBase<PersonalQuestion> {

    private static final long serialVersionUID = 1824964104L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPersonalQuestion personalQuestion = new QPersonalQuestion("personalQuestion");

    public final StringPath contents = createString("contents");

    public final NumberPath<Long> questionNo = createNumber("questionNo", Long.class);

    public final QResumeDetail resumeDetail;

    public final EnumPath<Saved> saved = createEnum("saved", Saved.class);

    public final NumberPath<Long> stdNo = createNumber("stdNo", Long.class);

    public final NumberPath<Long> writerNo = createNumber("writerNo", Long.class);

    public QPersonalQuestion(String variable) {
        this(PersonalQuestion.class, forVariable(variable), INITS);
    }

    public QPersonalQuestion(Path<? extends PersonalQuestion> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPersonalQuestion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPersonalQuestion(PathMetadata metadata, PathInits inits) {
        this(PersonalQuestion.class, metadata, inits);
    }

    public QPersonalQuestion(Class<? extends PersonalQuestion> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.resumeDetail = inits.isInitialized("resumeDetail") ? new QResumeDetail(forProperty("resumeDetail"), inits.get("resumeDetail")) : null;
    }

}

