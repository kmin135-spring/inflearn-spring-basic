package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final MyLogger myLogger;
//    private final ObjectProvider<MyLogger> myLoggerProvider;

    @ResponseBody
    @GetMapping("log-demo")
    public String logDemo(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        // getObject 호출 시점에 request scope로 빈을 만들어줌.
//        MyLogger myLogger = myLoggerProvider.getObject();


        // proxyMode = ScopedProxyMode.TARGET_CLASS 로 프록시를 먼저 주입해둠
        // myLogger = class hello.core.common.MyLogger$$EnhancerBySpringCGLIB$$c53f5397
        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestUrl);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "log-demo";
    }
}
