package com.lublog.provider.serviceimpl;

import com.lublog.provider.dao.CommentMapper;
import com.lublog.po.Comment;
import com.lublog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: java类作用描述CommentServiceImpl
 * @Author: lxy
 * @time: 2020/4/16 1:21
 */
@Component
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public List<Comment> allCommentsById(int bid) {
        return commentMapper.allCommentsById(bid);
    }

    @Override
    public void insertCommentById(String luser, int bid, String ccont) {
        commentMapper.insertCommentByid(luser, bid, ccont);
    }

    @Override
    public int findBidNum(int bid) {
        return commentMapper.findBlogNum(bid);
    }

    @Override
    public void deleteCommentsOfBlog(int bid) {
        commentMapper.deleteCommentsOfBlog(bid);
    }
}