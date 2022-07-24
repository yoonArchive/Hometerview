package com.ssafy.api.service;

import com.ssafy.api.request.PostWritePostReq;
import com.ssafy.db.entity.Posts;
import com.ssafy.db.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public void writePost(PostWritePostReq postWritePostReq) {
        Posts posts = new Posts();
        posts.setPostTitle(postWritePostReq.getPostTitle());
        posts.setStdName(postWritePostReq.getStdName());
        posts.setStdDetail(postWritePostReq.getStdDetail());
        posts.setStdImg(postWritePostReq.getStdImg());
        posts.setStdType(postWritePostReq.getStdType());
        posts.setComName(postWritePostReq.getComName());
        posts.setStartDate(postWritePostReq.getStartDate());
        posts.setEndDate(postWritePostReq.getEndDate());
        posts.setStdDay(postWritePostReq.getStdDay());
        posts.setStdLimit(postWritePostReq.getStdLimit());
        postRepository.save(posts);
    }
}
