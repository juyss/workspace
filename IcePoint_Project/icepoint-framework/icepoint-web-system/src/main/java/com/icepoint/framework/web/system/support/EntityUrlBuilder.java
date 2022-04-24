package com.icepoint.framework.web.system.support;

import com.icepoint.framework.core.util.MessageTemplates;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Jiawei Zhao
 */
public class EntityUrlBuilder extends LinkedList<String> {

    public EntityUrlBuilder() {
    }

    public EntityUrlBuilder(Collection<? extends String> c) {
        super(c);
    }

    @Nullable
    public String getPath() {

        if (isEmpty()) {
            return null;
        }

        return String.join("/", this);
    }

    public static EntityUrlBuilder fromUrl(String path) {

        Assert.hasText(path, MessageTemplates.notNull("path"));

        return fromUrl(path.split("/"));
    }

    public static EntityUrlBuilder fromUrl(String... segments) {

        Assert.notEmpty(segments, MessageTemplates.notNull("segments"));

        return new EntityUrlBuilder(Arrays.asList(segments));
    }

    public static EntityUrlBuilder fromUrl(Iterable<String> segments) {

        if (segments instanceof Collection) {
            return new EntityUrlBuilder((Collection<String>) segments);
        }

        EntityUrlBuilder builder = new EntityUrlBuilder();
        segments.forEach(builder::add);
        return builder;
    }
}
