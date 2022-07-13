package info.whitesweater.day05;


import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

@Slf4j(topic = "c.Test01")
public class Test01 {

    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue(2);
        for (int i = 0; i < 3; i++) {
            int id = i;
            new Thread(() -> {
                queue.put(new Message(id, "消息" + id));
            }, "生产者" + id).start();
        }
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    Message message = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "消费者").start();

        
    }

}

/**
 * 消息队列类  用于Java线程之间通信
 */
@Slf4j(topic = "c.MessageQueue")
class MessageQueue {

    //    消息的队列集合
    private LinkedList<Message> list = new LinkedList<>();
    //    队列容量
    private int capacity;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 获取消息
     */
    public Message take() {
        synchronized (list) {
            //检查队列是否为空
            while (list.isEmpty()) {
                try {
                    log.debug("队列为空，消费者线程等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//            从队列的头部获取消息返回
            Message message = list.removeFirst();
            log.debug("已消费消息：{}", message);
//            唤醒生产者的等待
            list.notifyAll();
            return message;
        }
    }

    /**
     * 存入消息
     */
    public void put(Message message) {
        synchronized (list) {
//            检查队列是否已满
            while (list.size() == capacity) {
                try {
                    log.debug("队列已满，生产者线程等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//            将消息加入队列尾部
            list.addLast(message);
            log.debug("已生产消息：{}", message);
//            唤醒消费者的等待
            list.notifyAll();
        }
    }
}

final class Message {
    private int id;
    private Object value;

    public Message(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
