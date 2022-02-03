package io.java_web_base.services;

import java.util.List;

public interface BaseService<TDTO, TFORM, TID> {

    List<TDTO> getAll();

    TDTO getOne(TID id);

    boolean insert(TFORM form);

    TDTO insertWithReturnValue(TFORM form);

    boolean delete(TID id);

    TDTO update(TFORM form, TID id);

}
