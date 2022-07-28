package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyJoin is a Querydsl query type for StudyJoin
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudyJoin extends EntityPathBase<StudyJoin> {

    private static final long serialVersionUID = -1583582959L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudyJoin studyJoin = new QStudyJoin("studyJoin");

    public final NumberPath<Long> joinNo = createNumber("joinNo", Long.class);

    public final QStudy study;

    public final QUser user;

    public QStudyJoin(String variable) {
        this(StudyJoin.class, forVariable(variable), INITS);
    }

    public QStudyJoin(Path<? extends StudyJoin> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyJoin(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyJoin(PathMetadata metadata, PathInits inits) {
        this(StudyJoin.class, metadata, inits);
    }

    public QStudyJoin(Class<? extends StudyJoin> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.study = inits.isInitialized("study") ? new QStudy(forProperty("study")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

