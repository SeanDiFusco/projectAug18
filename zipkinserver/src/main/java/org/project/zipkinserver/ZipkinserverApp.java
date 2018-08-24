package org.project.zipkinserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class ZipkinserverApp 
{
    public static void main( String[] args )
    {
        SpringApplication.run(ZipkinserverApp.class, args);
    }
}
