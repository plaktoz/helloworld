package com.plaktoz.todoist.todoistapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.webclient")
public class WebClientProperties {

    private String callapp2;

    public String getCallapp2() {
        return callapp2;
    }

    public void setCallapp2(String callapp2) {
        this.callapp2 = callapp2;
    }
}
