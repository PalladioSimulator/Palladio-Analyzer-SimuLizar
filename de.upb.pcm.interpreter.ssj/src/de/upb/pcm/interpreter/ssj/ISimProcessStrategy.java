package de.upb.pcm.interpreter.ssj;

public interface ISimProcessStrategy {

	public abstract void startProcess(Runnable myRunnable);

	public abstract void resumeProcess();

	public abstract void finishProcess();

	public abstract void suspendProcess();

}