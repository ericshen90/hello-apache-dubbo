package com.eric.apache.dubbo.consumer.controller;

import com.eric.apache.dubbo.provider.api.EchoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author EricShen
 * @date 2019-11-28
 */
@RestController
@RefreshScope
public class EcoController {

  @Reference(version = "1.0.0")
  private EchoService echoService;

  @Value("${user.name}")
  private String username;

  @GetMapping(value = "echo/{str}")
  public String echo(@PathVariable String str) {
    return echoService.echo(str);
  }

  @GetMapping(value = "lb")
  public String lb() {
    return echoService.lb();
  }

  @GetMapping(value = "config")
  public String nacosConfig() {
    return "Get config of user.name= " + username + " from nacos";
  }

}
