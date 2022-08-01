package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRegistResume is a Querydsl query type for RegistResume
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRegistResume extends EntityPathBase<RegistResume> {

    private static final long serialVersionUID = -1320070139L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRegistResume registResume = new QRegistResume("registResume");

    public final NumberPath<Long> registNo = createNumber("registNo", Long.class);

    public final QResume resume;

    public final QStudy study;

    public QRegistResume(String variable) {
        this(RegistResume.class, forVariable(variable), INITS);
    }

    public QRegistResume(Path<? extends RegistResume> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRegistResume(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRegistResume(PathMetadata metadata, PathInits inits) {
        this(RegistResume.class, metadata, inits);
    }

    public QRegistResume(Class<? extends RegistResume> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.resume = inits.isInitialized("resume") ? new QResume(forProperty("resume"), inits.get("resume")) : null;
        this.study = inits.isInitialized("study") ? new QStudy(forProperty("study")) : null;
    }

}

