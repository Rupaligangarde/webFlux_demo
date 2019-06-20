import org.junit.Test
import org.slf4j.LoggerFactory
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.lang.NullPointerException
import java.time.Duration
import java.util.concurrent.CountDownLatch


class ReactiveExampleTest {
    val logger = LoggerFactory.getLogger(ReactiveExampleTest::class.java)


    val rupali = Person("Rupali", "gangarde")
    val john = Person("John", "Thomson")
    val chandler = Person("Chandler", "Bing")
    val ross = Person("Ross", "Aik")
    val monica = Person("Monica", "Geller")

    @Test
    fun monoTest() {
        val rupaliMono = Mono.just(rupali)
        val person = rupaliMono.block()
        logger.info(person.sayMyName())
    }

    @Test
    fun monoTransform() {
        val johnMono = Mono.just(john)
        val personCommand = johnMono
            .map { person ->
                return@map PersonCommand(person)
            }.block()

        logger.info(personCommand.sayMyName())

    }

    @Test(expected = NullPointerException::class)
    fun monoFilter() {
        val chandlerMono = Mono.just(chandler)
        val person = chandlerMono
            .filter { person ->
                person.firstName.equals("sam", true)
            }.block()

        logger.info(person.sayMyName())
    }

    @Test
    fun fluxTest() {
        val flux = Flux.just(rupali, chandler, ross, monica)
        flux
            .subscribe { person ->
                logger.info(person.sayMyName())
            }
    }

    @Test
    fun fluxDelayOperationOnOutput() {
        val flux = Flux.just(rupali, chandler, monica, john)
        flux
            .delayElements(Duration.ofSeconds(1))
            .subscribe { person ->
                logger.info(person.sayMyName())
            }

    }
//
//    @Test
//    fun fluxDelayTest() {
//        val flux = Flux.just(rupali, monica, ross)
//        val countDownLatch = CountDownLatch(1)
//        flux
//            .delayElements(Duration.ofSeconds(1))
//    }
}
