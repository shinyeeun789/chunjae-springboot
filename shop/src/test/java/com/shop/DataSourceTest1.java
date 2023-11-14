package com.shop;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
@Log4j2
public class DataSourceTest1 {

    @Autowired
    private DataSource dataSource;

    @Test
    public void connectionTest() throws SQLException {
        
        @Cleanup                                        // 사전 작업
        Connection con = dataSource.getConnection();
        log.info(con);

        // Assert : 확인하는 메서드
        Assertions.assertNotNull(con);                  // con이 비어있는지 확인
                
    }

}
