package com.dt.dao;


import com.dt.common.StatusQuery;
import com.dt.vo.Verifcode;
import org.springframework.stereotype.Repository;

/**
 * Created by aQiu.
 */
@Repository
public interface VerifcodeDAO extends  BaseDAO<Verifcode> {
    Verifcode queryByCode(String phone);
    void updateCodeStatus(StatusQuery statusQuery);
}
