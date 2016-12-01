package net.jcip.examples;

import java.util.concurrent.locks.*;

/**
 * InterruptibleLocking
 *
 * @author Brian Goetz and Tim Peierls
 */
public class InterruptibleLocking {
    private Lock lock = new ReentrantLock();

    public boolean sendOnSharedLine(String message)
            {
        try {
			lock.lockInterruptibly();
			return cancellableSendOnSharedLine(message);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
            lock.unlock();
        }
        return false;
    }

    private boolean cancellableSendOnSharedLine(String message)  {
        /* send something */
        return true;
    }

}
