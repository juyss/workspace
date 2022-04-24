package com.icepoint.base.sql;

public interface Join extends Aliasable {

    From getParent();

    JoinOn getOn();
}
