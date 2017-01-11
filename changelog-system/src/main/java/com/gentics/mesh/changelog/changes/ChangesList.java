package com.gentics.mesh.changelog.changes;

import java.util.ArrayList;
import java.util.List;

import com.gentics.mesh.changelog.Change;

/**
 * Stores a list of all changes in the system. Please note that the order of changes is very important and new changes should always be appended to the list
 * (bottom).
 */
public final class ChangesList {

	public static List<Change> getList() {
		List<Change> list = new ArrayList<>();
		list.add(new Change_266B8BCC3FFC473AAB8BCC3FFC173AA4());
		//list.add(new Change_A36C972476C147F3AC972476C157F3EF());
		list.add(new Change_093BEFB47FA4476FBE37FD27C613F7AA());
		list.add(new Change_610A32F04FC7414E8A32F04FC7614EF5());
		list.add(new Change_610A32F04FC7414E8A32F04FC7614EF3());
		list.add(new Change_07F0975BD47249C6B0975BD472E9C6A4());
		list.add(new Change_0A58BEF0E7E7488D98BEF0E7E7588D4D());
		return list;
	}
}
