package com.cloudboy.study.thread.lesson5;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
  
/**
 * 求和，采用CompletionService
 * @author cloudboy(yun.xia)
 *
 */
public class ConcurrentCalculator2 {  
  
    private ExecutorService exec;
    private CompletionService<Long> completionService;
    private int cpuCoreNumber;  
  
    // 内部类  
    class SumCalculator implements Callable<Long> {  
        private int[] numbers;  
        private int start;  
        private int end;  
  
        public SumCalculator(final int[] numbers, int start, int end) {  
            this.numbers = numbers;  
            this.start = start;  
            this.end = end;  
        }  
  
        public Long call() throws Exception {  
            Long sum = 0l;  
            for (int i = start; i < end; i++) {  
                sum += numbers[i];  
            }  
            return sum;  
        }  
    }  
  
    public ConcurrentCalculator2() {  
        cpuCoreNumber = Runtime.getRuntime().availableProcessors();  
        exec = Executors.newFixedThreadPool(cpuCoreNumber);
        completionService = new ExecutorCompletionService<Long>(exec);
    }  
  
    public Long sum(final int[] numbers) {  
        // 根据CPU核心个数拆分任务，创建FutureTask并提交到Executor  
        for (int i = 0; i < cpuCoreNumber; i++) {  
            int increment = numbers.length / cpuCoreNumber + 1;  
            int start = increment * i;  
            int end = increment * i + increment;  
            if (end > numbers.length)  {
                end = numbers.length;
            }
            SumCalculator subCalc = new SumCalculator(numbers, start, end);  
            if(!exec.isShutdown()) {
            	completionService.submit(subCalc);
            }  
        }  
        return getResult();  
    }  
  
    /** 
     * 迭代每个只任务，获得部分和，相加返回 
     *  
     * @return 
     */  
    public Long getResult() {  
        Long result = 0l;  
        for (int i = 0; i < cpuCoreNumber; i++) {
            try {  
                Long subSum = completionService.take().get();  
                result += subSum;             
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            } catch (ExecutionException e) {  
                e.printStackTrace();
            }  
        }
        return result;  
    }  
  
    public void close() {  
        exec.shutdown();
    }
    
    public static void main(String[] args) {
    	int[] numbers = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};  
    	ConcurrentCalculator2 calc = new ConcurrentCalculator2();  
    	Long sum = calc.sum(numbers);  
    	System.out.println(sum);  
    	calc.close();
    }
} 
