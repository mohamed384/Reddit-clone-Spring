package com.workshop.reddit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {
	private Long id;
	private Long postId;
	private Instant createdDate;
	private String text;
	private String userName;
}