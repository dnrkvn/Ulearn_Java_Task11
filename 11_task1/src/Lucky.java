public class Lucky {

    static int x = 0;
    static int count = 0;

    static class LuckyThread extends Thread {
        Resource resource;

        LuckyThread(Resource resource){
            this.resource = resource;
        }

        @Override
        public void run() {
            resource.count();
        }
    }

    static class Resource {
        synchronized void count(){
            while (x < 999999) {
                x++;
                if ((x % 10) + (x / 10) % 10 + (x / 100) % 10 == (x / 1000)
                        % 10 + (x / 10000) % 10 + (x / 100000) % 10) {
                    System.out.println(x);
                    count++;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Resource resource = new Resource();
        Thread t1 = new LuckyThread(resource);
        Thread t2 = new LuckyThread(resource);
        Thread t3 = new LuckyThread(resource);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + count);
    }

}
