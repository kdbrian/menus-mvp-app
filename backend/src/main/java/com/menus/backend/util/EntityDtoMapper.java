package com.menus.backend.util;

public abstract class EntityDtoMapper<E, D> {
    public abstract E fromDto(D d);

    public abstract D toDto(E e);
}
