package com.icepoint.framework.data.domain;

import java.io.Serializable;

public interface Identifiable<ID> extends Serializable {

    ID getId();
}
