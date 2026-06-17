import java.util.Random;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Error");

        try {
            System.out.println("Kernel panic - not syncing: System crashed unexpectedly.");
            System.out.println("Arch Linux Kernel 5.20.13 (root@archbox) (gcc version 12.2.1 20230201) #1 SMP PREEMPT");
            System.out.println("CPU: 0 PID: 4321 Comm: java Tainted: G        L     5.20.13-arch1-1");
            System.out.println("Hardware name: Generic Virtual Machine");
            System.out.println("");
            System.out.println("Call Trace:");

			Random rand = new Random();
            long duration = 5000;
            long start = System.currentTimeMillis();

            while (System.currentTimeMillis() - start < duration) {
				String addr1 = String.format("0x%03x", rand.nextInt(0xFFF));
				String addr2 = String.format("0x%03x", rand.nextInt(0xFFF));

				System.out.println(" dump_stack+" + addr1 + "/" + addr2);
				System.out.println(" panic+" + addr1 + "/" + addr2);
				System.out.println(" ? do_exit+" + addr1 + "/" + addr2);
				System.out.println(" do_exit+" + addr1 + "/" + addr2);
				System.out.println(" ? do_group_exit+" + addr1 + "/" + addr2);
				System.out.println(" ? sys_exit_group+" + addr1 + "/" + addr2);
				System.out.println(" sys_exit_group+" + addr1 + "/" + addr2);

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ProcessBuilder pb = new ProcessBuilder("notmyfaultc64.exe", "crash", "1");
			Process process = pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}