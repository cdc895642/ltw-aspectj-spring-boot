package com.test.ltwaspectjtest;

import com.test.ltwaspectjtest.service.TestService;
import com.test.ltwaspectjtest.service.TestService2;
import org.aspectj.lang.Aspects;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.EnableLoadTimeWeaving.AspectJWeaving;
import org.springframework.context.annotation.LoadTimeWeavingConfigurer;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.instrument.classloading.tomcat.TomcatLoadTimeWeaver;

@SpringBootApplication
@EnableLoadTimeWeaving//(aspectjWeaving = AspectJWeaving.ENABLED)
public class LtwAspectjTestApplication implements CommandLineRunner, LoadTimeWeavingConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(LtwAspectjTestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		TestService testService=new TestService();
		testService.test();
		testService.test();
		TestService2 testService2 = new TestService2();
		testService2.test2("aaa");

	}

//	@Bean
//	public ProfilingAspect hystrixAspect() {
//		ProfilingAspect aspect = Aspects.aspectOf(ProfilingAspect.class);
//		return aspect;
//	}

	@Override
	public LoadTimeWeaver getLoadTimeWeaver() {
		return new InstrumentationLoadTimeWeaver();
	}
}
