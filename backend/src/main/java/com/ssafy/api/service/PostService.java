package com.ssafy.api.service;

import com.ssafy.api.request.PostWritePostReq;
import com.ssafy.db.entity.Posts;

import java.util.List;

public interface PostService {
    void writePost(PostWritePostReq postWritePostReq);

    List<Posts> getList();

    Posts getByPostNo(long postNo);
}
