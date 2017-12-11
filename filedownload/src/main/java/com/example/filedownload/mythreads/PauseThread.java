package com.example.filedownload.mythreads;

/**
 *
 * @author libohan
 *         邮箱:76681287@qq.com
 *         create on 2017/12/5.
 */

/**
 * 可以暂停的线程
 */
public class PauseThread  extends Thread{
    private boolean flag=false;
    private final Object object=new Object();
    private TaskToDo taskToDo;

    public PauseThread(TaskToDo taskToDo) {
        this.taskToDo = taskToDo;
    }

    public void resumeThread()
    {
        flag=false;
        synchronized (object)
        {
            object.notifyAll();
        }
    }
    public void pauseThread()
    {
        flag=true;
    }
    private void onPause()
    {
        synchronized (object)
        {
            try {
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void run() {
        super.run();
        while (true)
        {
            while (flag)
            {
                onPause();
            }
            if (taskToDo!=null)
            {
                taskToDo.toDo();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 要在线程里操作的业务逻辑
     */
    public interface TaskToDo
    {
        void toDo();
    }
}
