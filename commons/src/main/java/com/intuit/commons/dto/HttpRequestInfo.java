package com.intuit.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.AntPathMatcher;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HttpRequestInfo {
  private String httpMethod;
  private String path;

  public boolean isMatchingHttpRequest(String httpMethod, String url) {
    return this.httpMethod.equalsIgnoreCase(httpMethod) &&
        new AntPathMatcher().match(this.path, url);
  }
}
