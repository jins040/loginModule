package kr.re.kitri.auth_demo;

import kr.re.kitri.auth_demo.interceptor.MeasuringInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class AuthDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthDemoApplication.class, args);
	}

	@Bean
	public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(new MeasuringInterceptor()).addPathPatterns("/**");
				// + 인터셉터 추가하는 순서대로 인터셉터가 걸린다, excludePath 하면 예외 경로도 설정할 수 있다.
			}
		};
	}
}
