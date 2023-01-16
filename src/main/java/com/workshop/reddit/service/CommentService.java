package com.workshop.reddit.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.workshop.reddit.auth.AuthenticationService;
import com.workshop.reddit.dto.CommentsDto;
import com.workshop.reddit.exceptions.PostNotFoundException;
import com.workshop.reddit.exceptions.SpringRedditException;
import com.workshop.reddit.mapper.CommentMapper;
import com.workshop.reddit.model.Comment;
import com.workshop.reddit.model.Post;
import com.workshop.reddit.repository.CommentRepository;
import com.workshop.reddit.repository.PostRepository;
import com.workshop.reddit.user.User;
import com.workshop.reddit.user.UserRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CommentService {
	private static final String POST_URL = "";
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final AuthenticationService authService;
	private final CommentMapper commentMapper;
	private final CommentRepository commentRepository;

	public void save(CommentsDto commentsDto) {
		Post post = postRepository.findById(commentsDto.getPostId())
				.orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId().toString()));
		Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
		commentRepository.save(comment);

		sendCommentNotification(post.getUser().getUsername() + " posted a comment on your post." + POST_URL,
				post.getUser());
	}

	private void sendCommentNotification(String message, User user) {
		// mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented
		// on your post", user.getEmail(), message));
	}

	public List<CommentsDto> getAllCommentsForPost(Long postId) {
		Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));
		return commentRepository.findByPost(post).stream().map(commentMapper::mapToDto).collect(toList());
	}

	public List<CommentsDto> getAllCommentsForUser(String userName) {
		User user = userRepository.findByEmail(userName).orElseThrow(() -> new UsernameNotFoundException(userName));
		return commentRepository.findAllByUser(user).stream().map(commentMapper::mapToDto).collect(toList());
	}

	public boolean containsSwearWords(String comment) {
		if (comment.contains("shit")) {
			throw new SpringRedditException("Comments contains unacceptable language");
		}
		return false;
	}
}