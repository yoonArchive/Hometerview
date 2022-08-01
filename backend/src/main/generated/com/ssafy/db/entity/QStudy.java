package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudy is a Querydsl query type for Study
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudy extends EntityPathBase<Study> {

    private static final long serialVersionUID = 471210823L;

    public static final QStudy study = new QStudy("study");

    public final ListPath<CommonQuestion, QCommonQuestion> commonQuestions = this.<CommonQuestion, QCommonQuestion>createList("commonQuestions", CommonQuestion.class, QCommonQuestion.class, PathInits.DIRECT2);

    public final StringPath comName = createString("comName");

    public final StringPath endDate = createString("endDate");

    public final ListPath<Recording, QRecording> recordings = this.<Recording, QRecording>createList("recordings", Recording.class, QRecording.class, PathInits.DIRECT2);

    public final ListPath<RegistResume, QRegistResume> registResumes = this.<RegistResume, QRegistResume>createList("registResumes", RegistResume.class, QRegistResume.class, PathInits.DIRECT2);

    public final StringPath startDate = createString("startDate");

    public final StringPath stdDay = createString("stdDay");

    public final StringPath stdDetail = createString("stdDetail");

    public final StringPath stdImg = createString("stdImg");

    public final NumberPath<Integer> stdLimit = createNumber("stdLimit", Integer.class);

    public final StringPath stdName = createString("stdName");

    public final NumberPath<Long> stdNo = createNumber("stdNo", Long.class);

    public final StringPath stdNotice = createString("stdNotice");

    public final EnumPath<StdType> stdType = createEnum("stdType", StdType.class);

    public final ListPath<StudyJoin, QStudyJoin> studyJoins = this.<StudyJoin, QStudyJoin>createList("studyJoins", StudyJoin.class, QStudyJoin.class, PathInits.DIRECT2);

    public QStudy(String variable) {
        super(Study.class, forVariable(variable));
    }

    public QStudy(Path<? extends Study> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudy(PathMetadata metadata) {
        super(Study.class, metadata);
    }

}

