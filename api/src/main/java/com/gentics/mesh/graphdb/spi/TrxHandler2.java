package com.gentics.mesh.graphdb.spi;

import java.util.concurrent.Callable;

import rx.functions.Function;

/**
 * Represents a function with zero arguments.
 */
@FunctionalInterface
public interface TrxHandler2<T> extends Function, Callable<T> {

	T call() throws Exception;
}