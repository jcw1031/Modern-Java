package woopaca.chapter07;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.TearDown;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xmx4G"})
public class ParallelStreamExample {

    private static final long N = 10_000_000L;

    @Benchmark
    private long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(N)
                .reduce(0L, Long::sum);
    }

    private long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    @TearDown(Level.Invocation)
    public void tearDown() {
        System.gc();
    }

    public long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n)
                .forEach(accumulator::add);
        return accumulator.total;
    }

    public long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel()
                .forEach(accumulator::add);
        return accumulator.total;
    }
}

class Accumulator {

    public long total = 0;

    public void add(long value) {
        total += value;
    }
}
