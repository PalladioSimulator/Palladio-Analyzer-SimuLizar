package de.upb.pcm.interpreter.ssj;

import java.util.concurrent.Semaphore;

public class SimProcessThreadingStrategy implements ISimProcessStrategy {

	private Thread myThread = null;

	Semaphore waitingSemaphore = new Semaphore(0);
	Semaphore waitingForSuspendSemaphore = new Semaphore(0);

	public void startProcess(Runnable myRunnable) {
		this.myThread = new Thread(myRunnable);
		this.myThread.start();
		waitingForSuspendSemaphore.acquireUninterruptibly();
	}

	public void resumeProcess() {
		waitingSemaphore.release();
		waitingForSuspendSemaphore.acquireUninterruptibly();
	}

	public void finishProcess() {
		// This process is done and will not suspend any more...
		// Hence, release its wait for suspend semaphore held by the main control thread.
		this.waitingForSuspendSemaphore.release();
	}

	public void suspendProcess() {
		waitingForSuspendSemaphore.release();
		waitingSemaphore.acquireUninterruptibly();
	}

}
