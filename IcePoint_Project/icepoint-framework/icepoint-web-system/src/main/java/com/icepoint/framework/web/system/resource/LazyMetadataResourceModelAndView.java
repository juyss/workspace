package com.icepoint.framework.web.system.resource;

import org.springframework.data.util.Lazy;

import java.util.Map;

/**
 * @author Jiawei Zhao
 */
public class LazyMetadataResourceModelAndView implements ResourceModelAndView {

    private final ResourceModel model;

    private final Lazy<Map<String , Object>> view;

    private final Lookup lookup;

    private final ResourceModelViewResolver viewResolver;

    public LazyMetadataResourceModelAndView(ResourceModel model, Lookup lookup,
            ResourceModelViewResolver viewResolver) {
        this.model = model;
        this.lookup = lookup;
        this.viewResolver = viewResolver;
        this.view = Lazy.of(() -> {
            ViewType viewType = this.lookup.getViewType();
            return this.viewResolver.resolve(this.model, viewType);
        });
    }

    @Override
    public ResourceModel getModel() {
        return model;
    }

    @Override
    public Map<String, Object> getView() {
        return view.get();
    }
}
