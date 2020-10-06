package com.pengal.Siren.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {
    String hostName;

    @Value("${DB_USER:#{environment.DB_USER}}") String databaseUser;
    @Value("${DB_PASS:#{environment.DB_PASS}}") String databasePass;
    @Value("${DB_USER:#{environment.DB_USER}}") String databaseHost;
    @Value("${DB_PORT:#{environment.DB_PORT}}") String databasePort;
    @Value("${DB_NAME:#{environment.DB_NAME}}") String databaseName;
    @Value("${DB_SCHEMA:#{environment.DB_SCHEMA}}") String databaseSchema;
}
