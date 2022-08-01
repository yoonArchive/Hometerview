package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFeedback is a Querydsl query type for Feedback
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFeedback extends EntityPathBase<Feedback> {

    private static final long serialVersionUID = -179501753L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFeedback feedback = new QFeedback("feedback");

    public final StringPath contents = createString("contents");

    public final NumberPath<Long> feedbackNo = createNumber("feedbackNo", Long.class);

    public final QRecording recording;

    public final QStudy study;

    public final NumberPath<Long> userNo = createNumber("userNo", Long.class);

    public final StringPath writeDate = createString("writeDate");

    public final NumberPath<Long> writerNo = createNumber("writerNo", Long.class);

    public QFeedback(String variable) {
        this(Feedback.class, forVariable(variable), INITS);
    }

    public QFeedback(Path<? extends Feedback> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFeedback(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFeedback(PathMetadata metadata, PathInits inits) {
        this(Feedback.class, metadata, inits);
    }

    public QFeedback(Class<? extends Feedback> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.recording = inits.isInitialized("recording") ? new QRecording(forProperty("recording"), inits.get("recording")) : null;
        this.study = inits.isInitialized("study") ? new QStudy(forProperty("study")) : null;
    }

}

