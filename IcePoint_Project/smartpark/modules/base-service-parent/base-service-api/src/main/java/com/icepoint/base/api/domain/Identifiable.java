package com.icepoint.base.api.domain;

import java.io.Serializable;

public interface Identifiable<ID> extends Serializable {

    ID getId();
}
