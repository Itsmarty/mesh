package com.gentics.mesh.search.index;

import static com.gentics.mesh.search.index.MappingHelper.NAME_KEY;
import static com.gentics.mesh.search.index.MappingHelper.NOT_ANALYZED;
import static com.gentics.mesh.search.index.MappingHelper.STRING;
import static com.gentics.mesh.search.index.MappingHelper.fieldType;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.gentics.mesh.core.data.Role;
import com.gentics.mesh.core.data.root.RootVertex;

import io.vertx.core.json.JsonObject;

@Component
public class RoleIndexHandler extends AbstractIndexHandler<Role> {

	private static RoleIndexHandler instance;

	@PostConstruct
	public void setup() {
		instance = this;
	}

	public static RoleIndexHandler getInstance() {
		return instance;
	}

	@Override
	protected String getIndex() {
		return Role.TYPE;
	}

	@Override
	protected String getType() {
		return Role.TYPE;
	}

	@Override
	protected RootVertex<Role> getRootVertex() {
		return boot.meshRoot().getRoleRoot();
	}

	@Override
	protected Map<String, Object> transformToDocumentMap(Role role) {
		Map<String, Object> map = new HashMap<>();
		map.put(NAME_KEY, role.getName());
		addBasicReferences(map, role);
		return map;
	}

	@Override
	protected JsonObject getMapping() {
		JsonObject props = new JsonObject();
		props.put(NAME_KEY, fieldType(STRING, NOT_ANALYZED));
		return props;
	}
}