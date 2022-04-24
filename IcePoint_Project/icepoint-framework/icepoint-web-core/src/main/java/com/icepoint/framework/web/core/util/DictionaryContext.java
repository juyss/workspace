package com.icepoint.framework.web.core.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jiawei Zhao
 */
public class DictionaryContext {

    private static final ThreadLocal<Set<String>> INCLUDED = ThreadLocal.withInitial(HashSet::new);
    private static final ThreadLocal<Set<String>> EXCLUDED = ThreadLocal.withInitial(HashSet::new);

    private DictionaryContext() {
        throw new UnsupportedOperationException();
    }

    public static void included(String... paths) {
        if (paths.length <= 0) {
            INCLUDED.set(new HashSet<>());
            return;
        }

        Set<String> included = INCLUDED.get();
        Collections.addAll(included, paths);
        INCLUDED.set(included);
    }

    public static void excluded(String... paths) {
        if (paths.length <= 0) {
            EXCLUDED.set(new HashSet<>());
            return;
        }

        Set<String> excluded = EXCLUDED.get();
        Collections.addAll(excluded, paths);
        EXCLUDED.set(excluded);
    }

    public static Set<String> getIncluded() {
        return INCLUDED.get();
    }

    public static Set<String> getExcluded() {
        return EXCLUDED.get();
    }

    public static void clear() {
        INCLUDED.remove();
        EXCLUDED.remove();
    }
}
