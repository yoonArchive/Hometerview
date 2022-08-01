package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecruit is a Querydsl query type for Recruit
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRecruit extends EntityPathBase<Recruit> {

    private static final long serialVersionUID = 528887100L;

    public static final QRecruit recruit = new QRecruit("recruit");

    public final ListPath<Apply, QApply> applies = this.<Apply, QApply>createList("applies", Apply.class, QApply.class, PathInits.DIRECT2);

    public final StringPath comName = createString("comName");

    public final StringPath endDate = createString("endDate");

    public final NumberPath<Long> recruitNo = createNumber("recruitNo", Long.class);

    public final EnumPath<RecruitStatus> recruitStatus = createEnum("recruitStatus", RecruitStatus.class);

    public final StringPath recruitTitle = createString("recruitTitle");

    public final StringPath startDate = createString("startDate");

    public final StringPath stdDay = createString("stdDay");

    public final StringPath stdDetail = createString("stdDetail");

    public final StringPath stdImg = createString("stdImg");

    public final NumberPath<Integer> stdLimit = createNumber("stdLimit", Integer.class);

    public final StringPath stdName = createString("stdName");

    public final EnumPath<StdType> stdType = createEnum("stdType", StdType.class);

    public QRecruit(String variable) {
        super(Recruit.class, forVariable(variable));
    }

    public QRecruit(Path<? extends Recruit> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRecruit(PathMetadata metadata) {
        super(Recruit.class, metadata);
    }

}

