package com.tanmay.assignment.contrpller;

import com.tanmay.assignment.entity.Comment;
import com.tanmay.assignment.entity.Post;
import com.tanmay.assignment.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class BlogController {
        @Autowired
        private BlogService blogService;

        @PostMapping
        public Post createPost(@RequestBody Post post) {
            return blogService.createPost(post);
        }

        @PostMapping("/{id}/comments")
        public Comment addCommentToPost(@PathVariable Long id, @RequestBody Comment comment) {
            return blogService.addCommentToPost(id, comment);
        }

        @GetMapping("/{id}/comments")
        public List<Comment> getCommentsByPostId(@PathVariable Long id) {
            return blogService.getCommentsByPostId(id);
        }
}
