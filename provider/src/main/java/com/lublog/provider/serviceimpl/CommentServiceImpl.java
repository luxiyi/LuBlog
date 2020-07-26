package com.lublog.provider.serviceimpl;

import com.lublog.provider.dao.CommentMapper;
import com.lublog.po.Comment;
import com.lublog.service.CommentService;
import com.lublog.vo.CommentShow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Description: java类作用描述CommentServiceImpl
 * @Author: lxy
 * @time: 2020/4/16 1:21
 */
@Component
@Slf4j
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public List<Comment> allCommentsById(int bid) {
        return commentMapper.allCommentsById(bid);
    }

    @Override
    public void insertCommentById(int blogId, String observer, String commenter, String contact, String commentContent, Date commentDate) {
        commentMapper.insertCommentById(blogId, observer, commenter,contact, commentContent, commentDate);
    }

    @Override
    public int findBidNum(int bid) {
        return commentMapper.findBlogNum(bid);
    }

    @Override
    public void deleteCommentsOfBlog(int bid) {
        commentMapper.deleteCommentsOfBlog(bid);
    }


    @Override
    public Integer queryAllCommentsTotalPage() {
        int total = commentMapper.queryAllCommentsTotalPage();
        int totalPage = total % 12 == 0 ? (total / 12) : (total / 12 + 1);
        return totalPage;
    }

    @Override
    public List<Comment> findAllByIndex(int index, int count) {
        return commentMapper.findAllByIndex(index, count);
    }

    @Override
    public List<Comment> queryOneBlogCommentByIndex(Integer blogId, int index, int count) {
        return commentMapper.queryOneBlogCommentByIndex(blogId, index, count);
    }

    @Override
    public Integer queryOneBlogCommentTotalPage(int blogId) {
        int total = commentMapper.queryOneBlogCommentsCount(blogId);
        int totalPage = total % 12 == 0 ? (total / 12) : (total / 12 + 1);
        return totalPage;
    }

    @Override
    public void deleteOneComment(Integer blogId) {
        commentMapper.deleteOneComment(blogId);
    }

    @Override
    public int queryOneBlogOfComment(Integer commentId) {
        return commentMapper.queryOneBlogOfComment(commentId);
    }

    @Override
    public int queryOneBlogCommentsCount(int blogId) {
        return commentMapper.queryOneBlogCommentsCount(blogId);
    }

    @Override
    public int queryCommentAllCount() {
        return commentMapper.queryCommentAllCount();
    }
}
