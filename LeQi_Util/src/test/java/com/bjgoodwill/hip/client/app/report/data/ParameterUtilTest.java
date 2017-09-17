/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bjgoodwill.hip.client.app.report.data;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zhuqing
 */
public class ParameterUtilTest {

    public ParameterUtilTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void testSomeMethod() {

        Long loginUserId = 10L;
        Long loginOrgId = 11L;
        Long loginHospitalId = 12L;
        Long loginServiceId = 13L;
        String sql = "select t.input_py      as INPUT_PY,\n"
                + "       t.mnemonic_code as MNEMONIC_CODE,\n"
                + "       t.element_name  as DISPLAY,\n"
                + "       t.id            as DATA_VALUE\n"
                + "  from DICT_ELEMENT t\n"
                + " where t.dict_id = 772 and ${loginUserId} and ${loginOrgId}";

        sql = sql.replace("${loginUserId}", loginUserId.toString());
        sql = sql.replace("${loginOrgId}", loginOrgId.toString());
        sql = sql.replace("${loginHospitalId}", loginHospitalId.toString());
        sql = sql.replace("${loginServiceId}", loginServiceId.toString());

        System.err.println(sql);

    }

}
