package com.workshop.reddit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
	private Long postId;
	private String subredditName;
	private String postName;
	private String url;
	private String description;
}