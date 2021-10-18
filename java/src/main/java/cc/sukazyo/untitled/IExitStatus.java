package cc.sukazyo.untitled;

public interface IExitStatus {
	
	interface Success extends IExitStatus {}
	
	interface Failed extends IExitStatus {}
	
	interface SystemEnd extends IExitStatus, Success {}
	
}
