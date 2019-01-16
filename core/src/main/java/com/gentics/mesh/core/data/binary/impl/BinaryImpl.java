package com.gentics.mesh.core.data.binary.impl;

import static com.gentics.mesh.core.data.relationship.GraphRelationships.HAS_FIELD;

import java.util.Base64;

import com.gentics.mesh.context.BulkActionContext;
import com.gentics.mesh.core.data.binary.Binary;
import com.gentics.mesh.core.data.generic.MeshVertexImpl;
import com.gentics.mesh.core.data.node.field.BinaryGraphField;
import com.gentics.mesh.core.data.node.field.impl.BinaryGraphFieldImpl;
import com.gentics.mesh.dagger.MeshInternal;
import com.gentics.mesh.graphdb.spi.Database;
import com.gentics.mesh.graphdb.spi.FieldType;
import com.gentics.mesh.storage.BinaryStorage;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.vertx.core.buffer.Buffer;

/**
 * @see Binary
 */
public class BinaryImpl extends MeshVertexImpl implements Binary {

	private static final Base64.Encoder BASE64 = Base64.getEncoder();

	public static void init(Database database) {
		database.addVertexType(BinaryImpl.class, MeshVertexImpl.class);
		database.addVertexIndex(BinaryImpl.class, true, Binary.SHA512SUM_KEY, FieldType.STRING);
	}

	@Override
	public Iterable<? extends BinaryGraphField> findFields() {
		return inE(HAS_FIELD).frameExplicit(BinaryGraphFieldImpl.class);
	}

	@Override
	public Flowable<Buffer> getStream() {
		BinaryStorage storage = MeshInternal.get().binaryStorage();
		return storage.read(getUuid());
	}

	@Override
	public Single<String> getBase64Content() {
		return getStream()
			.reduce(Buffer::appendBuffer)
			.map(buffer -> BASE64.encodeToString(buffer.getBytes()))
			.toSingle("");
	}

	@Override
	public void delete(BulkActionContext bac) {
		BinaryStorage storage = MeshInternal.get().binaryStorage();
		storage.delete(getUuid()).blockingAwait();
		getElement().remove();
	}

}
