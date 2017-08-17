package kr.re.kitri.auth_demo.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MeasuringInterceptor extends HandlerInterceptorAdapter {


    public static Logger log = LoggerFactory.getLogger(MeasuringInterceptor.class);

    // Ctrl + O 해서 상속받을 것 선택
    // true 일 때 무사 통과
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        //log.info("in the preHandle of MeasuringInterceptor");
        long millis = System.currentTimeMillis();
        request.setAttribute("b_time", millis);                 // 요청 들어올 때 시작 시간(begin time)
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {

        //log.info("in the afterCompletion of MeasuringInterceptor");
        long endMillis = System.currentTimeMillis();                // 응답 완료되었을 때 시간
        long beginMillis = (long)request.getAttribute("b_time");

        long elapsedTime = endMillis - beginMillis;

        log.info(request.getRequestURI() + "의 실행 시간은 " + elapsedTime + "ms 입니다.");
    }
}
