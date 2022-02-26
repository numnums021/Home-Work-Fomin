package com.sbrf.reboot.concurrency;

import com.sbrf.reboot.service.concurrency.Task;
import com.sbrf.reboot.service.concurrency.TaskExecutorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class TaskExecutorServiceTest {

    @Test
    public void successRunMultithreading() throws InterruptedException {

        Task task = Mockito.mock(Task.class);

        //  Объект класса CountDownLatch, даёт возможность заставить потоки ждать,
        //  пока не завершится определённое число операций (2)
        CountDownLatch latch = new CountDownLatch(2);

        // Создание пула потоков фиксированного размера с помощью ExecutorService
        TaskExecutorService taskExecutorService = new TaskExecutorService(2);

        // Позволяет заглушать метод void с помощью универсального интерфейс ответа
        doAnswer((e -> {
            // CountDown() - после завершения каждого потока, гарантирует,
            // что зависимый поток, вызывающий await(), будет блокироваться до тех пор,
            // пока рабочие потоки не будут завершены.
            latch.countDown();
            return null;
        })).when(task).run();

        taskExecutorService.execute(task);

        latch.await();

        assertEquals(0, latch.getCount());

        verify(task, times(2)).run();

        taskExecutorService.shutdown();
    }

    @Test
    void successStartMultithreadingOnBlockingThreads() throws InterruptedException {

        Task task = Mockito.mock(Task.class);

        int amountOfThreads = getNumberOfBlockingThreads();

        CountDownLatch latch = new CountDownLatch(amountOfThreads);

        TaskExecutorService taskExecutorService = new TaskExecutorService(amountOfThreads);

        doAnswer((e -> {
            latch.countDown();
            return null;
        })).when(task).run();

        taskExecutorService.execute(task);

        latch.await();

        assertEquals(0, latch.getCount());

        verify(task, times(9)).run();

        taskExecutorService.shutdown();
    }

    @Test
    void successStartMultithreadingOnNonBlockingThreads() throws InterruptedException {

        Task task = Mockito.mock(Task.class);

        int amountOfThreads = getNumberOfNonBlockingThreads();

        CountDownLatch latch = new CountDownLatch(amountOfThreads);

        TaskExecutorService taskExecutorService = new TaskExecutorService(amountOfThreads);

        doAnswer((e -> {
            latch.countDown();
            return null;
        })).when(task).run();

        taskExecutorService.execute(task);

        latch.await();

        assertEquals(0, latch.getCount());

        verify(task, times(5)).run();

        taskExecutorService.shutdown();
    }


    /*
        Потоки не блокируют друг друга, нет циклов ожидания I/O,
        и время обработки задач одинаково
    */
    public int getNumberOfNonBlockingThreads() {
        return Runtime.getRuntime().availableProcessors() + 1;
    }

    /*
        Потоки в основном ожидают I/O, оптимальный размер пула должен быть увеличен на
        отношение между временем ожидания процесса и временем вычисления
    */
    public int getNumberOfBlockingThreads() {
        return 2 * Runtime.getRuntime().availableProcessors() + 1;
    }

}








