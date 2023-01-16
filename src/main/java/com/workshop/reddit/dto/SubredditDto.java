package com.workshop.reddit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubredditDto {
	private Long id;
	private String name;
	private String description;
	private Integer numberOfPosts;
}