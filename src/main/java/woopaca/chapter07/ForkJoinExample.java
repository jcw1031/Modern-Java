package woopaca.chapter07;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinExample {

    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }
}

// RecursiveTask를 상속받아 포크/조인 프레임워크에서 사용할 태스크를 생성
class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private final long[] numbers; // 더할 숫자 배열
    private final int start; // 서브태스크에서 처리할 배열의 초기 위치
    private final int end; // 서브태스크에서 처리할 배열의 최종 위치
    public static final long THRESHOLD = 10_000; // 이 값 이하의 서브태스크는 더 이상 분해 불가

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    // RecursiveTask의 추상 메서드 오버라이드
    @Override
    protected Long compute() {
        int length = end - start; // 해당 태스크에서 더할 배열의 길이
        if (length <= THRESHOLD) {
            return computeSequentially(); // 기준값과 같거나 작으면 순차적으로 결과 계산
        }

        // 배열의 첫 번째 절반 서브태스크 생성
        ForkJoinSumCalculator leftTask =
                new ForkJoinSumCalculator(numbers, start, start + length / 2);
        leftTask.fork(); // ForkJoinPool의 다른 스레드로 새로 생성한 태스크를 비동기 실행
        // 배열의 나머지 절반 서브태스크 생성
        ForkJoinSumCalculator rightTask =
                new ForkJoinSumCalculator(numbers, start + length / 2, end);
        Long rightResult = rightTask.compute(); // 두 번째 서브태스크를 동기 실행 (추가로 분할 가능)
        Long leftResult = leftTask.join(); //첫 번째 서브태스크의 결과를 읽거나 결과가 없는 경우 대기
        return leftResult + rightResult; // 두 서브태스크의 결과를 조합
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
