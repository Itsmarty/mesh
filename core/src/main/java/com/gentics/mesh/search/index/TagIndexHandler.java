package com.gentics.mesh.search.index;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.ActionResponse;
import org.springframework.stereotype.Component;

import com.gentics.mesh.core.data.Tag;
import com.gentics.mesh.core.data.TagFamily;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@Component
public class TagIndexHandler extends AbstractIndexHandler<Tag> {

	@Override
	String getIndex() {
		return "tag";
	}

	@Override
	String getType() {
		return "tag";
	}

	public void store(Tag tag, Handler<AsyncResult<ActionResponse>> handler) {
		Map<String, Object> map = new HashMap<>();
		Map<String, String> tagFields = new HashMap<>();
		tagFields.put("name", tag.getName());
		map.put("fields", tagFields);
		addBasicReferences(map, tag);
		addTagFamily(map, tag.getTagFamily());
		store(tag.getUuid(), map, handler);
	}

	@Override
	public void store(String uuid, Handler<AsyncResult<ActionResponse>> handler) {
		boot.tagRoot().findByUuid(uuid, rh -> {
			if (rh.result() != null && rh.succeeded()) {
				Tag tag = rh.result();
				store(tag, handler);
			} else {
				//TODO reply error? discard? log?
			}
		});

	}

	private void addTagFamily(Map<String, Object> map, TagFamily tagFamily) {
		Map<String, Object> tagFamilyFields = new HashMap<>();
		tagFamilyFields.put("name", tagFamily.getName());
		tagFamilyFields.put("uuid", tagFamily.getUuid());
		map.put("tagFamily", tagFamilyFields);
	}

	public void update(String uuid,  Handler<AsyncResult<ActionResponse>> handler) {
		// TODO Auto-generated method stub
		
	}
}
