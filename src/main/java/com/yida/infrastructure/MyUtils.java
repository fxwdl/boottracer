package com.yida.infrastructure;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class MyUtils
{
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
	{
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
}