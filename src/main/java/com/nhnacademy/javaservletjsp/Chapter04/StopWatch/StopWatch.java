package com.nhnacademy.javaservletjsp.Chapter04.StopWatch;

// 실행시간 측정을 자동화할 StopWatch Annotation을 정의함

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;

/*
* @Target
* Annotation이 적용가능한 대상을 지정하는데 사용함
* 컴파일러가 Annotation을 어디에 적용할지 결정하는 Annotation
* ElementType.METHOD : 메서드 선언
* */
@Target(value = {ElementType.METHOD})
/*
* @Retention
* Annotation이 유지되는 범위를 지정하는데 사용함
* RetentionPolicy.RUNTIME : 컴파일 이후에도 JVM에 의해서 계속 참조가 가능, 주로 리플렉션이나 로깅에 많이 사용됨
* */
@Retention(RetentionPolicy.RUNTIME)

public @interface StopWatch {
}
