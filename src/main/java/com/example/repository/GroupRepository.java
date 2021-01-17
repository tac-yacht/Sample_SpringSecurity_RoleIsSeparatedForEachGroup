package com.example.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.Group;

/** 疑似groupレポジトリ */
//説明用なのでCrudRepository風ではあるものの実装しない
@Service
public class GroupRepository {
	static final Map<String, Group> groups = Collections.synchronizedMap(new HashMap<>());
	public GroupRepository() {
		groups.put("groupA", new Group("groupA", "ぐるーぷえー"));
		groups.put("groupB", new Group("groupA", "ぐるーぷびー"));
		groups.put("groupC", new Group("groupA", "ぐるーぷしー"));
	}

	public Optional<Group> findById(String id) {
		return Optional.ofNullable(groups.get(id));
	}
}
