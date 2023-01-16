package com.workshop.reddit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workshop.reddit.model.Post;
import com.workshop.reddit.model.Subreddit;
import com.workshop.reddit.user.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	List<Post> findAllBySubreddit(Subreddit subreddit);

	List<Post> findByUser(User user);
}