package de.upb.pcm.interpreter.ssj;

import org.apache.commons.javaflow.Continuation;

public class SimProcessContinuationStrategy implements ISimProcessStrategy {

	private Continuation thisThreadsContinuation = null;

	public void startProcess(Runnable myRunnable) {
		// This lets the thread run until it suspends or terminates and stores
		// its state in thisThreadsContinuation
		thisThreadsContinuation = Continuation.startWith(myRunnable);
	}

	public void resumeProcess() {
		thisThreadsContinuation = Continuation
				.continueWith(thisThreadsContinuation);
	}

	public void finishProcess() {
	}

	public void suspendProcess() {
		Continuation.suspend();
	}

}
