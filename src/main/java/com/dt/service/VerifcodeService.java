package com.dt.service;

import com.dt.common.StatusQuery;
import com.dt.vo.Verifcode;

public interface VerifcodeService extends BaseService<Verifcode>{
    Verifcode queryByCode(String phone);
    void updateCodeStatus(StatusQuery statusQuery);
}
