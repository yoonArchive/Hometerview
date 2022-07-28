package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecording is a Querydsl query type for Recording
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRecording extends EntityPathBase<Recording> {

    private static final long serialVersionUID = 1365548143L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecording recording = new QRecording("recording");

    public final ListPath<Feedback, QFeedback> feedbacks = this.<Feedback, QFeedback>createList("feedbacks", Feedback.class, QFeedback.class, PathInits.DIRECT2);

    public final NumberPath<Long> recordNo = createNumber("recordNo", Long.class);

    public final StringPath saveDate = createString("saveDate");

    public final QStudy study;

    public final NumberPath<Long> userNo = createNumber("userNo", Long.class);

    public final StringPath videoUrl = createString("videoUrl");

    public QRecording(String variable) {
        this(Recording.class, forVariable(variable), INITS);
    }

    public QRecording(Path<? extends Recording> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecording(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecording(PathMetadata metadata, PathInits inits) {
        this(Recording.class, metadata, inits);
    }

    public QRecording(Class<? extends Recording> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.study = inits.isInitialized("study") ? new QStudy(forProperty("study")) : null;
    }

}

