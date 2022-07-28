package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResumeDetail is a Querydsl query type for ResumeDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QResumeDetail extends EntityPathBase<ResumeDetail> {

    private static final long serialVersionUID = 2001706912L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QResumeDetail resumeDetail = new QResumeDetail("resumeDetail");

    public final StringPath answer = createString("answer");

    public final NumberPath<Long> detailNo = createNumber("detailNo", Long.class);

    public final StringPath item = createString("item");

    public final NumberPath<Long> itemNo = createNumber("itemNo", Long.class);

    public final ListPath<PersonalQuestion, QPersonalQuestion> personalQuestions = this.<PersonalQuestion, QPersonalQuestion>createList("personalQuestions", PersonalQuestion.class, QPersonalQuestion.class, PathInits.DIRECT2);

    public final QResume resume;

    public QResumeDetail(String variable) {
        this(ResumeDetail.class, forVariable(variable), INITS);
    }

    public QResumeDetail(Path<? extends ResumeDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QResumeDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QResumeDetail(PathMetadata metadata, PathInits inits) {
        this(ResumeDetail.class, metadata, inits);
    }

    public QResumeDetail(Class<? extends ResumeDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.resume = inits.isInitialized("resume") ? new QResume(forProperty("resume"), inits.get("resume")) : null;
    }

}

