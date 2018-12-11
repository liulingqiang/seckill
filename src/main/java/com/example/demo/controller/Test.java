package com.example.demo.controller;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import java.util.concurrent.TimeUnit;

/**
 * Created by liulq on 2018-11-27 .
 */

public class Test {
    static private Flux<String> getZipDescFlux() {
        String desc = "Zip two sources together, that is to say wait for all the sources to emit one element and combine these elements once into a Tuple2.";
        return Flux.fromArray(desc.split("\\s+"));  // 1
    }
    static private String getStringSync() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello, Reactor!";
    }
    static public void testBackpressure() {
        Flux.range(1, 6)    // 1
                .doOnRequest(n -> System.out.println("Request " + n + " values..."))    // 2
                .subscribe(new BaseSubscriber<Integer>() {  // 3
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) { // 4
                        System.out.println("Subscribed and make a request...");
                        request(2); // 5
                    }

                   @Override
                    protected void hookOnNext(Integer value) {  // 6

                        System.out.println("Get value [" + value + "]");    // 8
                       request(2);

                    }
                });
    }
    public static void main(String[] args) throws InterruptedException {
        testBackpressure();
        TimeUnit.SECONDS.sleep(10);

       /* Flux.range(1, 6)
                .map(i -> 10/(i-3)) // 1
                .map(i -> i*i)
                .subscribe(System.out::println, System.err::println);*/

        /*Mono.fromCallable(() -> getStringSync())    // 1
                .subscribeOn(Schedulers.elastic())  // 2
                .subscribe(System.out::println);
        System.out.println("-----");*/
       // TimeUnit.SECONDS.sleep(10);

        /*Flux.zip(
                getZipDescFlux(),
                Flux.interval(Duration.ofMillis(400)))  // 3
                .subscribe(t -> System.out.println(t.getT1()));    // 4

        TimeUnit.SECONDS.sleep(10);*/
        /*Flux.just(1, 2, 3, 4, 5, 6).map(x -> x * x).subscribe(System.out::println);
        Flux.just("flux", "mono")
                .flatMap(s -> Flux.fromArray(s.split("\\s*")))
                .subscribe(System.out::println);
        System.out.println();
        Mono.just(1).subscribe(System.out::println);*/
    }
}
