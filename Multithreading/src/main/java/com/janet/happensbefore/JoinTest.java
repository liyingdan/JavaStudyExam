package com.janet.happensbefore;

/**
 * @Description TODO
 * @Author Janet
 * @Date 2021-08-08
 */
public class JoinTest {

    public static void main(String[] args) throws InterruptedException {
        Book book = new Book(199);
        Thread subThread = new Thread(()->{
            //在子线程内部对book的价格进行修改
            book.setPrice(99);
        });
        subThread.start();
        subThread.join(); //如果不加 join 方法的话，看主线程和子线程谁先执行，书的价格不一定修改成功，加了join的意思是子线程执行完之后，主线程再执行
        System.out.println(book.getPrice());//99
    }

    static class Book {
        private int price;
        public Book(int price) {
            this.price = price;
        }
        public int getPrice() {
            return price;
        }
        public void setPrice(int price) {
            this.price = price;
        }
    }

}
