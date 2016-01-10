package com.cloudboy.study.jvm.gc;

public class FinalizeEscapeGC {
	public static FinalizeEscapeGC SAVE_HOOK = null;

	public void isAlive() {
		System.out.println("yes, i am alive.");
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("finalize method executed!");

		FinalizeEscapeGC.SAVE_HOOK = this;
	}

	public static void main(String[] args) throws InterruptedException {
		SAVE_HOOK = new FinalizeEscapeGC();

		// 对象头一次成功拯救自己
		SAVE_HOOK = null;
		System.gc();
		// 因为finalize方法优先级很低，所以暂停0.5秒等待它
		Thread.sleep(500);
		if (SAVE_HOOK != null) {
			SAVE_HOOK.isAlive();
		} else {
			System.out.println("no, i am dead");
		}

		// 下面的代码完全相同，但第二次拯救失败了。(GC只会调用finalize方法一次)
		SAVE_HOOK = null;
		System.gc();
		// 因为finalize方法优先级很低，所以暂停0.5秒等待它
		Thread.sleep(500);
		if (SAVE_HOOK != null) {
			SAVE_HOOK.isAlive();
		} else {
			System.out.println("no, i am dead");
		}
	}

}
